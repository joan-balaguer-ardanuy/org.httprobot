package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.operator.string.Concat;
import org.httprobot.operator.string.Contains;
import org.httprobot.operator.string.EndsWith;
import org.httprobot.operator.string.Equals;
import org.httprobot.operator.string.Replace;
import org.httprobot.operator.string.StartsWith;
import org.httprobot.operator.string.Substring;
import org.httprobot.operator.string.ToLowerCase;
import org.httprobot.operator.string.ToUpperCase;
import org.httprobot.operator.string.Trim;
import org.httprobot.operator.string.TryParse;

/**
 * Abstract placeholder {@link Message} message.
 * It encapsulates the string operators to treat string objects.
 * Only one of these operators MUST be implemented, otherwise the program won't run properly.
 * 
 * @author joan
 *
 */
public abstract class AbstractOperator extends Message {

	/**
	 * 3345969046526629498L
	 */
	private static final long serialVersionUID = 3345969046526629498L;

	/**
	 * The Contains string operator.
	 */
	Contains contains;
	/**
	 * The Equals string operator.
	 */
	Equals equals;
	/**
	 * The Trim string operator
	 */
	Trim trim;
	/**
	 * The Replace string operator
	 */
	Replace replace;
	/**
	 * The Concat string operator
	 */
	Concat concat;
	/**
	 * The Substring string operator
	 */
	Substring substring;
	/**
	 * The TryParse string operator
	 */
	TryParse tryParse;
	/**
	 * The StartsWith string operator
	 */
	StartsWith startsWith;
	/**
	 * The EndsWith string operator
	 */
	EndsWith endsWith;
	/**
	 * The ToUpperCase string operator
	 */
	ToUpperCase toUpperCase;
	/**
	 * The ToLowerCase string operator
	 */
	ToLowerCase toLowerCase;

	/**
	 * Returns the {@link Contains} string operator.
	 * @return the {@link Contains} string operator.
	 */
	@XmlElement
	public Contains getContains() {
		return contains;
	}
	/**
	 * Sets the {@link Contains} string operator.
	 * @param contains {@link Contains} string operator to be set
	 */
	public void setContains(Contains contains) {
		this.contains = contains;
	}

	/**
	 * Returns the {@link Equals} string operator.
	 * @return the {@link Equals} string operator.
	 */
	@XmlElement
	public Equals getEquals() {
		return equals;
	}
	/**
	 * Sets the {@link Equals} string operator.
	 * @param contains {@link Equals} string operator to be set
	 */
	public void setEquals(Equals equals) {
		this.equals = equals;
	}

	/**
	 * Returns the {@link Trim} string operator.
	 * @return the {@link Trim} string operator.
	 */
	@XmlElement
	public Trim getTrim() {
		return trim;
	}
	/**
	 * Sets the {@link Trim} string operator.
	 * @param contains {@link Trim} string operator to be set
	 */
	public void setTrim(Trim remove) {
		this.trim = remove;
	}

	/**
	 * Returns the {@link Replace} string operator.
	 * @return the {@link Replace} string operator.
	 */
	@XmlElement
	public Replace getReplace() {
		return replace;
	}
	/**
	 * Sets the {@link Replace} string operator.
	 * @param contains {@link Replace} string operator to be set
	 */
	public void setReplace(Replace replace) {
		this.replace = replace;
	}

	/**
	 * Returns the {@link Concat} string operator.
	 * @return the {@link Concat} string operator.
	 */
	@XmlElement
	public Concat getConcat() {
		return concat;
	}
	/**
	 * Sets the {@link Concat} string operator.
	 * @param contains {@link Concat} string operator to be set
	 */
	public void setConcat(Concat concat) {
		this.concat = concat;
	}

	/**
	 * Returns the {@link Substring} string operator.
	 * @return the {@link Substring} string operator.
	 */
	@XmlElement
	public Substring getSubstring() {
		return substring;
	}
	/**
	 * Sets the {@link Substring} string operator.
	 * @param contains {@link Substring} string operator to be set
	 */
	public void setSubstring(Substring substring) {
		this.substring = substring;
	}

	/**
	 * Returns the {@link TryParse} string operator.
	 * @return the {@link TryParse} string operator.
	 */
	@XmlElement
	public TryParse getTryParse() {
		return tryParse;
	}
	/**
	 * Sets the {@link TryParse} string operator.
	 * @param contains {@link TryParse} string operator to be set
	 */
	public void setTryParse(TryParse tryParse) {
		this.tryParse = tryParse;
	}
	
	/**
	 * Returns the {@link StartsWith} string operator.
	 * @return the {@link StartsWith} string operator.
	 */
	@XmlElement
	public StartsWith getStartsWith() {
		return startsWith;
	}
	/**
	 * Sets the {@link StartsWith} string operator.
	 * @param contains {@link StartsWith} string operator to be set
	 */
	public void setStartsWith(StartsWith startsWith) {
		this.startsWith = startsWith;
	}
	
	/**
	 * Returns the {@link EndsWith} string operator.
	 * @return the {@link EndsWith} string operator.
	 */
	@XmlElement
	public EndsWith getEndsWith() {
		return endsWith;
	}
	/**
	 * Sets the {@link EndsWith} string operator.
	 * @param contains {@link EndsWith} string operator to be set
	 */
	public void setEndsWith(EndsWith endsWith) {
		this.endsWith = endsWith;
	}

	/**
	 * Returns the {@link ToUpperCase} string operator.
	 * @return the {@link ToUpperCase} string operator.
	 */
	@XmlElement
	public ToUpperCase getToUpperCase() {
		return toUpperCase;
	}
	/**
	 * Sets the {@link ToUpperCase} string operator.
	 * @param contains {@link ToUpperCase} string operator to be set
	 */
	public void setToUpperCase(ToUpperCase toUpperCase) {
		this.toUpperCase = toUpperCase;
	}
	
	/**
	 * Returns the {@link ToLowerCase} string operator.
	 * @return the {@link ToLowerCase} string operator.
	 */
	@XmlElement
	public ToLowerCase getToLowerCase() {
		return toLowerCase;
	}
	/**
	 * Sets the {@link ToLowerCase} string operator.
	 * @param contains {@link ToLowerCase} string operator to be set
	 */
	public void setToLowerCase(ToLowerCase toLowerCase) {
		this.toLowerCase = toLowerCase;
	}
	
	/**
	 * {@link AbstractOperator} default class constructor.
	 */
	public AbstractOperator() {
		super();
	}
}