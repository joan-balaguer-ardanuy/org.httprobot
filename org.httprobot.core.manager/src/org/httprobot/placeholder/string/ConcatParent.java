package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class ConcatManager
	extends AbstractStringManager<ConcatControl> {

	/**
	 * -2674949404675117218L
	 */
	private static final long serialVersionUID = -2674949404675117218L;

	@Override
	@XmlElement
	public ConcatControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ConcatControl control) {
		super.setControl(control);
	}
	
	public ConcatManager() {
		super();
	}
	public ConcatManager(Concat message, ManagerListener parent) {
		super(message, ConcatControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.concat(getControl().getMessage().getValue()));
		return super.put(key, key.getValue().toString());
	}
}