package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;

@XmlRootElement
public final class SubstringParent
	extends AbstractStringParent {

	/**
	 * 7868954939148015302L
	 */
	private static final long serialVersionUID = 7868954939148015302L;

	@Override
	@XmlElement
	public SubstringControl getControl() {
		return (SubstringControl) super.getControl();
	}
	
	public SubstringParent() {
		super();
	}
	public SubstringParent(Substring message, Parent parent) {
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