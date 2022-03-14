package org.httprobot.operator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.html.ElementControl;

@XmlRootElement
public final class HtmlControl 
	extends AbstractOperatorControl<Html> {

	/**
	 * -5062725452790453335L
	 */
	private static final long serialVersionUID = -5062725452790453335L;
	
	ElementControl elementControl;
	
	@XmlElement
	public ElementControl getPageControl() {
		return elementControl;
	}
	public void setPageControl(ElementControl pageControl) {
		this.elementControl = pageControl;
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