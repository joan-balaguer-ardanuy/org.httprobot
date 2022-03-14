package org.httprobot.operator.html;

import org.httprobot.Control;
import org.httprobot.AbstractHtml;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.string.AbstractStringControl;

public class AbstractHtmlControl<T extends AbstractHtml>
	extends AbstractStringControl<T> {

	/**
	 * 7409054080699637039L
	 */
	private static final long serialVersionUID = 7409054080699637039L;

	public AbstractHtmlControl() {
		super();
	}
	public AbstractHtmlControl(T message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
	}
}