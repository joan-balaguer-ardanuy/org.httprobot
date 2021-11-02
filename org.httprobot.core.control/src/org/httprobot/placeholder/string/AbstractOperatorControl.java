package org.httprobot.placeholder.string;

import org.httprobot.ControlListener;
import org.httprobot.AbstractString;
import org.httprobot.placeholder.AbstractPlaceholderControl;

public abstract class AbstractOperatorControl<TMessage extends AbstractString>
	extends AbstractPlaceholderControl<TMessage> {

	/**
	 * 4715870462460214922L
	 */
	private static final long serialVersionUID = 4715870462460214922L;

	public AbstractOperatorControl() {
		super();
	}
	public AbstractOperatorControl(TMessage message, ControlListener parent) {
		super(message, parent);
	}

}
