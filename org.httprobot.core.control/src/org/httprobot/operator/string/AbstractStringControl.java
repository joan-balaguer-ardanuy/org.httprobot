package org.httprobot.operator.string;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.AbstractString;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.event.EventArgs;

public abstract class AbstractStringControl
	extends AbstractControl {

	/**
	 * -138070185044904871L
	 */
	private static final long serialVersionUID = -138070185044904871L;

	/**
	 * Contains message control.
	 */
	ContainsControl containsControl;
	/**
	 * Equals message control.
	 */
	EqualsControl equalsControl;
	/**
	 * Trim message control.
	 */
	TrimControl trimControl;
	/**
	 * Replace message control.
	 */
	ReplaceControl replaceControl;
	/**
	 * Concat message control.
	 */
	ConcatControl concatControl;
	/**
	 * Substring message control.
	 */
	SubstringControl substringControl;
	/**
	 * TryParse message control.
	 */
	TryParseControl tryParseControl;
	/**
	 * StartsWith message control.
	 */
	StartsWithControl startsWithControl;
	/**
	 * EndsWith message control.
	 */
	EndsWithControl endsWithControl;
	/**
	 * ToUpperCase message control.
	 */
	ToUpperCaseControl toUpperCaseControl;
	/**
	 * ToLowerCase message control.
	 */
	ToLowerCaseControl toLowerCaseControl;
	
	/**
	 * Returns the {@link Contains} message control.
	 * @return the {@link Contains} message control.
	 */
	@XmlElement
	public ContainsControl getContainsControl() {
		return containsControl;
	}
	/**
	 * Sets the {@link Contains} message control.
	 * @param containsControl {@link ContainsControl} the message control.
	 */
	public void setContainsControl(ContainsControl containsControl) {
		this.containsControl = containsControl;
	}
	/**
	 * Returns the {@link Equals} message control.
	 * @return the {@link Equals} message control.
	 */
	@XmlElement
	public EqualsControl getEqualsControl() {
		return equalsControl;
	}
	/**
	 * Sets the {@link Equals} message control.
	 * @param equalsControl {@link EqualsControl} the message control.
	 */
	public void setEqualsControl(EqualsControl equalsControl) {
		this.equalsControl = equalsControl;
	}
	/**
	 * Returns the {@link Trim} message control.
	 * @return the {@link Trim} message control.
	 */
	@XmlElement
	public TrimControl getTrimControl() {
		return trimControl;
	}
	/**
	 * Sets the {@link Trim} message control.
	 * @param trimControl {@link TrimControl} the message control.
	 */
	public void setTrimControl(TrimControl trimControl) {
		this.trimControl = trimControl;
	}
	/**
	 * Returns the {@link Replace} message control.
	 * @return the {@link Replace} message control.
	 */
	@XmlElement
	public ReplaceControl getReplaceControl() {
		return replaceControl;
	}
	/**
	 * Sets the {@link Replace} message control.
	 * @param replaceControl {@link ReplaceControl} the message control.
	 */
	public void setReplaceControl(ReplaceControl replaceControl) {
		this.replaceControl = replaceControl;
	}
	/**
	 * Returns the {@link Concat} message control.
	 * @return the {@link Concat} message control.
	 */
	@XmlElement
	public ConcatControl getConcatControl() {
		return concatControl;
	}
	/**
	 * Sets the {@link Concat} message control.
	 * @param concatControl {@link ConcatControl} the message control.
	 */
	public void setConcatControl(ConcatControl concatControl) {
		this.concatControl = concatControl;
	}
	/**
	 * Returns the {@link Substring} message control.
	 * @return the {@link Substring} message control.
	 */
	@XmlElement
	public SubstringControl getSubstringControl() {
		return substringControl;
	}
	/**
	 * Sets the {@link Substring} message control.
	 * @param substringControl {@link SubstringControl} the message control.
	 */
	public void setSubstringControl(SubstringControl substringControl) {
		this.substringControl = substringControl;
	}
	/**
	 * Returns the {@link TryParse} message control.
	 * @return the {@link TryParse} message control.
	 */
	@XmlElement
	public TryParseControl getTryParseControl() {
		return tryParseControl;
	}
	/**
	 * Sets the {@link TryParse} message control.
	 * @param tryParseControl {@link TryParseControl} the message control.
	 */
	public void setTryParseControl(TryParseControl tryParseControl) {
		this.tryParseControl = tryParseControl;
	}
	/**
	 * Returns the {@link StartsWith} message control.
	 * @return the {@link StartsWith} message control.
	 */
	@XmlElement
	public StartsWithControl getStartsWithControl() {
		return startsWithControl;
	}
	/**
	 * Sets the {@link StartsWith} message control.
	 * @param startsWithControl {@link StartsWithControl} the message control.
	 */
	public void setStartsWithControl(StartsWithControl startsWithControl) {
		this.startsWithControl = startsWithControl;
	}
	/**
	 * Returns the {@link EndsWith} message control.
	 * @return the {@link EndsWith} message control.
	 */
	@XmlElement
	public EndsWithControl getEndsWithControl() {
		return endsWithControl;
	}
	/**
	 * Sets the {@link EndsWith} message control.
	 * @param endsWithControl {@link EndsWithControl} the message control.
	 */
	public void setEndsWithControl(EndsWithControl endsWithControl) {
		this.endsWithControl = endsWithControl;
	}
	/**
	 * Returns the {@link ToUpperCase} message control.
	 * @return the {@link ToUpperCase} message control.
	 */
	@XmlElement
	public ToUpperCaseControl getToUpperCaseControl() {
		return toUpperCaseControl;
	}
	/**
	 * Sets the {@link ToUpperCase} message control.
	 * @param toUpperCaseControl {@link ToUpperCaseControl} the message control.
	 */
	public void setToUpperCaseControl(ToUpperCaseControl toUpperCaseControl) {
		this.toUpperCaseControl = toUpperCaseControl;
	}
	/**
	 * Returns the {@link ToLowerCase} message control.
	 * @return the {@link ToLowerCase} message control.
	 */
	@XmlElement
	public ToLowerCaseControl getToLowerCaseControl() {
		return toLowerCaseControl;
	}
	/**
	 * Sets the {@link ToLowerCase} message control.
	 * @param toLowerCaseControl {@link ToLowerCaseControl} the message control.
	 */
	public void setToLowerCaseControl(ToLowerCaseControl toLowerCaseControl) {
		this.toLowerCaseControl = toLowerCaseControl;
	}
	
	/**
	 * {@link AbstractStringControl} default class constructor.
	 */
	public AbstractStringControl() {
		super();
	}
	/**
	 * {@link AbstractStringControl} class constructor.
	 * @param message {@link XML} the message
	 * @param parent {@link Control} the parent instance
	 */
	public AbstractStringControl(XML message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast abstract message
				AbstractString operator = (AbstractString) e.getValue();
				// find next operator, only the first one found will be taken
				if (operator.getContains() != null) {
					// instance new operator message control
					new ContainsControl(operator.getContains(), this);
				} else if (operator.getEquals() != null) {
					// instance new operator message control
					new EqualsControl(operator.getEquals(), this);
				} else if (operator.getTrim() != null) {
					// instance new operator message control
					new TrimControl(operator.getTrim(), this);
				} else if (operator.getReplace() != null) {
					// instance new operator message control
					new ReplaceControl(operator.getReplace(), this);
				} else if (operator.getConcat() != null) {
					// instance new operator message control
					new ConcatControl(operator.getConcat(), this);
				} else if (operator.getSubstring() != null) {
					// instance new operator message control
					new SubstringControl(operator.getSubstring(), this);
				} else if (operator.getTryParse() != null) {
					// instance new operator message control
					new TryParseControl(operator.getTryParse(), this);
				} else if (operator.getStartsWith() != null) {
					// instance new operator message control
					new StartsWithControl(operator.getStartsWith(), this);
				} else if (operator.getEndsWith() != null) {
					// instance new operator message control
					new EndsWithControl(operator.getEndsWith(), this);
				} else if (operator.getToUpperCase() != null) {
					// instance new operator message control
					new ToUpperCaseControl(operator.getToUpperCase(), this);
				} else if (operator.getToLowerCase() != null) {
					// instance new operator message control
					new ToLowerCaseControl(operator.getToLowerCase(), this);
				}
			} else if (e.getSource() instanceof TryParseControl) {
				// set property
				tryParseControl = (TryParseControl) e.getSource();
				// set new child
				addChild(tryParseControl);
			} else if (e.getSource() instanceof SubstringControl) {
				// set property
				substringControl = (SubstringControl) e.getSource();
				// set new child
				addChild(substringControl);
			} else if (e.getSource() instanceof ConcatControl) {
				// set property
				concatControl = (ConcatControl) e.getSource();
				// set new child
				addChild(concatControl);
			} else if (e.getSource() instanceof ReplaceControl) {
				// set property
				replaceControl = (ReplaceControl) e.getSource();
				// set new child
				addChild(replaceControl);
			} else if (e.getSource() instanceof TrimControl) {
				// set property
				trimControl = (TrimControl) e.getSource();
				// set new child
				addChild(trimControl);
			} else if (e.getSource() instanceof EqualsControl) {
				// set property
				equalsControl = (EqualsControl) e.getSource();
				// set new child
				addChild(equalsControl);
			} else if (e.getSource() instanceof ContainsControl) {
				// set property
				containsControl = (ContainsControl) e.getSource();
				// set new child
				addChild(containsControl);
			} else if (e.getSource() instanceof StartsWithControl) {
				// set property
				startsWithControl = (StartsWithControl) e.getSource();
				// set new child
				addChild(startsWithControl);
			} else if (e.getSource() instanceof EndsWithControl) {
				// set property
				endsWithControl = (EndsWithControl) e.getSource();
				// set new child
				addChild(endsWithControl);
			} else if (e.getSource() instanceof ToUpperCaseControl) {
				// set property
				toUpperCaseControl = (ToUpperCaseControl) e.getSource();
				// set new child
				addChild(toUpperCaseControl);
			} else if (e.getSource() instanceof ToLowerCaseControl) {
				// set property
				toLowerCaseControl = (ToLowerCaseControl) e.getSource();
				// set new child
				addChild(toLowerCaseControl);
			}
			break;
		case CONTROL_LOADED:
			// find source's operator message control
			if (e.getSource() instanceof TryParseControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.TRY_PARSE, e.getValue());
				}
			} else if (e.getSource() instanceof SubstringControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.SUBSTRING, e.getValue());
				}
			} else if (e.getSource() instanceof ConcatControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.CONCAT, e.getValue());
				}
			} else if (e.getSource() instanceof ReplaceControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.REPLACE, e.getValue());
				}
			} else if (e.getSource() instanceof TrimControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.TRIM, e.getValue());
				}
			} else if (e.getSource() instanceof EqualsControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.EQUALS, e.getValue());
				}
			} else if (e.getSource() instanceof ContainsControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.CONTAINS, e.getValue());
				}
			} else if (e.getSource() instanceof StartsWithControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.STARTS_WITH, e.getValue());
				}
			} else if (e.getSource() instanceof EndsWithControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.ENDS_WITH, e.getValue());
				}
			} else if (e.getSource() instanceof ToUpperCaseControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.TO_UPPER_CASE, e.getValue());
				}
			} else if (e.getSource() instanceof ToLowerCaseControl) {
				if (getChildren().contains(e.getSource())) {
					// set data message
					put(Data.TO_LOWER_CASE, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}