package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractHtml;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Anchor extends AbstractHtml {

	/**
	 * -7137911219896650095L
	 */
	private static final long serialVersionUID = -7137911219896650095L;
	
	String href;
	String hrefLang;
	String name;
	String textContent;
	String charset;
	String type;
	String target;
	
	@XmlElement
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	@XmlElement
	public String getHrefLang() {
		return hrefLang;
	}
	public void setHrefLang(String hrefLang) {
		this.hrefLang = hrefLang;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	@XmlElement
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	@XmlElement
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public Anchor() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Anchor anchor = Anchor.class.cast(e.getSource());
		setHref(anchor.getHref());
		setHrefLang(anchor.getHrefLang());
		setName(anchor.getName());
		setTextContent(anchor.getTextContent());
		setCharset(anchor.getCharset());
		setType(anchor.getType());
		setTarget(anchor.getTarget());
	}
}