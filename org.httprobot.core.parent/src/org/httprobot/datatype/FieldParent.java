package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.data.field.InputField;
import org.httprobot.event.EventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.operator.HtmlControl;
import org.httprobot.operator.HtmlParent;
import org.httprobot.operator.UrlParent;
import org.httprobot.operator.UrlControl;

@XmlRootElement
public final class FieldParent
	extends ParentEntry<InputField, HtmlPage> {

	/**
	 * -8390181286230356701L
	 */
	private static final long serialVersionUID = -8390181286230356701L;
		
	HtmlParent htmlManager;
	UrlParent httpAddressManager;
	
	public FieldParent() {
		super();
	}
	public FieldParent(Field message, Parent parent) {
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
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof HtmlControl) {
				HtmlControl htmlUnitControl = HtmlControl.class.cast(e.getSource());
				htmlManager = new HtmlParent(htmlUnitControl.getMessage(), this);
				addChild(htmlManager);
			} else if(e.getSource() instanceof UrlControl) {
				UrlControl httpAddressControl = UrlControl.class.cast(e.getSource());
				httpAddressManager = new UrlParent(httpAddressControl.getMessage(), this);
				addChild(httpAddressManager);
			}
			break;
		default:
			break;
		}
	}
}
