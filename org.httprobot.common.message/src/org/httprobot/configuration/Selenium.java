package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.AbstractConfiguration;
import org.httprobot.BrowserVersion;
import org.httprobot.event.MessageEventArgs;
import org.openqa.selenium.WebDriver;

@XmlRootElement
public final class Selenium extends AbstractConfiguration {
	
	/**
	 * -7265402834372126641L
	 */
	private static final long serialVersionUID = -7265402834372126641L;
	
	private BrowserVersion browserVersion;
	private String driverProperty;
	private String driverPath;
	private Boolean allowImages;
	
	/**
	 * The Selenium's {@link WebDriver}.
	 */
	private WebDriver webDriver;
	
	@XmlElement
	public String getDriverProperty() {
		return driverProperty;
	}
	public void setDriverProperty(String driverProperty) {
		this.driverProperty = driverProperty;
	}
	@XmlElement(namespace = "http://org.httprobot")
	public BrowserVersion getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(BrowserVersion browserVersion) {
		this.browserVersion = browserVersion;
	}
	@XmlElement
	public String getDriverPath() {
		return driverPath;
	}
	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
	@XmlElement
	public Boolean getAllowImages() {
		return allowImages;
	}
	public void setAllowImages(Boolean allowImages) {
		this.allowImages = allowImages;
	}
	@XmlTransient
	public WebDriver getWebDriver() {
		return webDriver;
	}
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public Selenium() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		Selenium selenium = Selenium.class.cast(e.getSource());
		setDriverPath(selenium.getDriverPath());
		setDriverProperty(selenium.getDriverProperty());
		setBrowserVersion(selenium.getBrowserVersion());
		setAllowImages(selenium.getAllowImages());
	}
}