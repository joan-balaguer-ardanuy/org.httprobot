/**
 * 
 */
package org.xmlrobot.util;


/**
 * Abort exception implementation class.
 * 
 * <p>An <tt>abort</tt> will be thrown when something is wrong.
 * 
 * @author joan
 *
 */
public class Abort extends RuntimeException {

	/**
	 * -2997851168164530403L
	 */
	private static final long serialVersionUID = -2997851168164530403L;

	protected transient Object source;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	/**
	 * {@link Abort} default class constructor.
	 */
	public Abort() {
		super();
	}
	/**
	 * {@link Abort} class constructor.
	 * @param source {@link TimeListener} the source
	 */
	public Abort(Object source) {
		super();
		this.source = source;
	}
	/**
	 * {@link Abort} class constructor.
	 * @param cause {@link Throwable} the cause
	 */
	public Abort(Throwable cause) {
		super(cause);
	}
	/**
	 * {@link Abort} class constructor.
	 * @param cause {@link Throwable} the cause
	 * @param source {@link TimeListener} the source
	 */
	public Abort(Throwable cause, Object source) {
		super(cause);
		this.source = source;
	}
}