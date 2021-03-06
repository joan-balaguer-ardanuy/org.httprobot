package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.EndsWith;
import org.httprobot.placeholder.string.EndsWithControl;

@XmlRootElement
public final class EndsWithParent
	extends AbstractStringParent {

	/**
	 * 7738501434061695663L
	 */
	private static final long serialVersionUID = 7738501434061695663L;
	
	@Override
	@XmlElement
	public EndsWithControl getControl() {
		return (EndsWithControl) super.getControl();
	}
	
	public EndsWithParent() {
		super();
	}
	public EndsWithParent(EndsWith message, Parent parent) {
		super(message, EndsWithControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		if(!value.endsWith(getControl().getMessage().getValue())) {
			key.setValue(null);
		}
		return super.put(key, value);
	}
}
