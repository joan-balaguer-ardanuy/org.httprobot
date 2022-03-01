package org.httprobot.placeholder.string;

import org.httprobot.Control;
import org.httprobot.ParentListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.AbstractString;
import org.httprobot.placeholder.AbstractPlaceholderParent;

public abstract class AbstractStringParent<T extends Control<?>>
	extends AbstractPlaceholderParent<InputField, String,T> {

	/**
	 * 9016980694923790707L
	 */
	private static final long serialVersionUID = 9016980694923790707L;
	
	public AbstractStringParent() {
		super();
	}
	public AbstractStringParent(AbstractString message, Class<T> type, ParentListener parent) {
		super(message, type, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		if (key.getValue() != null && hasChildManagers()) {
			if(concatParent != null) {
				concatParent.put(key, value);
			} else if (containsParent != null) {
				containsParent.put(key, value);
			} else if (endsWithParent != null) {
				endsWithParent.put(key, value);
			} else if (equalsParent != null) {
				equalsParent.put(key, value);
			} else if (replaceParent != null) {
				replaceParent.put(key, value);
			} else if (replaceParent != null) {
				replaceParent.put(key, value);
			} else if (startsWithParent != null) {
				startsWithParent.put(key, value);
			} else if (substringParent != null) {
				substringParent.put(key, value);
			} else if (toLowerCaseParent != null) {
				toLowerCaseParent.put(key, value);
			} else if (toUpperCaseParent != null) {
				toUpperCaseParent.put(key, value);
			} else if (trimParent != null) {
				trimParent.put(key, value);
			} else if (tryParseParent != null) {
				tryParseParent.put(key, value);
			}
		}
		return super.put(key, value);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case FINISHED:
			if(e.getSource().equals(this)) {
				
			}
			break;
		default:
			break;
		}
	}
}
