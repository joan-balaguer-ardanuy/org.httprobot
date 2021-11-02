package org.httprobot.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ServiceUrl extends AbstractConfig {

	/**
	 * 8383249175803013880L
	 */
	private static final long serialVersionUID = 8383249175803013880L;
	
	String url;
	String protocol;
	String host;
	Integer port;
	String file;
	
	@XmlAttribute
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlAttribute
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	@XmlAttribute
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	@XmlAttribute
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	@XmlAttribute
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public ServiceUrl() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ServiceUrl serviceUrl = ServiceUrl.class.cast(e.getSource());
		setUrl(serviceUrl.getUrl());
		setProtocol(serviceUrl.getProtocol());
		setHost(serviceUrl.getHost());
		setPort(serviceUrl.getPort());
		setFile(serviceUrl.getFile());
	}
}
