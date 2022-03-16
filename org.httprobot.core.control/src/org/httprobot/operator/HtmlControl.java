package org.httprobot.operator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.html.AbstractHtmlControl;
import org.httprobot.operator.html.ElementControl;
import org.httprobot.operator.string.AbstractStringControl;

@XmlRootElement
public final class HtmlControl 
	extends AbstractHtmlControl<Html> {

	/**
	 * -5062725452790453335L
	 */
	private static final long serialVersionUID = -5062725452790453335L;
	
	ElementControl elementControl;
	
	@XmlElement
	public ElementControl getElementControl() {
		return elementControl;
	}
	public void setElementControl(ElementControl pageControl) {
		this.elementControl = pageControl;
	}
	@Override
	@XmlElement
	public Html getMessage() {
		return super.getMessage();
	}
	@Override
	public void setMessage(Html message) {
		super.setMessage(message);
	}
	
	public HtmlControl() {
		super();
	}
	public HtmlControl(Html message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				Html html = Html.class.cast(e.getValue());
				
				if(html.getElement() != null) {
					new ElementControl(html.getElement(), this);
				}
			}
			else if(e.getSource() instanceof ElementControl) {
				elementControl = ElementControl.class.cast(e.getSource());
				addChild(elementControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof ElementControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.ELEMENT, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}