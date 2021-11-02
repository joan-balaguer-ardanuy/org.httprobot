package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class StartsWithManager 
	extends AbstractStringManager<StartsWithControl> {

	/**
	 * 7695767707161722927L
	 */
	private static final long serialVersionUID = 7695767707161722927L;

	@Override
	@XmlElement
	public StartsWithControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(StartsWithControl control) {
		super.setControl(control);
	}
	
	@Override
	public InputField setValue(InputField value) {
		if(!getKey().startsWith(getControl().getMessage().getValue())) {
			value.setValue(null);
		}
		return super.setValue(value);
	}
	
	public StartsWithManager() {
		super();
	}
	public StartsWithManager(StartsWith message, Manager<?> parent) {
		super(message, StartsWithControl.class, parent);
	}
}