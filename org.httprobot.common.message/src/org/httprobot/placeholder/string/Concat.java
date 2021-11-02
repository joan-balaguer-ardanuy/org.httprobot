package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Concat extends AbstractString {

	/**
	 * 7159704331152281866L
	 */
	private static final long serialVersionUID = 7159704331152281866L;

	String value;

	@XmlElement
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Concat() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Concat split = Concat.class.cast(e.getSource());
		setValue(split.getValue());
	}
}
