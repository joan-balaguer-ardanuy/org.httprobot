package org.httprobot.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Config;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class AppConfig extends Config {
	
	/**
	 * -7265402834372126641L
	 */
	private static final long serialVersionUID = -7265402834372126641L;
	
	String configFilePath;
	String logFilePath;
	String xmlManagerFilePath;
	String inetManagerFilePath;
	String dataManagerFilePath;
	String uiManagerFilePath;
	
	private ServiceConnection serviceConnection;
	
	@XmlElement
	public String getConfigFilePath() {
		return configFilePath;
	}
	public void setConfigFilePath(String sourceUrl) {
		this.configFilePath = sourceUrl;
	}

	@XmlElement
	public String getLogFilePath() {
		return logFilePath;
	}
	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	@XmlElement
	public String getXmlManagerFilePath() {
		return xmlManagerFilePath;
	}
	public void setXmlManagerFilePath(String xmlManagerFilePath) {
		this.xmlManagerFilePath = xmlManagerFilePath;
	}

	@XmlElement
	public String getInetManagerFilePath() {
		return inetManagerFilePath;
	}
	public void setInetManagerFilePath(String inetManagerFilePath) {
		this.inetManagerFilePath = inetManagerFilePath;
	}

	@XmlElement
	public String getDataManagerFilePath() {
		return dataManagerFilePath;
	}
	public void setDataManagerFilePath(String dataManagerFilePath) {
		this.dataManagerFilePath = dataManagerFilePath;
	}

	@XmlElement
	public String getUiManagerFilePath() {
		return uiManagerFilePath;
	}
	public void setUiManagerFilePath(String uiManagerFilePath) {
		this.uiManagerFilePath = uiManagerFilePath;
	}

	@XmlElement
	public ServiceConnection getServiceConnection() {
		return serviceConnection;
	}
	public void setServiceConnection(ServiceConnection serviceConnection) {
		this.serviceConnection = serviceConnection;
	}

	public AppConfig() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		AppConfig config = AppConfig.class.cast(e.getSource());
		setConfigFilePath(config.getConfigFilePath());
		setLogFilePath(config.getLogFilePath());
		setXmlManagerFilePath(config.getXmlManagerFilePath());
		setInetManagerFilePath(config.getInetManagerFilePath());
		setDataManagerFilePath(config.getDataManagerFilePath());
		setUiManagerFilePath(config.getUiManagerFilePath());
		setServiceConnection(config.getServiceConnection());
	}
}
