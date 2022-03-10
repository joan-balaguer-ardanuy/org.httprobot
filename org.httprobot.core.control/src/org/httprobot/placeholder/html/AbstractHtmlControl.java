package org.httprobot.placeholder.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.Data;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.string.AbstractStringControl;

public class AbstractHtmlControl<TMessage extends AbstractHtml>
	extends AbstractStringControl<TMessage> {

	/**
	 * 7409054080699637039L
	 */
	private static final long serialVersionUID = 7409054080699637039L;

	ElementControl elementControl;
	ContainsElementControl containsElementControl;
	
	public AbstractHtmlControl() {
		super();
	}
	public AbstractHtmlControl(TMessage message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			AbstractHtml html = AbstractHtml.class.cast(e.getMessage());
			if(html.getElement() != null) {
				new ElementControl(html.getElement(), this);
			} else if(html.getContainsElement() != null) {
				new ContainsElementControl(html.getContainsElement(), this);
			}
		} else if (e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		} else if(e.getSource() instanceof ContainsElementControl) {
			containsElementControl = ContainsElementControl.class.cast(e.getSource());
		}
		
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			AbstractHtml html = AbstractHtml.class.cast(e.getMessage());
			
			if(html.getId() != null) {
				put(Data.ID, html.getId());
			}
			if(html.getStyle() != null) {
				put(Data.STYLE, html.getStyle());
			}
			if(html.getClassName() != null) {
				put(Data.CLASS_NAME, html.getClassName());
			}
			if(html.getTitle() != null) {
				put(Data.TITLE, html.getTitle());
			}
			if(html.getElement() != null) {
				put(Data.ELEMENT, html.getElement());
			}
			if(html.getContainsElement() != null) {
				put(Data.CONTAINS_ELEMENT, html.getContainsElement());
			}
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					Control control = next();
					
					if(control instanceof ContainsElementControl ?
							containsElementControl.equals(control) : false) {
						containsElementControl.loadControl();
					} else if(control instanceof ElementControl ?
							elementControl.equals(control) : false) {
						elementControl.loadControl();
					}
				}
			}
		} else if(e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		} else if(e.getSource() instanceof ContainsElementControl) {
			containsElementControl = ContainsElementControl.class.cast(e.getSource());
			addChildControl(containsElementControl);
		}
	}
}