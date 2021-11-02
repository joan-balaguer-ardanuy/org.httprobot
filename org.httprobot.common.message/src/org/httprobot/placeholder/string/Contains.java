package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Contains extends AbstractString {

	/**
	 * -4428551664350568381L
	 */
	private static final long serialVersionUID = -4428551664350568381L;

	String value;
	
	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Contains() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Contains contains = Contains.class.cast(e.getSource());
		setValue(contains.getValue());
	}
}