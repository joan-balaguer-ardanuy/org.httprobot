package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.Html;
import org.httprobot.placeholder.html.AbstractHtmlControl;
import org.httprobot.placeholder.html.Element;
import org.httprobot.placeholder.html.ElementControl;

/**
 * {@link Html} {@link XML} {@link Control} class.
 * It inherits {@link AbstractHtmlControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class HtmlControl 
	extends AbstractHtmlControl {

	/**
	 * -5062725452790453335L
	 */
	private static final long serialVersionUID = -5062725452790453335L;
	
	/**
	 * The element message control.
	 */
	ElementControl elementControl;
	
	/**
	 * Returns the {@link Element} message control.
	 * @return the {@link Element} message control.
	 */
	@XmlElement
	public ElementControl getElementControl() {
		return elementControl;
	}
	/**
	 * Sets the {@link Element} message control.
	 * @param pageControl {@link ElementControl} the message element control
	 */
	public void setElementControl(ElementControl pageControl) {
		this.elementControl = pageControl;
	}
	@Override
	@XmlElement
	public Html getMessage() {
		return (Html) super.getMessage();
	}
	
	/**
	 * {@link HtmlControl} default class constuctor.
	 */
	public HtmlControl() {
		super();
	}
	/**
	 * {@link HtmlControl} class constuctor.
	 * @param message {@link Html} the message
	 * @param parent {@link Control} the parent instance
	 */
	public HtmlControl(Html message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast event's value
				Html html = (Html) e.getValue();
				
				if(html.getElement() != null) {
					// if element exists instance new element message control
					new ElementControl(html.getElement(), this);
				}
			}
			else if(e.getSource() instanceof ElementControl) {
				// set property
				elementControl = (ElementControl) e.getSource();
				// set child
				addChild(elementControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof ElementControl) {
				// if element message control is a child of current instance
				if(getChildren().contains(e.getSource())) {
					// set data
					put(Data.ELEMENT, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}