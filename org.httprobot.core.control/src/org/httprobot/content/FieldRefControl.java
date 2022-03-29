package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.event.EventArgs;

/**
 * {@link FieldRef} {@link XML} {@link Control} class.
 * @author joan
 *
 */
@XmlRootElement
public final class FieldRefControl 
	extends AbstractControl {

	/**
	 * 1646361821245878280L
	 */
	private static final long serialVersionUID = 1646361821245878280L;

	@XmlElement
	@Override
	public FieldRef getMessage() {
		return (FieldRef) super.getMessage();
	}
	
	/**
	 * {@link FieldRefControl} message default class constructor.
	 */
	public FieldRefControl() {
		super();
	}
	/**
	 * {@link FieldRefControl} message class constructor.
	 * @param message {@link FieldRef} the message
	 * @param parent {@link Control} the parent instance
	 */
	public FieldRefControl(FieldRef message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast source
				FieldRef fieldRef = (FieldRef) e.getValue();
				// check message integrity
				if(fieldRef.getName() == null || fieldRef.getDataType() == null) {
					throw new Error("FieldRefControl.OnEventReceived: FieldRef XML message missing fields");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				// cast source
				FieldRef fieldRef = (FieldRef) e.getValue();
				// set data
				put(Data.FIELD_NAME, fieldRef.getName());
				put(Data.FIELD_TYPE, fieldRef.getDataType());
			}
			break;
		default:
			break;
		}
	}
}