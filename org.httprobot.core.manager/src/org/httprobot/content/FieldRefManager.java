package org.httprobot.content;

import org.httprobot.ManagerListener;
import org.httprobot.MapManager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class FieldRefManager 
	extends MapManager<FieldRef, InputField, FieldRefControl> {

	/**
	 * 1140349784992668189L
	 */
	private static final long serialVersionUID = 1140349784992668189L;
	
	public FieldRefManager() {
		super();
	}
	public FieldRefManager(FieldRef message, ManagerListener parent) {
		super(message, FieldRefControl.class, parent);
	}
	@Override
	public InputField put(FieldRef key, InputField value) {
		if (keySet().contains(key)) {
			return super.put(key, value);
		}
		return null;
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case FIELD_REF_CONTROL_LOADED:
			if (e.getSource() instanceof FieldRefControl) {
				FieldRef contentTypeRef = FieldRefControl.class.cast(e.getSource()).getMessage();
				keySet().add(contentTypeRef);
			}
			break;

		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}