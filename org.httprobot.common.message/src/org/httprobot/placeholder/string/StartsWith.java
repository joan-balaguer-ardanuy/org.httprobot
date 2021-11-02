package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class StartsWith extends AbstractString {
	
	/**
	 * -7127204443905401697L
	 */
	private static final long serialVersionUID = -7127204443905401697L;

	String value;
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public StartsWith() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		StartsWith startsWith = StartsWith.class.cast(e.getSource());
		setValue(startsWith.getValue());
	}
}
