package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Command;
import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ServiceConnectionControl 
	extends Control<ServiceConnection> {

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
	public ServiceConnectionControl(ServiceConnection message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this))
		{
			//Cast XML message
			ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getMessage());
			//Check XML message integrity
			if(serviceOptions.getQName() == null || serviceOptions.getURL() == null) {
				throw new Error("ServiceConnectionControl.OnControlInitialized: Malformed ServiceConnection XML message control exception");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this))
		{
			//Cast message
			ServiceConnection serviceOptions = ServiceConnection.class.cast(e.getMessage());
			
			if(serviceOptions.getQName() != null && serviceOptions.getURL() != null) {
				//Store data to current XML message control
				put(Data.Q_NAME, serviceOptions.getQName());
				put(Data.URL, serviceOptions.getURL());	
				//Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			}
			else
			{
				throw new Error("ServiceConnectionControl.OnControlLoaded: Malformed ServiceConnection XML message control exception");
			}
		}
	}
}