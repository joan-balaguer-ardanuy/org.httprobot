package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.ToUpperCase;
import org.httprobot.placeholder.string.ToUpperCaseControl;

@XmlRootElement
public class ToUpperCaseParent
	extends AbstractStringParent {

	@Override
	@XmlElement
	public ToUpperCaseControl getControl() {
		return (ToUpperCaseControl) super.getControl();
	}
	
	/**
	 * -7874627263860439318L
	 */
	private static final long serialVersionUID = -7874627263860439318L;

	public ToUpperCaseParent() {
		super();
	}
	public ToUpperCaseParent(ToUpperCase message, Parent parent) {
		super(message, ToUpperCaseControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.toUpperCase());
		return super.put(key, key.getValue().toString());
	}
}