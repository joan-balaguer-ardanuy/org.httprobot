package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class ReplaceManager
	extends AbstractStringManager<ReplaceControl> {

	/**
	 * -4082786732248275675L
	 */
	private static final long serialVersionUID = -4082786732248275675L;

	@Override
	@XmlElement
	public ReplaceControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ReplaceControl control) {
		super.setControl(control);
	}
	
	public ReplaceManager() {
		super();
	}
	public ReplaceManager(Replace message, Manager<?> parent) {
		super(message, ReplaceControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		Replace message = getControl().getMessage();
		key.setValue(value.replace(message.getOldCharacter(), message.getNewCharacter()));
		return super.put(key, key.getValue().toString());
	}
}