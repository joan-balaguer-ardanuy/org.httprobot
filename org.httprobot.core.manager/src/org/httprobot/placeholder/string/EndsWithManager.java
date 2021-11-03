package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class EndsWithManager
	extends AbstractStringManager<EndsWithControl> {

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
	
	@Override
	public InputField setValue(InputField value) {
		if(!getKey().endsWith(getControl().getMessage().getValue())) {
			value.setValue(null);
		}
		return super.setValue(value);
	}
	
	public EndsWithManager() {
		super();
	}
	public EndsWithManager(EndsWith message, Manager<?> parent) {
		super(message, EndsWithControl.class, parent);
	}
}
