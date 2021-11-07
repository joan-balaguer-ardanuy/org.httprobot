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

	String oldString;
	String newString;
	
	@XmlElement
	public String getOldString() {
		return oldString;
	}
	public void setOldString(String oldString) {
		this.oldString = oldString;
	}
	
	@XmlElement
	public String getNewString() {
		return newString;
	}
	public void setNewString(String newString) {
		this.newString = newString;
	}

	public Replace() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Replace replace = Replace.class.cast(e.getSource());
		setOldString(replace.getOldString());
		setNewString(replace.getNewString());
	}
}
