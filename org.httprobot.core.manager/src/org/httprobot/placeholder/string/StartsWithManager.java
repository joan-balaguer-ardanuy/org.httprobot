package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
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
	
	public StartsWithManager() {
		super();
	}
	public StartsWithManager(StartsWith message, ManagerListener parent) {
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