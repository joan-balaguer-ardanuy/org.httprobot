package org.httprobot.placeholder;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.Url;
import org.httprobot.placeholder.UrlControl;

public class UrlParent 
	extends AbstractOperatorParent<InputField, String> {

	/**
	 * 5782528861553476045L
	 */
	private static final long serialVersionUID = 5782528861553476045L;
	
	@Override
	public UrlControl getControl() {
		return (UrlControl) super.getControl();
	}
	
	public UrlParent() {
		super();
	}
	public UrlParent(Url message, Parent parent) {
		super(message, UrlControl.class, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
	}
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.toString());
		
		if (hasChildren()) {
			if (concatParent != null) {
				concatParent.put(key, key.getValue().toString());
			} else if (containsParent != null) {
				containsParent.put(key, key.getValue().toString());
			}
		}
		
		return super.put(key, value);
	}
}