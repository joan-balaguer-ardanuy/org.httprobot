/**
 * 
 */
package org.xmlrobot;

/**
 * The commands.
 * @author joan
 *
 */
public enum Command {
	/**
	 * Genesis state.
	 */
	GENESIS(0),
	/**
	 * Listen time.
	 */
	LISTEN(1),
	/**
	 * Transfer message contents.
	 */
	TRANSFER(2),
	/**
	 * &lt;
	 */
	OPEN(3),
	/**
	 * &gt
	 */
	CLOSE(4),
	/**
	 * Plain text.
	 */
	TEXT(5),
	/**
	 * &lt;?xml declaration ?&gt;
	 */
	DECLARATION(6),
	/**
	 * &lt;!-- comment --&gt;
	 */
	COMMENT(7),
	/**
	 * &lt;start_markup&gt;
	 */
	START_MARKUP(8),
	/**
	 * &lt;/end_markup&gt;
	 */
	END_MARKUP(9),
	/**
	 * &lt;simple_markup/&gt;
	 */
	SIMPLE_MARKUP(10);

	/**
	 * The value associated to current instance.
	 */
	private final int value;
	/**
	 * Returns the value associated to current instance.
	 * @return the value associated to current instance
	 */
	public final int getValue() {
		return this.value;
	}
	/**
	 * {@link Command} default class constructor.
	 * @param value the value
	 */
	private Command(int value) {
		this.value = value;
	}
}