package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ParentListener;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class StartsWithParent 
	extends AbstractStringParent<StartsWithControl> {

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
	
	public StartsWithParent() {
		super();
	}
	public StartsWithParent(StartsWith message, ParentListener parent) {
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