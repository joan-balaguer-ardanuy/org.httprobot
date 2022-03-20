package org.httprobot.operator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.operator.string.AbstractStringControl;

@XmlRootElement
public final class UrlControl 
	extends AbstractStringControl {

	/**
	 * 1246127918758222588L
	 */
	private static final long serialVersionUID = 1246127918758222588L;

	@Override
	@XmlElement
	public Url getMessage() {
		return (Url) super.getMessage();
	}
	
	public UrlControl() {
		super();
	}
	public UrlControl(Url message, Control parent) {
		super(message, parent);
	}
}