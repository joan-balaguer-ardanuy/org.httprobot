package org.httprobot.placeholder.string;

import org.httprobot.Control;
import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.AbstractString;
import org.httprobot.placeholder.AbstractPlaceholderManager;

public abstract class AbstractStringManager<T extends Control<?>>
	extends AbstractPlaceholderManager<InputField, String,T> {

	/**
	 * 9016980694923790707L
	 */
	private static final long serialVersionUID = 9016980694923790707L;
	
	public AbstractStringManager() {
		super();
	}
	public AbstractStringManager(AbstractString message, Class<T> type, ManagerListener parent) {
		super(message, type, parent);
	}
	
	@Override
	public String put(InputField key, String value) {
		if (key.getValue() != null && hasChildManagers()) {
			if(concatManager != null) {
				concatManager.put(key, value);
			} else if (containsManager != null) {
				containsManager.put(key, value);
			} else if (endsWithManager != null) {
				endsWithManager.put(key, value);
			} else if (equalsManager != null) {
				equalsManager.put(key, value);
			} else if (replaceManager != null) {
				replaceManager.put(key, value);
			} else if (replaceManager != null) {
				replaceManager.put(key, value);
			} else if (startsWithManager != null) {
				startsWithManager.put(key, value);
			} else if (substringManager != null) {
				substringManager.put(key, value);
			} else if (toLowerCaseManager != null) {
				toLowerCaseManager.put(key, value);
			} else if (toUpperCaseManager != null) {
				toUpperCaseManager.put(key, value);
			} else if (trimManager != null) {
				trimManager.put(key, value);
			} else if (tryParseManager != null) {
				tryParseManager.put(key, value);
			}
		}
		return super.put(key, value);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
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
