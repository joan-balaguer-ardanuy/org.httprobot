package org.httprobot.placeholder;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.operator.Url;

public class HttpAddressParent 
	extends AbstractPlaceholderParent<InputField, String, HttpAddressControl> {

	/**
	 * 5782528861553476045L
	 */
	private static final long serialVersionUID = 5782528861553476045L;
	
	public HttpAddressParent() {
		super();
	}
	public HttpAddressParent(Url message, Listener parent) {
		super(message, HttpAddressControl.class, parent);
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		super.OnEventReceived(e);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.toString());
		
		if (hasChildManagers()) {
			if (concatParent != null) {
				concatParent.put(key, key.getValue().toString());
			} else if (containsParent != null) {
				containsParent.put(key, key.getValue().toString());
			}
		}
		
		return super.put(key, value);
	}
}