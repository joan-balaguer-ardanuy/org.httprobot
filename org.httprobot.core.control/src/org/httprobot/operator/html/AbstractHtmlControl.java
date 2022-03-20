package org.httprobot.operator.html;

import org.httprobot.AbstractHtml;
import org.httprobot.Control;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.string.AbstractStringControl;

public class AbstractHtmlControl
	extends AbstractStringControl {

	/**
	 * 7409054080699637039L
	 */
	private static final long serialVersionUID = 7409054080699637039L;

	public AbstractHtmlControl() {
		super();
	}
	public AbstractHtmlControl(AbstractHtml message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				AbstractHtml element = (AbstractHtml) e.getValue();
				if (element.getXPath() == null) {
					throw new Error("ElementControl.OnEventReceived: XPath expression is missing.");
				}
			}
			break;

		default:
			break;
		}
	}
}