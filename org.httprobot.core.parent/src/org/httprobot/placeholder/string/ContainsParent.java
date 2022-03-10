package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.operator.string.Contains;

@XmlRootElement
public final class ContainsParent
	extends AbstractStringParent<ContainsControl> {

	/**
	 * 6954038560443161414L
	 */
	private static final long serialVersionUID = 6954038560443161414L;
	
	@Override
	@XmlElement
	public ContainsControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(ContainsControl control) {
		super.setControl(control);
	}
	
	public ContainsParent() {
		super();
	}
	public ContainsParent(Contains message, Listener parent) {
		super(message, ContainsControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		if(!value.contains(getControl().getMessage().getValue())) {
			key.setValue(null);
		}
		return super.put(key, value);
	}
}