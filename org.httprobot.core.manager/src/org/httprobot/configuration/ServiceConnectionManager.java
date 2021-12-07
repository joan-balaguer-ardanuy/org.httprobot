package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.ManagerListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionManager 
	extends Manager<ServiceConnection, WebService, ServiceConnectionControl> {

	/**
	 * 798195388983416568L
	 */
	private static final long serialVersionUID = 798195388983416568L;

	@Override
	@XmlElement
	public ServiceConnectionControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ServiceConnectionControl control) {
		super.setControl(control);
	}
	
	public ServiceConnectionManager() {
		super();
	}
	public ServiceConnectionManager(ServiceConnection message, ManagerListener parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public WebService put(ServiceConnection key, WebService value) {
		return super.put(key, value);
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getMessage() instanceof ServiceConnection) {
			ServiceConnection message = ServiceConnection.class.cast(e.getMessage());
			put(message, new WebService(message.getURL(), message.getQName()));
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}