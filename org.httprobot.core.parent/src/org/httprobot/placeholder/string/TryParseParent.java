package org.httprobot.placeholder.string;

import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.string.TryParse;
import org.httprobot.placeholder.string.TryParseControl;

@XmlRootElement
public class TryParseParent 
	extends AbstractStringParent {

	/**
	 * 3702738941817036919L
	 */
	private static final long serialVersionUID = 3702738941817036919L;

	@Override
	@XmlElement
	public TryParseControl getControl() {
		return (TryParseControl) super.getControl();
	}
	
	public TryParseParent() {
		super();
	}
	public TryParseParent(TryParse message, Parent parent) {
		super(message, TryParseControl.class, parent);
	}

	@Override
	public String put(InputField key, String value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		switch (getControl().getMessage().getFieldType()) {
		case BASE64:
			key.setValue(Base64.class.cast(value));
			break;
		case BOOLEAN:
			key.setValue(Boolean.class.cast(value));
			break;
		case DATETIME:
			key.setValue(Date.class.cast(value));
			break;
		case FLOAT:
			key.setValue(Float.class.cast(value));
			break;
		case INTEGER:
			key.setValue(Integer.class.cast(value));
			break;
		case URL:
			key.setValue(URL.class.cast(value));
			break;
		case UUID:
			key.setValue(UUID.class.cast(value));
			break;
		default:
			key.setValue(null);
			break;
		}
		
		return super.put(key, value);
	}
}