package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.XML;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class EndIndex extends XML {

	/**
	 * 6832286035450762677L
	 */
	private static final long serialVersionUID = 6832286035450762677L;

	String stringValue;
	Integer integerValue;
	Integer offset;
	
	@XmlElement
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	@XmlElement
	public Integer getIntegerValue() {
		return integerValue;
	}
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	@XmlElement
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public EndIndex() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		EndIndex endIndex = EndIndex.class.cast(e.getSource());
		setStringValue(endIndex.getStringValue());
		setIntegerValue(endIndex.getIntegerValue());
		setOffset(endIndex.getOffset());
	}
}
