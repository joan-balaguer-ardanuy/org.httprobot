package org.httprobot.unit;

import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.httprobot.Data;
import org.httprobot.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.configuration.Selenium;
import org.httprobot.MapManager;
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
	extends MapManager<WebDocument, Set<WebDocument>, ActionControl> {

	/**
	 * -6659121403717296708L
	 */
	private static final long serialVersionUID = -6659121403717296708L;

	WebLoaderManager webLoaderManager;
	Map<Constant, ConstantManager> constantManagers;
	ElementManager elementManager;
	
	Selenium selenium;
	
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
		WebDriver driver = getSelenium().getWebDriver();
		WebElement htmlPage;
		
		if(key == null) {
			String httpAddress = (String) getControl().get(Data.HTTP_ADDRESS);
			httpAddress = deParameterizeURL(httpAddress);
			htmlPage = getPage(driver, httpAddress);
			key = new WebDocument(httpAddress, htmlPage.findElement(By.xpath("/html")).getAttribute("outerHTML"));
		}
		else {
			try {
				DOMSource domSource = new DOMSource(key.getDocument());
				StringWriter writer = new StringWriter();
				StreamResult result = new StreamResult(writer);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.transform(domSource, result);
				// Implementation of Data URLs: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Data_URIs
				htmlPage = getPage(driver, "data:text/html;charset=utf-8," + writer.toString());
			} catch (TransformerException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		keySet().add(key);
		setKey(key);
		setValue(value);

		if (elementManager != null) {
			elementManager.put(htmlPage, new LinkedHashSet<WebElement>());

			Actions action = new Actions(driver);

			for (WebElement element : clickableElements) {

				if (getControl().get(Data.JAVASCRIPT) != null) {
					((JavascriptExecutor) driver).executeScript((String) getControl().get(Data.JAVASCRIPT), element);
				}
				action.moveToElement(element).click().perform();

				loadWebDocument(driver);
			}
		} else {
			loadWebDocument(driver);
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
				currentOutput = webLoaderManager.getValue();
				getValue().add(currentOutput);
				ManagerEvent(new ManagerEventArgs(this, currentOutput, ManagerEventType.ACTION_WEB_LOADED));
			}
			break;
		case NEW_ELEMENT:
			if(e.getSource() instanceof ElementManager) {
				ElementManager elementManager = ElementManager.class.cast(e.getSource());
				WebElement webElement = WebElement.class.cast(e.getValue());
				if((Boolean) elementManager.getControl().get(Data.CLICK)) {
					WebDriver driver = getSelenium().getWebDriver();
					Actions action = new Actions(driver);
					
					if(elementManager.getControl().get(Data.JAVASCRIPT) != null) {
						((JavascriptExecutor) driver).executeScript((String) elementManager.getControl().get(Data.JAVASCRIPT), webElement);
					}
					action.moveToElement(webElement).click().perform();
				} else if((Boolean) elementManager.getControl().get(Data.ADD)) {
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
	private void loadWebDocument(WebDriver driver) {
		String url = driver.getCurrentUrl();

		if (((Boolean) getControl().get(Data.CLEAR_QUERY))) {
			url = url.substring(0, url.lastIndexOf('/') + 1);
		}

		webLoaderManager.put(url, new WebDocument(url, driver.findElement(By.xpath("/html")).getAttribute("outerHTML")));
	}
	private WebElement getPage(WebDriver driver, String url) {
		driver.get(url);
		return driver.findElement(By.xpath("/html"));	
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
}