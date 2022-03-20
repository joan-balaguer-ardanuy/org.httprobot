package org.httprobot.unit;

import org.httprobot.Control;
import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.openqa.selenium.WebDriver;

public class DriverParent 
	extends ParentEntry<Driver, WebDriver> {

	/**
	 * 7948356877406296858L
	 */
	private static final long serialVersionUID = 7948356877406296858L;
	
	@Override
	public Control getControl() {
		return (DriverControl) super.getControl();
	}
	
	public DriverParent() {
		super();
	}
	public DriverParent(Driver message, Parent parent) {
		super(message, DriverControl.class, parent);
	}
}