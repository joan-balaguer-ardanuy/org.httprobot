package org.httprobot.configuration;

import java.time.Duration;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.AbstractConfiguration;
import org.httprobot.BrowserVersion;
import org.httprobot.Constants;
import org.httprobot.event.MessageEventArgs;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.Capability;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

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
	public void loadWebDriver() {
		System.setProperty(getDriverProperty(), getDriverPath());
		switch (getBrowserVersion()) {
		case FIREFOX:
			webDriver = new FirefoxDriver(getFirefoxOptions(getAllowImages()));
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		case CHROME:
			webDriver = new ChromeDriver(getChromeOptions(getAllowImages()));
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		default:
			break;
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