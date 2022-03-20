package org.httprobot.parameter;

import org.httprobot.ParentMapping;
import org.httprobot.Listener;
import org.httprobot.event.ManagerEventArgs;

public class ConstantParent 
	extends ParentMapping<String, String, ConstantControl> 
		implements java.util.Map.Entry<String, String> {

	/**
	 * -1396975665843544898L
	 */
	private static final long serialVersionUID = -1396975665843544898L;
	
	public ConstantParent() {
	}
	public ConstantParent(Constant message, Listener parent) {
		super(message, ConstantControl.class, parent);
	}
	
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}