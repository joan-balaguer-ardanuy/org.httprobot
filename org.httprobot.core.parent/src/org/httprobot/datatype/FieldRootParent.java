package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.Map;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.InputField;
import org.httprobot.event.EventArgs;
import org.httprobot.net.HtmlPage;

public class FieldRootParent
	extends ParentEntry<InputDocument, HtmlPage> {

	/**
	 * 6156586566583864082L
	 */
	private static final long serialVersionUID = 6156586566583864082L;

	Map<Field, FieldParent> fieldManagers;
	
	public FieldRootParent() {
		super();
	}
	public FieldRootParent(FieldRoot message, Parent parent) {
		super(message, FieldRootControl.class, parent);
		fieldManagers = new LinkedHashMap<Field, FieldParent>();
	}
	@Override
	public HtmlPage put(InputDocument key, HtmlPage value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		for(Field field : fieldManagers.keySet()) {
			FieldParent fieldManager = fieldManagers.get(field);
			InputField inputField = getTemplateLibrary().getTemplateFieldLibrary().getByName(field.getName());
			fieldManager.put(inputField, value);
		}
		return super.put(key, value);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if (e.getSource() instanceof FieldControl) {
				Field field = FieldControl.class.cast(e.getSource()).getMessage();
				FieldParent fieldManager = new FieldParent(field, this);
				fieldManagers.put(field, fieldManager);
				addChild(fieldManager);
			}
			break;
		default:
			break;
		}
	}
}