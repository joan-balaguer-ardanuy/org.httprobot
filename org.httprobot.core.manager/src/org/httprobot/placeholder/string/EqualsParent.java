package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class EqualsParent 
	extends AbstractStringParent<EqualsControl> {

	/**
	 * -481593600327061747L
	 */
	private static final long serialVersionUID = -481593600327061747L;

	@Override
	@XmlElement
	public EqualsControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(EqualsControl control) {
		super.setControl(control);
	}
	
	public EqualsParent() {
		super();
	}
	public EqualsParent(Equals message, ManagerListener parent) {
		super(message, EqualsControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		if(!value.equals(getControl().getMessage().getValue())) {
			key.setValue(null);
		}
		return super.put(key, value);
	}
}