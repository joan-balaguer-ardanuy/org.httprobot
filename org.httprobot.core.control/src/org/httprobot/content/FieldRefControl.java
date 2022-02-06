package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class FieldRefControl 
	extends Control<FieldRef> {

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
		setMessage(new FieldRef());
	}
	public FieldRefControl(FieldRef message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
			
			if(fieldRef.getName() == null || fieldRef.getDataType() == null) {
				throw new Error("FieldRefControl.OnControlInitialized: FieldRef XML message missing fields");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			FieldRef fieldRef = FieldRef.class.cast(e.getMessage());
			
			if(fieldRef.getUuid() != null) {
				put(Data.FIELD_NAME, fieldRef.getName());
				put(Data.FIELD_TYPE, fieldRef.getDataType());
				//Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.FIELD_REF_CONTROL_LOADED));
			}
			else {
				throw new Error("FieldRefControl.OnControlLoaded: UUID cannot be null.");
			}
		}
	}
}