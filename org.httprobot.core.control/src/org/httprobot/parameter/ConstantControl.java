package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

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
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Constant constant = Constant.class.cast(e.getValue());
				
				if(constant.getKey() == null || constant.getValue() == null) {
					throw new Error("ConstantControl.OnEventReceived: Inconsistent Constant XML message.");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				Constant constant = Constant.class.cast(e.getValue());
				put(Data.KEY, constant.getKey());
				put(Data.VALUE, constant.getValue());
			}	
			break;
		default:
			break;
		}
	}
}