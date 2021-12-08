package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.EntryManager;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

public class SeleniumManager extends EntryManager<Selenium, WebDriver, SeleniumControl> {

	/**
	 * 7948356877406296858L
	 */
	private static final long serialVersionUID = 7948356877406296858L;

	@Override
	@XmlElement
	public Selenium getKey() {
		return getControl().getMessage();
	}
	
	public SeleniumManager() {
		super();
	}
	public SeleniumManager(Selenium message, ManagerListener parent) {
		super(message, SeleniumControl.class, parent);
	}

	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}
