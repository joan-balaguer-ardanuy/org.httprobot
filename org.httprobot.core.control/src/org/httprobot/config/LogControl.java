package org.httprobot.config;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.event.ControlEventArgs;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public final class LogControl 
	extends Control<Log> {

	/**
	 * 7068308103907023010L
	 */
	private static final long serialVersionUID = 7068308103907023010L;
	
	@XmlElement
	@Override
	public Log getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Log message) {
		super.setMessage(message);
	}
	
	public LogControl() {
		super();
	}
	public LogControl(Log message, ControlListener parent) {
		super(message, parent);
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		
	}
}