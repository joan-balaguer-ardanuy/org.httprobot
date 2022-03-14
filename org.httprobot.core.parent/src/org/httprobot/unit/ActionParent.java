package org.httprobot.unit;

import java.io.StringWriter;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.httprobot.Constants;
import org.httprobot.Data;
import org.httprobot.ManagerEventType;
import org.httprobot.Listener;
import org.httprobot.Entry;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.operator.html.Element;
import org.httprobot.operator.html.ElementControl;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantParent;
import org.httprobot.placeholder.html.ElementParent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxDriver.Capability;
import org.openqa.selenium.interactions.Actions;

public class ActionParent
	extends Entry<HtmlPage, Set<HtmlPage>, ActionControl> {

	/**
	 * -6659121403717296708L
	 */
	private static final long serialVersionUID = -6659121403717296708L;

	WebLoaderParent webLoaderManager;
	Map<Constant, ConstantParent> constantManagers;
	ElementParent elementManager;
	
	DriverParent seleniumManager;
	
	HtmlPage currentOutput;

	Set<WebElement> clickableElements;
	
	public ActionParent() {
		super();
	}
	public ActionParent(Action message, Listener parent) {
		super(message, ActionControl.class, parent);
	}
	
	@Override
	public Set<HtmlPage> put(HtmlPage key, Set<HtmlPage> value) {
		WebDriver driver = getWebDriver();
		WebElement htmlElement;
		
		if(key == null) {
			String httpAddress = (String) getControl().get(Data.HTTP_ADDRESS);
			httpAddress = deParameterizeURL(httpAddress);
			htmlElement = getPage(driver, httpAddress);
			key = new HtmlPage(httpAddress, htmlElement.findElement(By.xpath("/html")).getAttribute("outerHTML"));
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
				htmlElement = getPage(driver, "data:text/html;charset=utf-8," + writer.toString());
			} catch (TransformerException ex) {
				ex.printStackTrace();
				return null;
			}
		}
		keySet().add(key);
		setKey(key);
		setValue(value);

		if (elementManager != null) {
			elementManager.put(htmlElement, new LinkedHashSet<WebElement>());

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
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource() instanceof ConstantParent) {
				ConstantParent constantManager = ConstantParent.class.cast(e.getSource());
				if(constantManagers.containsValue(constantManager)) {
					getConstants().put(constantManager.getKey(), constantManager.getValue());
				}
			} else if(e.getSource() instanceof DriverParent) {
				DriverParent seleniumManager = DriverParent.class.cast(e.getSource());
				seleniumManager.setValue(loadWebDriver(seleniumManager.getKey()));
			}
			break;
		case FINISHED: 
			if(e.getSource().equals(webLoaderManager)) {
				currentOutput = webLoaderManager.getValue();
				getValue().add(currentOutput);
				ManagerEvent(new ManagerEventArgs(this, currentOutput, ManagerEventType.ACTION_WEB_LOADED));
			}
			else if(e.getSource().equals(seleniumManager)) {
				setWebDriver(seleniumManager.getValue());
			}
			break;
		case ALL_PAGES_LOADED:
			getWebDriver().quit();
			setWebDriver(null);
			break;
		case NEW_ELEMENT:
			if(e.getSource() instanceof ElementParent) {
				ElementParent elementManager = ElementParent.class.cast(e.getSource());
				WebElement webElement = WebElement.class.cast(e.getValue());
				if((Boolean) elementManager.getControl().get(Data.CLICK)) {
					WebDriver driver = getWebDriver();
					Actions action = new Actions(driver);
					
					if(elementManager.getControl().get(Data.JAVASCRIPT) != null) {
						((JavascriptExecutor) driver).executeScript((String) elementManager.getControl().get(Data.JAVASCRIPT), webElement);
					}
					action.moveToElement(webElement).click().perform();
				} else if((Boolean) elementManager.getControl().get(Data.SAVE)) {
					clickableElements.add(webElement);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof WebLoaderControl) {
				WebLoader webLoader = WebLoaderControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.WEB_LOADER).equals(webLoader)) {
					webLoaderManager = new WebLoaderParent(webLoader, this);
					addChildManager(webLoaderManager);
				}
			} else if(e.getSource() instanceof ConstantControl) {
				Constant constant = ConstantControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.CONSTANT).equals(constant)) {
					ConstantParent constantManager = new ConstantParent(constant, this);
					constantManagers.put(constant, constantManager);
					addChildManager(constantManager);
				}
			} else if(e.getSource() instanceof ElementControl) {
				Element element = ElementControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.WEB_LOADER).equals(element)) {
					elementManager = new ElementParent(element, this);
					addChildManager(elementManager);
				}
			} else if(e.getSource() instanceof DriverControl) {
				Driver selenium = DriverControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.DRIVER).equals(selenium)) {
					seleniumManager = new DriverParent(selenium, this);
					addChildManager(seleniumManager);
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

		webLoaderManager.put(url, new HtmlPage(url, driver.findElement(By.xpath("/html")).getAttribute("outerHTML")));
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
	private WebDriver loadWebDriver(Driver selenium) {
		WebDriver webDriver;
		System.setProperty(selenium.getDriverProperty(), selenium.getDriverPath());
		switch (selenium.getBrowserVersion()) {
		case FIREFOX:
			webDriver = new FirefoxDriver(getFirefoxOptions(selenium.getAllowImages()));
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return webDriver;
		case CHROME:
			webDriver = new ChromeDriver(getChromeOptions(selenium.getAllowImages()));
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return webDriver;
		default:
			return null;
		}
	}
	private FirefoxOptions getFirefoxOptions(Boolean allowImages) {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference(Constants.FIREFOX_PERMISSION_IMAGE, allowImages ? 1 : 2);
		options.setProfile(profile);
		options.setCapability(Capability.PROFILE, profile);
		return options;
	}
	private ChromeOptions getChromeOptions(Boolean allowImages) {
		ChromeOptions options = new ChromeOptions();
		HashMap<String,Object> images = new HashMap<>();
		images.put(Constants.CHROME_PERMISSION_IMAGE, allowImages ? 1 : 2);
		HashMap<String,Object> preferences = new HashMap<>();
		preferences.put(Constants.CHROME_PROFILE_DEFAULT_CONTENT, images);
		options.setExperimentalOption(Constants.CHROME_PREFERENCES, preferences);
		return null;
	}
}