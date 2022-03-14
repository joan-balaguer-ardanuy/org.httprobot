package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class FieldRefControl 
	extends AbstractControl<FieldRef> {

	/**
	 * 1646361821245878280L
	 */
	private static final long serialVersionUID = 1646361821245878280L;

	@XmlElement
	@Override
	public FieldRef getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(FieldRef message) {
		super.setMessage(message);
	}
	
	public FieldRefControl() {
		super();
	}
	public FieldRefControl(FieldRef message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				FieldRef fieldRef = FieldRef.class.cast(e.getValue());
				
				if(fieldRef.getName() == null || fieldRef.getDataType() == null) {
					throw new Error("FieldRefControl.OnControlInitialized: FieldRef XML message missing fields");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				FieldRef fieldRef = FieldRef.class.cast(e.getValue());
				
				if(fieldRef.getName() != null) {
					put(Data.FIELD_NAME, fieldRef.getName());
					put(Data.FIELD_TYPE, fieldRef.getDataType());
				} else {
					throw new Error("FieldRefControl.OnControlLoaded: name cannot be null.");
				}
			}
			break;
		default:
			break;
		}
	}
}