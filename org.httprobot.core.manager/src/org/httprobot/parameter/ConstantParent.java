package org.httprobot.parameter;

import org.httprobot.Parent;
import org.httprobot.ManagerEventType;
import org.httprobot.ManagerListener;
import org.httprobot.event.ManagerEventArgs;

public class ConstantParent 
	extends Parent<ConstantControl> 
		implements java.util.Map.Entry<String, String> {

	/**
	 * -1396975665843544898L
	 */
	private static final long serialVersionUID = -1396975665843544898L;

	@Override
	public String getKey() {
		return getControl().getMessage().getKey();
	}
	@Override
	public String getValue() {
		return getControl().getMessage().getValue();
	}
	@Override
	public String setValue(String value) {
		getControl().getMessage().setValue(value);
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return null;
	}
	
	public ConstantParent() {
	}
	public ConstantParent(Constant message, ManagerListener parent) {
		super(message, ConstantControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}