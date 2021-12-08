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
public final class SeleniumControl extends Control<Selenium> {

	/**
	 * 7011339833433807856L
	 */
	private static final long serialVersionUID = 7011339833433807856L;

	@Override
	@XmlElement
	public Selenium getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Selenium message) {
		super.setMessage(message);
	}
	
	public SeleniumControl() {
		super();
	}
	public SeleniumControl(Selenium message, ControlListener parent) {
		super(message, parent);
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Selenium selenium = Selenium.class.cast(e.getMessage());
			
			if(selenium.getBrowserVersion() == null) {
				throw new Error("SeleniumControl.OnControlInitialized: BrowserVersion is missing.");
			}
			if(selenium.getDriverPath() == null) {
				throw new Error("SeleniumControl.OnControlInitialized: driver path is missing.");
			}
			if(selenium.getDriverProperty() == null) {
				throw new Error("SeleniumControl.OnControlInitialized: driver property is missing.");
			}
			if(selenium.getAllowImages() == null) {
				throw new Error("SeleniumControl.OnControlInitialized: allow images is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Selenium selenium = Selenium.class.cast(e.getMessage());
			
			put(Data.BROWSER_VERSION, selenium.getBrowserVersion());
			put(Data.DRIVER_PATH, selenium.getDriverPath());
			put(Data.DRIVER_PROPERTY, selenium.getDriverProperty());
			put(Data.ALLOW_IMAGES, selenium.getAllowImages());
			
			CommandLineEvent(new CommandEventArgs(this, Command.SELENIUM_CONTROL_LOADED));
		}	
	}
}