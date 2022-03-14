package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.operator.string.Trim;
import org.httprobot.operator.string.TrimControl;

@XmlRootElement
public final class TrimParent 
	extends AbstractStringParent<TrimControl> {

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
	
	public TrimParent() {
		super();
	}
	public TrimParent(Trim message, Listener parent) {
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