package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfiguration;
import org.httprobot.BrowserVersion;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Application extends AbstractConfiguration {
	
	/**
	 * -7265402834372126641L
	 */
	private static final long serialVersionUID = -7265402834372126641L;
	
	private BrowserVersion browserVersion;
	private String driverProperty;
	private String driverPath;
	private ServiceConnection serviceConnection;
	
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
	public ServiceConnection getServiceConnection() {
		return serviceConnection;
	}
	public void setServiceConnection(ServiceConnection serviceConnection) {
		this.serviceConnection = serviceConnection;
	}

	public Application() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		Application config = Application.class.cast(e.getSource());
		setDriverPath(config.getDriverPath());
		setServiceConnection(config.getServiceConnection());
	}
}