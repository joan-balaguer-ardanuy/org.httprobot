package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class DriverControl extends AbstractControl<Driver> {

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
	public DriverControl(Driver message, Control parent) {
		super(message, parent);
	}

	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Driver driver = Driver.class.cast(e.getValue());
				
				if(driver.getBrowserVersion() == null) {
					throw new Error("DriverControl.OnEventReceived: BrowserVersion is missing.");
				}
				if(driver.getDriverPath() == null) {
					throw new Error("DriverControl.OnEventReceived: driver path is missing.");
				}
				if(driver.getDriverProperty() == null) {
					throw new Error("DriverControl.OnEventReceived: driver property is missing.");
				}
				if(driver.getAllowImages() == null) {
					throw new Error("DriverControl.OnEventReceived: allow images is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				Driver selenium = Driver.class.cast(e.getValue());
				
				put(Data.BROWSER_VERSION, selenium.getBrowserVersion());
				put(Data.DRIVER_PATH, selenium.getDriverPath());
				put(Data.DRIVER_PROPERTY, selenium.getDriverProperty());
				put(Data.ALLOW_IMAGES, selenium.getAllowImages());
			}
			break;
		default:
			break;
		}
	}
}