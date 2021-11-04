package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Replace extends AbstractString {

	/**
	 * 4097627589074564841L
	 */
	private static final long serialVersionUID = 4097627589074564841L;

	Character oldChar;
	Character newChar;
	
	@XmlElement
	public Character getOldCharacter() {
		return oldChar;
	}
	public void setOldCharacter(Character oldString) {
		this.oldChar = oldString;
	}
	
	@XmlElement
	public Character getNewCharacter() {
		return newChar;
	}
	public void setNewCharacter(Character newString) {
		this.newChar = newString;
	}

	public Replace() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Replace replace = Replace.class.cast(e.getSource());
		setOldCharacter(replace.getOldCharacter());
		setNewCharacter(replace.getNewCharacter());
	}
}
