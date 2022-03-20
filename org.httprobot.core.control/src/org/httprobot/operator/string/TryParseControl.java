package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class TryParseControl
	extends AbstractStringControl {

	/**
	 * -5977456131566401187L
	 */
	private static final long serialVersionUID = -5977456131566401187L;
	
	@Override
	@XmlElement
	public TryParse getMessage() {
		return (TryParse) super.getMessage();
	}
	
	public TryParseControl() {
		super();
	}
	public TryParseControl(TryParse message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				TryParse tryParse = TryParse.class.cast(e.getValue());
				if(tryParse.getFieldType() == null) {
					throw new Error("TryParseControl.OnEventReceived: FieldType XML element message is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				TryParse tryParse = TryParse.class.cast(e.getValue());
				if(tryParse.getFieldType() != null) {
					put(Data.FIELD_TYPE, tryParse.getFieldType());
				}
			}
			break;
		default:
			break;
		}
	}
}