package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class IsInstanceControl extends Control<IsInstance> {

	/**
	 * -730335751618952610L
	 */
	private static final long serialVersionUID = -730335751618952610L;

	@Override
	@XmlElement
	public IsInstance getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(IsInstance message) {
		super.setMessage(message);
	}
	
	public IsInstanceControl() {
		super();
	}
	public IsInstanceControl(IsInstance message, ControlListener parent) {
		super(message, parent);
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			IsInstance isInstance = IsInstance.class.cast(e.getMessage());
			
			if(isInstance.getClassName() == null) {
				throw new Error("IsInstanceControl.OnControlInitialized: ClassName property is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			IsInstance isInstance = IsInstance.class.cast(e.getMessage());
			
			put(Data.CLASS_NAME, isInstance.getClassName());
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.IS_INSTANCE_CONTROL_LOADED));
		}
	}
}