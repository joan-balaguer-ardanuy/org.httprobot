package org.httprobot.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Enums.ManagerEventType;
import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionManager 
	extends Manager<ServiceConnectionControl> 
		implements java.util.Map.Entry<ServiceConnection, WebService> {

	/**
	 * 798195388983416568L
	 */
	private static final long serialVersionUID = 798195388983416568L;

	WebService value;
	
	@Override
	public ServiceConnection getKey() {
		return getControl().getMessage();
	}
	@Override
	public WebService getValue() {
		return value;
	}
	@Override
	public WebService setValue(WebService value) {
		WebService oldValue = this.value;
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
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
	public ServiceConnectionManager(ServiceConnection message, Manager<?> parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getMessage() instanceof ServiceConnection) {
			ServiceConnection message = ServiceConnection.class.cast(e.getMessage());
			setValue(new WebService(message.getUrl(), message.getQName()));
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}