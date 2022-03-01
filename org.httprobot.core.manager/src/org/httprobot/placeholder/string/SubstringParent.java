package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ParentListener;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class SubstringParent
	extends AbstractStringParent<SubstringControl> {

	/**
	 * 7868954939148015302L
	 */
	private static final long serialVersionUID = 7868954939148015302L;

	@Override
	@XmlElement
	public SubstringControl getControl() {
		return super.getControl();
	}
	@Override
	public void setControl(SubstringControl control) {
		super.setControl(control);
	}
	
	public SubstringParent() {
		super();
	}
	public SubstringParent(Substring message, ParentListener parent) {
		super(message, SubstringControl.class, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		Substring message = getControl().getMessage();
		key.setValue(value.substring(message.getStartIndex(), message.getEndIndex()));
		return super.put(key, key.getValue().toString());
	}
}