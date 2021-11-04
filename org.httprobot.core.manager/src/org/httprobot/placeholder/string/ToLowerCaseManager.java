package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class ToLowerCaseManager 
	extends AbstractStringManager<ToLowerCaseControl> {

	/**
	 * -912172374068787372L
	 */
	private static final long serialVersionUID = -912172374068787372L;

	@Override
	@XmlElement
	public ToLowerCaseControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ToLowerCaseControl control) {
		super.setControl(control);
	}
	
	public ToLowerCaseManager() {
		super();
	}
	public ToLowerCaseManager(ToLowerCase message, Manager<?> parent) {
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