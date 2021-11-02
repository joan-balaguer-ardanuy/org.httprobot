package org.httprobot;

import javax.xml.bind.annotation.XmlAttribute;

import org.httprobot.event.MessageEventArgs;

public abstract class Html extends AbstractString {

	/**
	 * -6047806638957555356L
	 */
	private static final long serialVersionUID = -6047806638957555356L;

	String id;
	String style;
	String className;
	String title;
	
	@XmlAttribute
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlAttribute
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	@XmlAttribute
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@XmlAttribute
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Html() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Html html = Html.class.cast(e.getSource());
		setId(html.getId());
		setStyle(html.getStyle());
		setClassName(html.getClassName());
		setTitle(html.getTitle());
	}
}
