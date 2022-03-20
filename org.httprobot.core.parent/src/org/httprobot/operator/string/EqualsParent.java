package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class EqualsParent 
	extends AbstractStringParent {

	/**
	 * -481593600327061747L
	 */
	private static final long serialVersionUID = -481593600327061747L;

	@Override
	@XmlElement
	public EqualsControl getControl() {
		return (EqualsControl) super.getControl();
	}
	
	public EqualsParent() {
		super();
	}
	public EqualsParent(Equals message, Parent parent) {
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