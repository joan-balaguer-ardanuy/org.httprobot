package org.httprobot.operator.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;

@XmlRootElement
public final class ContainsElementControl 
	extends AbstractHtmlControl<ContainsElement> {

	/**
	 * -6845601402472213527L
	 */
	private static final long serialVersionUID = -6845601402472213527L;
	
	@Override
	@XmlElement
	public ContainsElement getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(ContainsElement message) {
		super.setMessage(message);
	}
	
	public ContainsElementControl() {
		super();
	}
	public ContainsElementControl(ContainsElement message, Control parent) {
		super(message, parent);
	}
}