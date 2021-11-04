package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractString;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Substring extends AbstractString {

	/**
	 * 7723823443728789980L
	 */
	private static final long serialVersionUID = 7723823443728789980L;

	Integer startIndex;
	Integer endIndex;
	
	@XmlElement
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	@XmlElement
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	public Substring() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Substring substring = Substring.class.cast(e.getSource());
		setStartIndex(substring.getStartIndex());
		setEndIndex(substring.getEndIndex());
	}
}
