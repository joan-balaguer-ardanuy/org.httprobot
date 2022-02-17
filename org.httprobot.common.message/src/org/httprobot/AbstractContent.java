package org.httprobot;

import javax.xml.bind.annotation.XmlAttribute;

import org.httprobot.event.MessageEventArgs;

/**
 * Abstract content {@link XML} message.
 * It defines the properties of any XML content message.
 * It encapsulates the name property.
 * 
 * @author joan
 *
 */
public abstract class AbstractContent extends XML {

	/**
	 * -3717737361338472612L
	 */
	private static final long serialVersionUID = -3717737361338472612L;
	
	/**
	 * The name of the content.
	 */
	String name;
	
	/**
	 * Returns the name of the content.
	 * @return the name of the content.
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the content.
	 * @param name the name of the content.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * {@link AbstractContent} default class constructor.
	 */
	public AbstractContent() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source and set name property
		setName(AbstractContent.class.cast(e.getSource()).getName());
	}
}