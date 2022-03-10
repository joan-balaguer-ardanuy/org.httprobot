package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.XML;
import org.httprobot.operator.Html;
import org.httprobot.operator.Url;

@XmlRootElement
public final class Field extends XML {

	/**
	 * -2640753397637192814L
	 */
	private static final long serialVersionUID = -2640753397637192814L;

	String fieldName;
	Url httpAddress;
	Html htmlUnit;
	
	@XmlElement
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@XmlElement
	public Url getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(Url httpAddress) {
		this.httpAddress = httpAddress;
	}

	@XmlElement
	public Html getHtmlUnit() {
		return htmlUnit;
	}
	public void setHtmlUnit(Html htmlUnit) {
		this.htmlUnit = htmlUnit;
	}

	public Field() {
		super();
	}
}
