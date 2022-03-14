package org.httprobot.operator.string;

import org.httprobot.Control;
import org.httprobot.operator.AbstractOperatorControl;
import org.httprobot.AbstractString;

public abstract class AbstractStringControl<T extends AbstractString>
	extends AbstractOperatorControl<T> {

	/**
	 * 4715870462460214922L
	 */
	private static final long serialVersionUID = 4715870462460214922L;

	public AbstractStringControl() {
		super();
	}
	public AbstractStringControl(T message, Control parent) {
		super(message, parent);
	}

}
