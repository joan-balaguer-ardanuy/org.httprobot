package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.operator.string.ToUpperCase;

@XmlRootElement
public class ToUpperCaseParent
	extends AbstractStringParent<ToUpperCaseControl> {

	@Override
	@XmlElement
	public ToUpperCaseControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ToUpperCaseControl control) {
		super.setControl(control);
	}
	
	/**
	 * -7874627263860439318L
	 */
	private static final long serialVersionUID = -7874627263860439318L;

	public ToUpperCaseParent() {
		super();
	}
	public ToUpperCaseParent(ToUpperCase message, Listener parent) {
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