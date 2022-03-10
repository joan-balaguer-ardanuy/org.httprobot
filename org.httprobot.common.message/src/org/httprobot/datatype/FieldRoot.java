package org.httprobot.datatype;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.XML;

@XmlRootElement
public final class FieldRoot extends XML {

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
}
