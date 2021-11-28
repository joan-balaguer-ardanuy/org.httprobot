package org.httprobot.placeholder;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class HttpAddressManager 
	extends AbstractPlaceholderManager<InputField, String, HttpAddressControl> {

	/**
	 * 5782528861553476045L
	 */
	private static final long serialVersionUID = 5782528861553476045L;
	
	public HttpAddressManager() {
		super();
	}
	public HttpAddressManager(HttpAddress message, ManagerListener parent) {
		super(message, HttpAddressControl.class, parent);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		super.OnCommandReceived(e);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		key.setValue(value.toString());
		
		if (hasChildManagers()) {
			if (concatManager != null) {
				concatManager.put(key, key.getValue().toString());
			} else if (containsManager != null) {
				containsManager.put(key, key.getValue().toString());
			}
		}
		
		return super.put(key, value);
	}
}