package org.httprobot.placeholder.string;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Delimiter extends AbstractString {

	/**
	 * -4767150463662612545L
	 */
	private static final long serialVersionUID = -4767150463662612545L;

	LinkedHashSet<String> delimiter;

	@XmlElement
	public LinkedHashSet<String> getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(LinkedHashSet<String> delimiter) {
		this.delimiter = delimiter;
	}

	public Delimiter() {
		super();
		delimiter = new LinkedHashSet<String>();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Delimiter delimiter = Delimiter.class.cast(e.getSource());
		setDelimiter(delimiter.getDelimiter());
	}
}
