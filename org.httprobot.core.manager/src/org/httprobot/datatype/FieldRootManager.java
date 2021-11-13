package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.Map;

import org.httprobot.ManagerListener;
import org.httprobot.Manager;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebElement;

public class FieldRootManager
	extends Manager<InputDocument, WebElement, FieldRootControl> {

	/**
	 * 6156586566583864082L
	 */
	private static final long serialVersionUID = 6156586566583864082L;

	Map<Field, FieldManager> fieldManagers;
	
	public FieldRootManager() {
		super();
	}
	public FieldRootManager(FieldRoot message, ManagerListener parent) {
		super(message, FieldRootControl.class, parent);
		fieldManagers = new LinkedHashMap<Field, FieldManager>();
	}
	@Override
	public WebElement put(InputDocument key, WebElement value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		for(Field field : fieldManagers.keySet()) {
			FieldManager fieldManager = fieldManagers.get(field);
			InputField inputField = getTemplateLibrary().getTemplateFieldLibrary().getByUUID(field.getUuid());
			fieldManager.put(inputField, value);
		}
		return super.put(key, value);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case FIELD_CONTROL_LOADED:
			if (e.getSource() instanceof FieldControl) {
				Field field = FieldControl.class.cast(e.getSource()).getMessage();
				FieldManager fieldManager = new FieldManager(field, this);
				fieldManagers.put(field, fieldManager);
				addChildManager(fieldManager);
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