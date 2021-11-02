package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Split extends AbstractString {

	/**
	 * 7159704331152281866L
	 */
	private static final long serialVersionUID = 7159704331152281866L;

	Delimiter delimiter;

	@XmlElement
	public Delimiter getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(Delimiter delimiter) {
		this.delimiter = delimiter;
	}

	public Split() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Split split = Split.class.cast(e.getSource());
		setDelimiter(split.getDelimiter());
	}
}
