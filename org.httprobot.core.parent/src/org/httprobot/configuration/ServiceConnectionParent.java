package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ParentListener;
import org.httprobot.MappingParent;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

@XmlRootElement
public final class ServiceConnectionParent 
	extends MappingParent<ServiceConnection, WebService, ServiceConnectionControl> {

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
	public ServiceConnectionParent(ServiceConnection message, ParentListener parent) {
		super(message, ServiceConnectionControl.class, parent);
	}
	
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}