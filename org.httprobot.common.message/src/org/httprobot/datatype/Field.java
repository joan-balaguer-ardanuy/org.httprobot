package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.XML;
import org.httprobot.placeholder.Html;
import org.httprobot.placeholder.Url;

@XmlRootElement
public final class Field extends XML {

	/**
	 * -2640753397637192814L
	 */
	private static final long serialVersionUID = -2640753397637192814L;

	Url url;
	Html html;
	
	@XmlElement
	public Url getUrl() {
		return url;
	}
	public void setUrl(Url httpAddress) {
		this.url = httpAddress;
	}

	@XmlElement
	public Html getHtml() {
		return html;
	}
	public void setHtml(Html htmlUnit) {
		this.html = htmlUnit;
	}

	public Field() {
		super();
	}
}
