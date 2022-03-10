package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;

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
}
