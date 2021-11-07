package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class TrimManager 
	extends AbstractStringManager<TrimControl> {

	/**
	 * 8160161481205354596L
	 */
	private static final long serialVersionUID = 8160161481205354596L;

	@Override
	@XmlElement
	public TrimControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(TrimControl control) {
		super.setControl(control);
	}
	
	public TrimManager() {
		super();
	}
	public TrimManager(Trim message, Manager<?> parent) {
		super(message, TrimControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.trim());
		return super.put(key, key.getValue().toString());
	}
}