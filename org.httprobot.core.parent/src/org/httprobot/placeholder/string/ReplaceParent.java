package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.ReplaceControl;

@XmlRootElement
public final class ReplaceParent
	extends AbstractStringParent {

	/**
	 * -4082786732248275675L
	 */
	private static final long serialVersionUID = -4082786732248275675L;

	@Override
	@XmlElement
	public ReplaceControl getControl() {
		return (ReplaceControl) super.getControl();
	}
	
	public ReplaceParent() {
		super();
	}
	public ReplaceParent(Replace message, Parent parent) {
		super(message, ReplaceControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		Replace message = getControl().getMessage();
		key.setValue(value.replace(message.getOldString(), message.getNewString()));
		return super.put(key, key.getValue().toString());
	}
}