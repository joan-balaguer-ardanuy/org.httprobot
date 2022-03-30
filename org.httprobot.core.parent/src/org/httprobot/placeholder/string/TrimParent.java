package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.TrimControl;

@XmlRootElement
public final class TrimParent 
	extends AbstractStringParent {

	/**
	 * 8160161481205354596L
	 */
	private static final long serialVersionUID = 8160161481205354596L;

	@Override
	@XmlElement
	public TrimControl getControl() {
		return (TrimControl) super.getControl();
	}
	
	public TrimParent() {
		super();
	}
	public TrimParent(Trim message, Parent parent) {
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