package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.ToLowerCase;
import org.httprobot.placeholder.string.ToLowerCaseControl;

@XmlRootElement
public final class ToLowerCaseParent 
	extends AbstractStringParent {

	/**
	 * -912172374068787372L
	 */
	private static final long serialVersionUID = -912172374068787372L;

	@Override
	@XmlElement
	public ToLowerCaseControl getControl() {
		return (ToLowerCaseControl) super.getControl();
	}
	
	public ToLowerCaseParent() {
		super();
	}
	public ToLowerCaseParent(ToLowerCase message, Parent parent) {
		super(message, ToLowerCaseControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.toLowerCase());
		return super.put(key, key.getValue().toString());
	}
}