package org.httprobot.placeholder.string;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.AbstractOperatorParent;
import org.httprobot.AbstractString;
import org.httprobot.Control;

public abstract class AbstractStringParent
	extends AbstractOperatorParent<InputField, String> {

	/**
	 * 9016980694923790707L
	 */
	private static final long serialVersionUID = 9016980694923790707L;
	
	public AbstractStringParent() {
		super();
	}
	public AbstractStringParent(AbstractString message, Class<? extends Control> type, Parent parent) {
		super(message, type, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		if (key.getValue() != null && hasChildren()) {
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
}
