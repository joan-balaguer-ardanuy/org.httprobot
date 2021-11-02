package org.httprobot.placeholder.string;

import org.httprobot.ControlListener;
import org.httprobot.AbstractString;
import org.httprobot.placeholder.AbstractPlaceholderControl;

public abstract class AbstractStringControl<TMessage extends AbstractString>
	extends AbstractPlaceholderControl<TMessage> {

	/**
	 * 4715870462460214922L
	 */
	private static final long serialVersionUID = 4715870462460214922L;

	public AbstractStringControl() {
		super();
	}
	public AbstractStringControl(TMessage message, ControlListener parent) {
		super(message, parent);
	}

}
