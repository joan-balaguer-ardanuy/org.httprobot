package org.httprobot.datatype;

import org.httprobot.Manager;
import org.httprobot.MappingManager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.placeholder.HtmlUnitControl;
import org.httprobot.placeholder.HtmlUnitManager;
import org.httprobot.placeholder.HttpAddressControl;
import org.httprobot.placeholder.HttpAddressManager;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FieldManager
	extends MappingManager<InputField, HtmlPage, FieldControl> {

	/**
	 * -8390181286230356701L
	 */
	private static final long serialVersionUID = -8390181286230356701L;
	
	HtmlPage key;
	InputField value;
	
	HtmlUnitManager htmlUnitManager;
	HttpAddressManager httpAddressManager;

	@Override
	public HtmlPage put(InputField key, HtmlPage value) {
		
		return super.put(key, value);
	}
	
	public FieldManager() {
		super();
	}
	public FieldManager(Field message, Manager<?> parent) {
		super(message, FieldControl.class, parent);
	}
	
//	@Override
//	public InputField put(HtmlPage key, InputField value) {
//		keySet().add(key);
//		setKey(key);
//		setValue(value);
//		
////		if(htmlUnitManager != null) {
////			htmlUnitManager.put(key, value);
////		} else if (httpAddressManager != null) {
////			httpAddressManager.put(key, value);
////		} else {
////			throw new Error("FieldManager.put: placeholder XML message manager expected");
////		}
//			
//		return super.put(key, value);
//	}

	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case HTML_UNIT_CONTROL_LOADED:
			if(e.getSource() instanceof HtmlUnitControl) {
				HtmlUnitControl htmlUnitControl = HtmlUnitControl.class.cast(e.getSource());
				htmlUnitManager = new HtmlUnitManager(htmlUnitControl.getMessage(), this);
				addChildManager(htmlUnitManager);
			}
			break;
		case HTTP_ADDRESS_CONTROL_LOADED:
			if(e.getSource() instanceof HttpAddressControl) {
				HttpAddressControl httpAddressControl = HttpAddressControl.class.cast(e.getSource());
				httpAddressManager = new HttpAddressManager(httpAddressControl.getMessage(), this);
				addChildManager(httpAddressManager);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		// TODO Auto-generated method stub
		
	}
}
