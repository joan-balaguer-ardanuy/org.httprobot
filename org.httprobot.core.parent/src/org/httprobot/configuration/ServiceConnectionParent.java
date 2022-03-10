package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.Entry;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionParent 
	extends Entry<ServiceConnection, WebService, ServiceConnectionControl> {

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
	
	public ServiceConnectionParent() {
		super();
	}
	public ServiceConnectionParent(ServiceConnection message, Listener parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}