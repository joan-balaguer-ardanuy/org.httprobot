package org.httprobot.operator;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;

@XmlRootElement
public final class UrlControl 
	extends AbstractOperatorControl<Url> {

	/**
	 * 1246127918758222588L
	 */
	private static final long serialVersionUID = 1246127918758222588L;

	public UrlControl() {
		super();
	}
	public UrlControl(Url message, Control parent) {
		super(message, parent);
	}
}