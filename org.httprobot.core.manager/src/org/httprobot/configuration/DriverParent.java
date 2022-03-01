package org.httprobot.configuration;

import org.httprobot.ParentListener;
import org.httprobot.MappingParent;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

public class DriverParent 
	extends MappingParent<Driver, WebDriver, DriverControl> {

	/**
	 * 7948356877406296858L
	 */
	private static final long serialVersionUID = 7948356877406296858L;
	
	public DriverParent() {
		super();
	}
	public DriverParent(Driver message, ParentListener parent) {
		super(message, DriverControl.class, parent);
	}

	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}