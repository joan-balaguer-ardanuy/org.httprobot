package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.event.EventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionParent 
	extends ParentEntry<ServiceConnection, WebService> {

	/**
	 * 798195388983416568L
	 */
	private static final long serialVersionUID = 798195388983416568L;

	@Override
	@XmlElement
	public ServiceConnectionControl getControl() {
		return (ServiceConnectionControl) super.getControl();
	}
	
	public ServiceConnectionParent() {
		super();
	}
	public ServiceConnectionParent(ServiceConnection message, Parent parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		
	}
}