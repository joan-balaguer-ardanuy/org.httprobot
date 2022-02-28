package org.httprobot.unit;

import org.httprobot.ManagerListener;
import org.httprobot.MappingParent;
import org.httprobot.event.ManagerEventArgs;

public class IsInstanceParent
	extends MappingParent<Object, Boolean, IsInstanceControl> {

	/**
	 * 313145130051473638L
	 */
	private static final long serialVersionUID = 313145130051473638L;
	
	public IsInstanceParent() {
		super();
	}
	public IsInstanceParent(IsInstance message, ManagerListener parent) {
		super(message, IsInstanceControl.class, parent);
	}
	@Override
	public Boolean put(Object key, Boolean value) {
		keySet().add(key);
		try {
			Class<?> type = Class.forName(getControl().getMessage().getClassName());
			if(type.isInstance(key)) {
				value = true;
			} else {
				value = false;
			}
		} catch (ClassNotFoundException e) {
			throw new Error("IsInstanceManager.put: class not found error.", e);
		}
		
		return super.put(key, value);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}

}
