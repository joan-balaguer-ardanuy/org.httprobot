package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ServiceConnectionControl 
	extends AbstractControl<ServiceConnection> {

	/**
	 * 4177732778336124009L
	 */
	private static final long serialVersionUID = 4177732778336124009L;
	
	@Override
	@XmlElement
	public ServiceConnection getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ServiceConnection message) {
		super.setMessage(message);
	}
	
	public ServiceConnectionControl() {
		super();
	}
	public ServiceConnectionControl(ServiceConnection message) {
		super(message);
	}
	public ServiceConnectionControl(ServiceConnection message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast XML message
				ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getValue());
				// check XML message integrity
				if(serviceOptions.getQName() == null || serviceOptions.getURL() == null) {
					throw new Error("ServiceConnectionControl.OnEventReceived: Malformed ServiceConnection XML message control exception");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				// cast message
				ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getValue());
				
				if(serviceOptions.getQName() != null && serviceOptions.getURL() != null) {
					//Store data to current XML message control
					put(Data.Q_NAME, serviceOptions.getQName());
					put(Data.URL, serviceOptions.getURL());	
				} else {
					throw new Error("ServiceConnectionControl.OnEventReceived: Malformed ServiceConnection XML message control exception");
				}
			}
			break;
		default:
			break;
		}
	}
}