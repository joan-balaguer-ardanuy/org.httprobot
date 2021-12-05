package org.httprobot.configuration;

import java.net.URL;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import org.httprobot.AbstractConfiguration;
import org.httprobot.configuration.adapter.ServiceQNameAdapter;
import org.httprobot.configuration.adapter.ServiceUrlAdapter;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ServiceConnection extends AbstractConfiguration {

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
	public URL getURL() {
		return url;
	}
	public void setURL(URL url) {
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
		setURL(serviceConnection.getURL());
	}
}
