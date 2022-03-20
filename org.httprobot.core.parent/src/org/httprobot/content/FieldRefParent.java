package org.httprobot.content;

import org.httprobot.ParentEntry;
import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.event.EventArgs;

public class FieldRefParent 
	extends ParentEntry<FieldRef, InputField> {

	/**
	 * 1140349784992668189L
	 */
	private static final long serialVersionUID = 1140349784992668189L;
	
	public FieldRefParent() {
		super();
	}
	public FieldRefParent(FieldRef message, Parent parent) {
		super(message, FieldRefControl.class, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
	}
}