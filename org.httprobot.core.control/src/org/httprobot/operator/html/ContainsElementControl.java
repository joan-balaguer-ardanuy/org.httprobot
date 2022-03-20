package org.httprobot.operator.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;

@XmlRootElement
public final class ContainsElementControl 
	extends AbstractHtmlControl {

	/**
	 * -6845601402472213527L
	 */
	private static final long serialVersionUID = -6845601402472213527L;
	
	@Override
	@XmlElement
	public ContainsElement getMessage() {
		return (ContainsElement) super.getMessage();
	}
	
	public ContainsElementControl() {
		super();
	}
	public ContainsElementControl(ContainsElement message, Control parent) {
		super(message, parent);
	}
}