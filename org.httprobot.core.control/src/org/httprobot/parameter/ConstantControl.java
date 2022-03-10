package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ConstantControl 
	extends AbstractControl<Constant> {

	/**
	 * 8308150241844524678L
	 */
	private static final long serialVersionUID = 8308150241844524678L;
	
	public ConstantControl() {
		super();	
	}
	public ConstantControl(Constant message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Constant constant = Constant.class.cast(e.getMessage());
			
			if(constant.getKey() == null || constant.getValue() == null) {
				throw new Error("ConstantControl.OnControlInitialized: Inconsistent Constant XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Constant constant = Constant.class.cast(e.getMessage());
			put(Data.KEY, constant.getKey());
			put(Data.VALUE, constant.getValue());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}
}