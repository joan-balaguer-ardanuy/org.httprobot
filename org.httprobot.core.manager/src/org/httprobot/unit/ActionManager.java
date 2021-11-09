package org.httprobot.unit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Enums.Data;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.parameter.BannedWord;
import org.httprobot.parameter.BannedWordControl;
import org.httprobot.parameter.BannedWordManager;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantManager;
import org.httprobot.placeholder.html.ElementManager;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ActionManager
	extends Manager<HtmlPage, Set<HtmlPage>, ActionControl> {

	/**
	 * -6659121403717296708L
	 */
	private static final long serialVersionUID = -6659121403717296708L;

	WebLoaderManager webLoaderManager;
	Map<Constant, ConstantManager> constantManagers;
	Map<BannedWord, BannedWordManager> bannedWordManagers;
	ElementManager elementManager;
	
	WebRequest currentOutputRequest;
	HtmlPage currentOutputPage;

	Set<HtmlAnchor> anchors;
	
	public ActionManager() {
		super();
	}

	public ActionManager(Action message, ManagerListener parent) {
		super(message, ActionControl.class, parent);
		
		webLoaderManager = new WebLoaderManager(message.getWebLoader(), this);
		elementManager = new ElementManager(message.getElement(), this);
	}
	@Override
	public Set<HtmlPage> put(HtmlPage key, Set<HtmlPage> value) {
		if(key == null) {
			String httpAddress = String.class.cast(getControl().get(Data.HTTP_ADDRESS));
			httpAddress = deParameterizeURL(httpAddress);
			try {
				WebRequest webRequest = new WebRequest(new URL(httpAddress));
				key = webLoaderManager.getPage(webRequest);
			} catch (MalformedURLException e) {
				throw new Error("ActionManager.put: bad first URL", e);
			}
		}
		keySet().add(key);
		
		elementManager.put(key, new LinkedHashSet<DomNode>());
		
		for(HtmlAnchor anchor : anchors) {
			String hrefAttribute = anchor.getHrefAttribute();
			if(hrefAttribute.startsWith("http://") || hrefAttribute.startsWith("https://")) {
				if(!containsBannedWord(hrefAttribute)) {
					try {
						URL url = new URL(hrefAttribute);
						webLoaderManager.put(new WebRequest(url), null);
					} catch (MalformedURLException e) {
						throw new Error("ActionManager.put: Malformed URL object.", e);
					}
				}
			}
			else if(hrefAttribute.startsWith("/")) {
				hrefAttribute = hrefAttribute.substring(1);
				if(!hrefAttribute.isEmpty()) {
					if(!containsBannedWord(hrefAttribute)) {
						String finalUrl = getConstants().get("[@server_url]") + hrefAttribute;
						
						try {
							URL url = new URL(finalUrl);
							webLoaderManager.put(new WebRequest(url), null);
						} catch (MalformedURLException e) {
							throw new Error("ActionManager.put: Malformed URL object.", e);
						}
					}
				}
			}
		}
		return super.put(getKey(), getValue());
	}

	private boolean containsBannedWord(String href) {
		for (String key : getBannedWords().keySet()) {
			String value = getBannedWords().get(key);

			if (href.contains(value)) {
				return true;
			}
		}
		return false;
	}

	private String deParameterizeURL(String url) {
		// De-parameterization of current address
		for (String paramName : getConstants().keySet()) {
			// Replace all instances of
			if (url.contains(paramName)) {
				String paramValue = getConstants().get(paramName);
				url = url.replace(paramName, paramValue);
			}
		}
		return url;
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case WEB_LOADER_CONTROL_LOADED:
			if(e.getSource() instanceof WebLoaderControl) {
				WebLoader webLoader = WebLoaderControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.WEB_LOADER).equals(webLoader)) {
					webLoaderManager = new WebLoaderManager(webLoader, this);
					addChildManager(webLoaderManager);
				}
			}
			break;
		case CONSTANT_CONTROL_LOADED:
			if(e.getSource() instanceof ConstantControl) {
				Constant constant = ConstantControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.CONSTANT).equals(constant)) {
					ConstantManager constantManager = new ConstantManager(constant, this);
					constantManagers.put(constant, constantManager);
					addChildManager(constantManager);
				}
			}
			break;
		case BANNED_WORD_CONTROL_LOADED:
			if(e.getSource() instanceof BannedWordControl) {
				BannedWord bannedWord = BannedWordControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.BANNED_WORD).equals(bannedWord)) {
					BannedWordManager bannedWordManager = new BannedWordManager(bannedWord, this);
					bannedWordManagers.put(bannedWord, bannedWordManager);
					addChildManager(bannedWordManager);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource() instanceof ConstantManager) {
				ConstantManager constantManager = ConstantManager.class.cast(e.getSource());
				if(constantManagers.containsValue(constantManager)) {
					getConstants().put(constantManager.getKey(), constantManager.getValue());
				}
			}
			else if(e.getSource() instanceof BannedWordManager) {
				BannedWordManager bannedWordManager = BannedWordManager.class.cast(e.getSource());
				if(bannedWordManagers.containsValue(bannedWordManager)) {
					getBannedWords().put(bannedWordManager.getKey(), bannedWordManager.getValue());
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(webLoaderManager)) {
				currentOutputPage = webLoaderManager.getValue();
				currentOutputRequest = webLoaderManager.getKey();
				
				getValue().add(currentOutputPage);
				
				ManagerEvent(new ManagerEventArgs(this, 
						new Node<WebRequest, HtmlPage>(currentOutputRequest, currentOutputPage), 
						ManagerEventType.ACTION_WEB_LOADED));
			}
			break;
		case DOM_SET_COMPLETED:
			if(e.getSource().equals(elementManager)) {
				@SuppressWarnings("unchecked")
				Set<DomNode> set = (Set<DomNode>) e.getValue();
				
				for(DomNode node : set) {
					if(node instanceof HtmlAnchor) {
						anchors.add((HtmlAnchor) node);
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	public class Node<K,V> implements java.util.Map.Entry<K, V> {

		K key;
		V value;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V value) {
			return null;
		}
	}
}