package org.httprobot.parameter;

import org.httprobot.ParentEntry;
import org.httprobot.Parent;

public class ConstantParent 
	extends ParentEntry<String, String> {

	/**
	 * -1396975665843544898L
	 */
	private static final long serialVersionUID = -1396975665843544898L;
	
	public ConstantParent() {
	}
	public ConstantParent(Constant message, Parent parent) {
		super(message, ConstantControl.class, parent);
	}
}