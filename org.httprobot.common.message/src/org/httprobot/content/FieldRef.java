package org.httprobot.content;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractContent;
import org.httprobot.DataType;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class FieldRef extends AbstractContent {

	/**
	 * 210911811831590239L
	 */
	private static final long serialVersionUID = 210911811831590239L;
	
	String name;
	DataType dataType;
	
	@XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public FieldRef() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		FieldRef fieldRef = FieldRef.class.cast(e.getSource());
		setName(fieldRef.getName());
		setDataType(fieldRef.getDataType());
	}
}
