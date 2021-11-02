package org.httprobot.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class SessionControl 
	extends Control<Session> {

	/**
	 * -3138445767041159575L
	 */
	private static final long serialVersionUID = -3138445767041159575L;
	
	@Override
	@XmlElement
	public Session getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Session message) {
		super.setMessage(message);
	}
	
	public SessionControl() {
		super();
	}
	public SessionControl(Session message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		
	}
}
