package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.StartsWith;
import org.httprobot.placeholder.string.StartsWithControl;

@XmlRootElement
public final class StartsWithParent 
	extends AbstractStringParent {

	/**
	 * 7695767707161722927L
	 */
	private static final long serialVersionUID = 7695767707161722927L;

	@Override
	@XmlElement
	public StartsWithControl getControl() {
		return (StartsWithControl) super.getControl();
	}
	
	public StartsWithParent() {
		super();
	}
	public StartsWithParent(StartsWith message, Parent parent) {
		super(message, StartsWithControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		if(!value.startsWith(getControl().getMessage().getValue())) {
			key.setValue(null);
		}
		return super.put(key, value);
	}
}