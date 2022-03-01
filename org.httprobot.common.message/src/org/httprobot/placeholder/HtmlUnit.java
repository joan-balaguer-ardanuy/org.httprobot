package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractPlaceholder;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.html.Element;

@XmlRootElement
public final class HtmlUnit extends AbstractPlaceholder {

	/**
	 * -5625666546941269503L
	 */
	private static final long serialVersionUID = -5625666546941269503L;

	Element element;

	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element page) {
		this.element = page;
	}

	public HtmlUnit() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);

		HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getSource());
		setElement(htmlUnit.getElement());		
	}
}
