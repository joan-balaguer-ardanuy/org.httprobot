package org.httprobot.unit;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.html.ElementControl;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;

@XmlRootElement
public final class ActionControl
	extends AbstractControl<Action> {

	/**
	 * -3447883786428247992L
	 */
	private static final long serialVersionUID = -3447883786428247992L;
	
	LinkedHashSet<ConstantControl> constant;
	WebLoaderControl webLoaderControl;
	ElementControl elementControl;
	DriverControl driverControl;
	
	@Override
	@XmlElement
	public Action getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Action message) {
		super.setMessage(message);
	}
	@XmlElement
	public WebLoaderControl getPaginatorControl() {
		return webLoaderControl;
	}
	public void setPaginatorControl(WebLoaderControl paginatorControl) {
		this.webLoaderControl = paginatorControl;
	}
	@XmlElement
	public LinkedHashSet<ConstantControl> getConstantControl() {
		return constant;
	}
	public void setConstantControl(LinkedHashSet<ConstantControl> constant) {
		this.constant = constant;
	}
	@XmlElement
	public ElementControl getElementControl() {
		return elementControl;
	}
	public void setElementControl(ElementControl elementControl) {
		this.elementControl = elementControl;
	}
	@XmlElement
	public DriverControl getDriverControl() {
		return driverControl;
	}
	public void setDriverControl(DriverControl driverControl) {
		this.driverControl = driverControl;
	}
	@XmlElement
	public WebLoaderControl getWebLoaderControl() {
		return webLoaderControl;
	}
	public void setWebLoaderControl(WebLoaderControl webLoaderControl) {
		this.webLoaderControl = webLoaderControl;
	}
	
	public ActionControl() {
		super();
	}
	public ActionControl(Action message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				Action action = Action.class.cast(e.getValue());

				constant = new LinkedHashSet<ConstantControl>();

				if (action.getClearQuery() == null) {
					throw new Error("ActionControl.OnEventReceived: clearQuery XML message attribute expected.");
				}
				if (action.getUrl() == null) {
					throw new Error("ActionControl.OnEventReceived: URL XML message element expected.");
				}
				if(action.getElement() != null) {
					new ElementControl(action.getElement(), this);
				} else {
					throw new Error("ActionControl.OnEventReceived: Element message expected.");
				}
				if(action.getDriver() != null) {
					new DriverControl(action.getDriver(), this);
				} else {
					throw new Error("ActionControl.OnEventReceived: Driver message expected.");
				}
				for (Constant constant : action.getConstant()) {
					new ConstantControl(constant, this);
				}
			} else if (e.getSource() instanceof ConstantControl) {
				ConstantControl childConstant = ConstantControl.class.cast(e.getSource());
				constant.add(childConstant);
				addChild(childConstant);
			} else if(e.getSource() instanceof ElementControl) {
				elementControl = ElementControl.class.cast(e.getSource());
				addChild(elementControl);
			} else if(e.getSource() instanceof WebLoaderControl) {
				webLoaderControl = WebLoaderControl.class.cast(e.getSource());
				addChild(webLoaderControl);
			} else if(e.getSource() instanceof DriverControl) {
				driverControl = DriverControl.class.cast(e.getSource());
				addChild(driverControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				Action action = Action.class.cast(e.getValue());
				//Set data
				put(Data.CLEAR_QUERY, action.getClearQuery());
				put(Data.URL, action.getUrl());
				put(Data.METHOD, action.getMethod());
			} else if (e.getSource() instanceof ElementControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.ELEMENT, e.getValue());
				}
			} else if (e.getSource() instanceof DriverControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.DRIVER, e.getValue());
				}
			} else if (e.getSource() instanceof ConstantControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONSTANT, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}