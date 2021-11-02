package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Html;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Page extends Html {

	/**
	 * 314618211080687657L
	 */
	private static final long serialVersionUID = 314618211080687657L;

	Anchor anchor;
	Element element;
	Page page;
	
	@XmlElement
	public Anchor getAnchor() {
		return anchor;
	}
	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}

	@XmlElement
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}

	@XmlElement
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public Page() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Page page = Page.class.cast(e.getSource());
		setAnchor(page.getAnchor());
		setElement(page.getElement());
		setPage(page.getPage());
	}
}