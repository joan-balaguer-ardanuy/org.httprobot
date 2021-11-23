package org.httprobot.unit;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.httprobot.Enums.Data;
import org.httprobot.Enums.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class ActionManager
	extends Manager<WebElement, Set<WebElement>, ActionControl> {

	/**
	 * -6659121403717296708L
	 */
	private static final long serialVersionUID = -6659121403717296708L;

	WebLoaderManager webLoaderManager;
	Map<Constant, ConstantManager> constantManagers;
	ElementManager elementManager;
	
	URL currentOutputRequest;
	WebElement currentOutputPage;

	Set<WebElement> clickableElements;
	
	public ActionManager() {
		super();
	}
	public ActionManager(Action message, ManagerListener parent) {
		super(message, ActionControl.class, parent);
	}
	
	@Override
	public Set<WebElement> put(WebElement key, Set<WebElement> value) {
		if(key == null) {
			String httpAddress = (String) getControl().get(Data.HTTP_ADDRESS);
			httpAddress = deParameterizeURL(httpAddress);
			try {
				key = webLoaderManager.getPage(new URL(httpAddress));
			} catch (MalformedURLException e) {
				throw new Error("ActionManager.put: bad first URL", e);
			}
		}
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		elementManager.put(key, new LinkedHashSet<WebElement>());
		
		for(WebElement element : clickableElements) {
			WebDriver driver = getWebDriver();
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			
			try {
				URL url = new URL(driver.getCurrentUrl());
				webLoaderManager.put(url, driver.findElement(By.xpath("/html")));
			} catch (MalformedURLException e) {
				throw new Error("ActionManager.put: Malformed URL object.", e);
			}
		}
		return super.put(key, value);
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
				currentOutputPage = webLoaderManager.getValue();
				currentOutputRequest = webLoaderManager.getKey();
				
				getValue().add(currentOutputPage);
				
				ManagerEvent(new ManagerEventArgs(this, 
						new Node<URL, WebElement>(currentOutputRequest, currentOutputPage), 
						ManagerEventType.ACTION_WEB_LOADED));

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

	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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