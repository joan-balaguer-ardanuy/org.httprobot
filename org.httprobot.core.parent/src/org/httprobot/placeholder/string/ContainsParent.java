package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.ContainsControl;

@XmlRootElement
public final class ContainsParent
	extends AbstractStringParent {

	/**
	 * 6954038560443161414L
	 */
	private static final long serialVersionUID = 6954038560443161414L;
	
	@Override
	@XmlElement
	public ContainsControl getControl() {
		return (ContainsControl) super.getControl();
	}
	
	public ContainsParent() {
		super();
	}
	public ContainsParent(Contains message, Parent parent) {
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