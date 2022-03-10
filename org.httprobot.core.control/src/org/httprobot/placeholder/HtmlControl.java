package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.Html;
import org.httprobot.placeholder.html.ElementControl;

@XmlRootElement
public final class HtmlControl 
	extends AbstractPlaceholderControl<Html> {

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
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Html htmlUnit = Html.class.cast(e.getMessage());
			
			if(htmlUnit.getElement() != null) {
				new ElementControl(htmlUnit.getElement(), this);
			}
		} 
		else if(e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {

			Html htmlUnit = Html.class.cast(e.getMessage());

			if (hasChildControls()) {

				while (hasNext()) {
					Control control = next();
					
					if(control instanceof ElementControl ?
							htmlUnit.getElement() != null ?
									elementControl.equals(control)
									: false : false) {
						elementControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof ElementControl) {
			if(getChildren().contains(e.getSource())) {
				put(Data.PAGE, e.getMessage());
			}	
		}
	}
}