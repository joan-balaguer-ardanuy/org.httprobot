package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ParentListener;

import org.httprobot.MappingParent;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.placeholder.HtmlUnitControl;
import org.httprobot.placeholder.HtmlParent;
import org.httprobot.placeholder.HttpAddressControl;
import org.httprobot.placeholder.HttpAddressParent;

@XmlRootElement
public final class FieldParent
	extends MappingParent<InputField, HtmlPage, FieldControl> {

	/**
	 * -8390181286230356701L
	 */
	private static final long serialVersionUID = -8390181286230356701L;
		
	HtmlParent htmlManager;
	HttpAddressParent httpAddressManager;
	
	public FieldParent() {
		super();
	}
	public FieldParent(Field message, ParentListener parent) {
		super(message, FieldControl.class, parent);
	}
	
	@Override
	public HtmlPage put(InputField key, HtmlPage value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		if(htmlManager != null) {
			htmlManager.put(key, value.getDocument());
		} else if (httpAddressManager != null) {
			httpAddressManager.put(key, value.getUrl());
		} else {
			throw new Error("FieldManager.put: placeholder XML message manager expected");
		}
			
		return super.put(key, value);
	}

	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case HTML_UNIT_CONTROL_LOADED:
			if(e.getSource() instanceof HtmlUnitControl) {
				HtmlUnitControl htmlUnitControl = HtmlUnitControl.class.cast(e.getSource());
				htmlManager = new HtmlParent(htmlUnitControl.getMessage(), this);
				addChildManager(htmlManager);
			}
			break;
		case HTTP_ADDRESS_CONTROL_LOADED:
			if(e.getSource() instanceof HttpAddressControl) {
				HttpAddressControl httpAddressControl = HttpAddressControl.class.cast(e.getSource());
				httpAddressManager = new HttpAddressParent(httpAddressControl.getMessage(), this);
				addChildManager(httpAddressManager);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		// TODO Auto-generated method stub
		
	}
}
