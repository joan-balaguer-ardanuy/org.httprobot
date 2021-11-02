package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Placeholder;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.html.Page;

@XmlRootElement
public final class HtmlUnit extends Placeholder {

	/**
	 * -5625666546941269503L
	 */
	private static final long serialVersionUID = -5625666546941269503L;

	Page page;

	@XmlElement
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public HtmlUnit() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);

		HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getSource());
		setPage(htmlUnit.getPage());		
	}
}
