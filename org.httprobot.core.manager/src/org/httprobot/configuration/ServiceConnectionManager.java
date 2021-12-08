package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.EntryManager;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionManager 
	extends EntryManager<ServiceConnection, WebService, ServiceConnectionControl> {

	/**
	 * 798195388983416568L
	 */
	private static final long serialVersionUID = 798195388983416568L;

	@Override
	public ServiceConnection getKey() {
		return getControl().getMessage();
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
	public ServiceConnectionManager(ServiceConnection message, ManagerListener parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}