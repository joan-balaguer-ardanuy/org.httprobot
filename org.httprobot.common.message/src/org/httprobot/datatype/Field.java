package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.DataType;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.HtmlUnit;
import org.httprobot.placeholder.HttpAddress;

@XmlRootElement
public final class Field extends DataType {

	/**
	 * -2640753397637192814L
	 */
	private static final long serialVersionUID = -2640753397637192814L;

	String fieldName;
	HttpAddress httpAddress;
	HtmlUnit htmlUnit;
	
	@XmlElement
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@XmlElement
	public HttpAddress getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(HttpAddress httpAddress) {
		this.httpAddress = httpAddress;
	}

	@XmlElement
	public HtmlUnit getHtmlUnit() {
		return htmlUnit;
	}
	public void setHtmlUnit(HtmlUnit htmlUnit) {
		this.htmlUnit = htmlUnit;
	}

	public Field() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Field field = Field.class.cast(e.getSource());
		setFieldName(field.getFieldName());
		setHttpAddress(field.getHttpAddress());
		setHtmlUnit(field.getHtmlUnit());
	}
}
