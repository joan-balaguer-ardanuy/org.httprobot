package org.httprobot.unit;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Enums.Data;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebDocument;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionManager
	extends Manager<WebDocument, Set<WebDocument>, ActionControl> {

	/**
	 * -6659121403717296708L
	 */
	private static final long serialVersionUID = -6659121403717296708L;

	WebLoaderManager webLoaderManager;
	Map<Constant, ConstantManager> constantManagers;
	ElementManager elementManager;
	
	WebDocument currentOutput;

	Set<WebElement> clickableElements;
	
	public ActionManager() {
		super();
	}
	public ActionManager(Action message, ManagerListener parent) {
		super(message, ActionControl.class, parent);
	}
	
	@Override
	public Set<WebDocument> put(WebDocument key, Set<WebDocument> value) {
		WebElement htmlPage;
		if(key == null) {
			String httpAddress = (String) getControl().get(Data.HTTP_ADDRESS);
			httpAddress = deParameterizeURL(httpAddress);
			htmlPage = webLoaderManager.getPage(httpAddress);
			key = new WebDocument(httpAddress, htmlPage);
		}
		else {
			htmlPage = webLoaderManager.getPage(key.getUrl());
		}
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		elementManager.put(htmlPage, new LinkedHashSet<WebElement>());
		
		for(WebElement element : clickableElements) {
			WebDriver driver = getWebDriver();
			Actions action = new Actions(driver);
			String script = getControl().get(Data.JAVASCRIPT) != null ?
					(String) getControl().get(Data.JAVASCRIPT) : null;
			if(script != null) {
				((JavascriptExecutor) driver).executeScript(script, element);
			}
			action.moveToElement(element).click().perform();
			
			webLoaderManager.put(driver.getCurrentUrl(), driver.findElement(By.xpath("/html")));
		}
		return super.put(key, value);
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
			break;
		case FINISHED: 
			if(e.getSource().equals(webLoaderManager)) {
				currentOutput = new WebDocument(webLoaderManager.getKey(), webLoaderManager.getValue());
				
				getValue().add(currentOutput);
				
				ManagerEvent(new ManagerEventArgs(this, currentOutput, ManagerEventType.ACTION_WEB_LOADED));
			}
			break;
		case NEW_ELEMENT:
			if(e.getSource() instanceof ElementManager) {
				ElementManager elementManager = ElementManager.class.cast(e.getSource());
				WebElement webElement = WebElement.class.cast(e.getValue());
				if((Boolean) elementManager.getControl().get(Data.CLICK)) {
					WebDriver driver = getWebDriver();
					Actions action = new Actions(driver);
					String script = elementManager.getControl().get(Data.JAVASCRIPT) != null ?
							(String) elementManager.getControl().get(Data.JAVASCRIPT) : null;
					if(script != null) {
						((JavascriptExecutor) driver).executeScript(script, webElement);
					}
					action.moveToElement(webElement).click().perform();
				}
				else if((Boolean) elementManager.getControl().get(Data.STORE)) {
					clickableElements.add(webElement);
				}
			}
			break;
		default:
			break;
		}
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
		case ELEMENT_CONTROL_LOADED:
			if(e.getSource() instanceof ElementControl) {
				Element element = ElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.WEB_LOADER).equals(element)) {
					elementManager = new ElementManager(element, this);
					addChildManager(elementManager);
				}
			}
			break;
		default:
			break;
		}
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