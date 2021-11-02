package org.httprobot.config;

import java.net.URL;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import org.httprobot.Config;
import org.httprobot.config.adapter.ServiceQNameAdapter;
import org.httprobot.config.adapter.ServiceUrlAdapter;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ServiceConnection extends Config {

	/**
	 * 355219426287742894L
	 */
	private static final long serialVersionUID = 355219426287742894L;

	private QName qName;
	private URL url;
	
	@XmlJavaTypeAdapter(value = ServiceQNameAdapter.class)
	public QName getQName() {
		return qName;
	}
	public void setQName(QName qName) {
		this.qName = qName;
	}
	
	@XmlJavaTypeAdapter(value = ServiceUrlAdapter.class)
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}

	public ServiceConnection() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ServiceConnection serviceConnection = ServiceConnection.class.cast(e.getSource());
		
		setQName(serviceConnection.getQName());
		setUrl(serviceConnection.getUrl());
	}
}
