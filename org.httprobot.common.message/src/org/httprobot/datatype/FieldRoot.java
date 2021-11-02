package org.httprobot.datatype;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.DataType;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class FieldRoot extends DataType {

	/**
	 * -4698409081823244264L
	 */
	private static final long serialVersionUID = -4698409081823244264L;

	LinkedHashSet<Field> field;
	
	@XmlElement
	public LinkedHashSet<Field> getField() {
		return field;
	}
	public void setField(LinkedHashSet<Field> field) {
		this.field = field;
	}

	public FieldRoot() {
		super();
		field = new LinkedHashSet<Field>();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		FieldRoot fieldRoot = FieldRoot.class.cast(e.getSource());
		setField(fieldRoot.getField());
	}
}
