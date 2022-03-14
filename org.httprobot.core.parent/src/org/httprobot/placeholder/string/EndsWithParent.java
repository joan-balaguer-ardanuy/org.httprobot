package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.operator.string.EndsWith;
import org.httprobot.operator.string.EndsWithControl;

@XmlRootElement
public final class EndsWithParent
	extends AbstractStringParent<EndsWithControl> {

	/**
	 * 7738501434061695663L
	 */
	private static final long serialVersionUID = 7738501434061695663L;
	
	@Override
	@XmlElement
	public EndsWithControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(EndsWithControl control) {
		super.setControl(control);
	}
	
	public EndsWithParent() {
		super();
	}
	public EndsWithParent(EndsWith message, Listener parent) {
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
