package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.AbstractConfiguration;
import org.httprobot.BrowserVersion;
import org.httprobot.event.MessageEventArgs;

/**
 * Selenium abstract configuration XML message class.
 * Inherits {@link AbstractConfiguration}. It contains all the 
 * necessary data to instance an {@link org.openqa.selenium.WebDriver}.
 * It encapsulates a {@link BrowserVersion} to select the programatic navigator,
 * the driver property to set the used driver on the {@link System},
 * the driver path to set the value of driver property and
 * allowImages to determine if you want to load images on selenium's
 * navigator.
 * @author joan
 *
 */
@XmlRootElement
public final class Selenium
	extends AbstractConfiguration {
	
	/**
	 * -7265402834372126641L
	 */
	private static final long serialVersionUID = -7265402834372126641L;
	
	/**
	 * The browser version property.
	 */
	private BrowserVersion browserVersion;
	/**
	 * The driver property.
	 */
	private String driverProperty;
	/**
	 * The driver path property.
	 */
	private String driverPath;
	/**
	 * The allow images property.
	 */
	private Boolean allowImages;
	
	/**
	 * Returns the driver property.
	 * "webdriver.chrome.driver" for Chrome navigator.
	 * "webdriver.gecko.driver" for Firefox navigator.
	 * @return the driver property.
	 */
	@XmlElement
	public String getDriverProperty() {
		return driverProperty;
	}
	/**
	 * Sets the driver property.
	 * "webdriver.chrome.driver" for Chrome navigator.
	 * "webdriver.gecko.driver" for Firefox navigator.
	 * @param driverProperty the driver property.
	 */
	public void setDriverProperty(String driverProperty) {
		this.driverProperty = driverProperty;
	}
	/**
	 * Returns the used {@link BrowserVersion} .
	 * @return the {@link BrowserVersion} used.
	 */
	@XmlElement(namespace = "http://org.httprobot")
	public BrowserVersion getBrowserVersion() {
		return browserVersion;
	}
	/**
	 * Sets the {@link BrowserVersion} used.
	 * @param browserVersion the {@link BrowserVersion} used.
	 */
	public void setBrowserVersion(BrowserVersion browserVersion) {
		this.browserVersion = browserVersion;
	}
	/**
	 * Returns the path of the used driver.
	 * @return the path of the used driver.
	 */
	@XmlElement
	public String getDriverPath() {
		return driverPath;
	}
	/**
	 * Sets the path of the used driver.
	 * @param driverPath the path of the used driver.
	 */
	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
	/**
	 * Returns the allow images property.
	 * @return the allow images property.
	 */
	@XmlElement
	public Boolean getAllowImages() {
		return allowImages;
	}
	/**
	 * Sets the allow images property.
	 * @param allowImages the allow images property.
	 */
	public void setAllowImages(Boolean allowImages) {
		this.allowImages = allowImages;
	}
	
	/**
	 * {@link Selenium} default class constructor.
	 */
	public Selenium() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		Selenium selenium = Selenium.class.cast(e.getSource());
		// Set properties
		setDriverPath(selenium.getDriverPath());
		setDriverProperty(selenium.getDriverProperty());
		setBrowserVersion(selenium.getBrowserVersion());
		setAllowImages(selenium.getAllowImages());
	}
}