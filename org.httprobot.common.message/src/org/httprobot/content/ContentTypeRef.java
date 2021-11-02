package org.httprobot.content;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Content;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ContentTypeRef extends Content {

	/**
	 * -6701067711809325948L
	 */
	private static final long serialVersionUID = -6701067711809325948L;
	
	String name;
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ContentTypeRef() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ContentTypeRef contentTypeRef = ContentTypeRef.class.cast(e.getSource());
		setName(contentTypeRef.getName());
	}
}
