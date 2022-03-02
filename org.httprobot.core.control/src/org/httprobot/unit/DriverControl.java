package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Command;
import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class DriverControl extends Control<Driver> {

	/**
	 * 7011339833433807856L
	 */
	private static final long serialVersionUID = 7011339833433807856L;

	@Override
	@XmlElement
	public Driver getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Driver message) {
		super.setMessage(message);
	}
	
	public DriverControl() {
		super();
	}
	public DriverControl(Driver message, ControlListener parent) {
		super(message, parent);
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Driver driver = Driver.class.cast(e.getMessage());
			
			if(driver.getBrowserVersion() == null) {
				throw new Error("DriverControl.OnControlInitialized: BrowserVersion is missing.");
			}
			if(driver.getDriverPath() == null) {
				throw new Error("DriverControl.OnControlInitialized: driver path is missing.");
			}
			if(driver.getDriverProperty() == null) {
				throw new Error("DriverControl.OnControlInitialized: driver property is missing.");
			}
			if(driver.getAllowImages() == null) {
				throw new Error("DriverControl.OnControlInitialized: allow images is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Driver selenium = Driver.class.cast(e.getMessage());
			
			put(Data.BROWSER_VERSION, selenium.getBrowserVersion());
			put(Data.DRIVER_PATH, selenium.getDriverPath());
			put(Data.DRIVER_PROPERTY, selenium.getDriverProperty());
			put(Data.ALLOW_IMAGES, selenium.getAllowImages());
			
			CommandListenerEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}	
	}
}