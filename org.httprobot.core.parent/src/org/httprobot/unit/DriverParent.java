package org.httprobot.unit;

import org.httprobot.Listener;
import org.httprobot.Entry;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

public class DriverParent 
	extends Entry<Driver, WebDriver, DriverControl> {

	/**
	 * 7948356877406296858L
	 */
	private static final long serialVersionUID = 7948356877406296858L;
	
	public DriverParent() {
		super();
	}
	public DriverParent(Driver message, Listener parent) {
		super(message, DriverControl.class, parent);
	}

	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}