package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Trim extends AbstractString {

	/**
	 * 7723659999253778536L
	 */
	private static final long serialVersionUID = 7723659999253778536L;

	public Trim() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
	}
}
