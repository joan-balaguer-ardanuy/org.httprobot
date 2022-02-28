package org.httprobot.configuration;

import org.httprobot.ManagerListener;
import org.httprobot.MappingParent;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

public class SeleniumParent 
	extends MappingParent<Selenium, WebDriver, SeleniumControl> {

	/**
	 * 7948356877406296858L
	 */
	private static final long serialVersionUID = 7948356877406296858L;
	
	public SeleniumParent() {
		super();
	}
	public SeleniumParent(Selenium message, ManagerListener parent) {
		super(message, SeleniumControl.class, parent);
	}

	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}