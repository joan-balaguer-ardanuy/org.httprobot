package org.httprobot.config;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class WebService extends AbstractConfig {

	/**
	 * -5928653934223911289L
	 */
	private static final long serialVersionUID = -5928653934223911289L;
	
	LinkedHashSet<ServiceConnection> serviceConnection;
	
	@XmlElement
	public LinkedHashSet<ServiceConnection> getServiceConnection() {
		return serviceConnection;
	}
	public void setServiceConnection(LinkedHashSet<ServiceConnection> serviceConnection) {
		this.serviceConnection = serviceConnection;
	}

	public WebService() {
		super();
	}
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		WebService webService = WebService.class.cast(e.getSource());
		setServiceConnection(webService.getServiceConnection());
	}
}
