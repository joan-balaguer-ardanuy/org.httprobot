package org.httprobot.parameter;

import org.httprobot.MappingParent;
import org.httprobot.ParentListener;
import org.httprobot.event.ManagerEventArgs;

public class ConstantParent 
	extends MappingParent<String, String, ConstantControl> 
		implements java.util.Map.Entry<String, String> {

	/**
	 * -1396975665843544898L
	 */
	private static final long serialVersionUID = -1396975665843544898L;
	
	public ConstantParent() {
	}
	public ConstantParent(Constant message, ParentListener parent) {
		super(message, ConstantControl.class, parent);
	}
	
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		
	}
}