package org.httprobot.operator;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.AbstractOperator;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.string.ConcatControl;
import org.httprobot.operator.string.ContainsControl;
import org.httprobot.operator.string.EndsWithControl;
import org.httprobot.operator.string.EqualsControl;
import org.httprobot.operator.string.ReplaceControl;
import org.httprobot.operator.string.StartsWithControl;
import org.httprobot.operator.string.SubstringControl;
import org.httprobot.operator.string.ToLowerCaseControl;
import org.httprobot.operator.string.ToUpperCaseControl;
import org.httprobot.operator.string.TrimControl;
import org.httprobot.operator.string.TryParseControl;

public abstract class AbstractOperatorControl<T extends AbstractOperator>
	extends AbstractControl<T> {

	/**
	 * -138070185044904871L
	 */
	private static final long serialVersionUID = -138070185044904871L;

	ContainsControl containsControl;
	EqualsControl equalsControl;
	TrimControl removeControl;
	ReplaceControl replaceControl;
	ConcatControl concatControl;
	SubstringControl substringControl;
	TryParseControl tryParseControl;
	StartsWithControl startsWithControl;
	EndsWithControl endsWithControl;
	ToUpperCaseControl toUpperCaseControl;
	ToLowerCaseControl toLowerCaseControl;
	
	@XmlElement
	public ContainsControl getContainsControl() {
		return containsControl;
	}
	public void setContainsControl(ContainsControl containsControl) {
		this.containsControl = containsControl;
	}
	@XmlElement
	public EqualsControl getEqualsControl() {
		return equalsControl;
	}
	public void setEqualsControl(EqualsControl equalsControl) {
		this.equalsControl = equalsControl;
	}
	@XmlElement
	public TrimControl getRemoveControl() {
		return removeControl;
	}
	public void setRemoveControl(TrimControl removeControl) {
		this.removeControl = removeControl;
	}
	@XmlElement
	public ReplaceControl getReplaceControl() {
		return replaceControl;
	}
	public void setReplaceControl(ReplaceControl replaceControl) {
		this.replaceControl = replaceControl;
	}
	@XmlElement
	public ConcatControl getConcatControl() {
		return concatControl;
	}
	public void setConcatControl(ConcatControl concatControl) {
		this.concatControl = concatControl;
	}
	@XmlElement
	public SubstringControl getSubstringControl() {
		return substringControl;
	}
	public void setSubstringControl(SubstringControl substringControl) {
		this.substringControl = substringControl;
	}
	@XmlElement
	public TryParseControl getTryParseControl() {
		return tryParseControl;
	}
	public void setTryParseControl(TryParseControl tryParseControl) {
		this.tryParseControl = tryParseControl;
	}
	@XmlElement
	public StartsWithControl getStartsWithControl() {
		return startsWithControl;
	}
	public void setStartsWithControl(StartsWithControl startsWithControl) {
		this.startsWithControl = startsWithControl;
	}
	@XmlElement
	public EndsWithControl getEndsWithControl() {
		return endsWithControl;
	}
	public void setEndsWithControl(EndsWithControl endsWithControl) {
		this.endsWithControl = endsWithControl;
	}
	@XmlElement
	public ToUpperCaseControl getToUpperCaseControl() {
		return toUpperCaseControl;
	}
	public void setToUpperCaseControl(ToUpperCaseControl toUpperCaseControl) {
		this.toUpperCaseControl = toUpperCaseControl;
	}
	@XmlElement
	public ToLowerCaseControl getToLowerCaseControl() {
		return toLowerCaseControl;
	}
	public void setToLowerCaseControl(ToLowerCaseControl toLowerCaseControl) {
		this.toLowerCaseControl = toLowerCaseControl;
	}
	
	public AbstractOperatorControl() {
		super();
	}
	public AbstractOperatorControl(T message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				
				AbstractOperator operator = AbstractOperator.class.cast(e.getValue());
				
				if (operator.getContains() != null) {
					new ContainsControl(operator.getContains(), this);
				} else if (operator.getEquals() != null) {
					new EqualsControl(operator.getEquals(), this);
				} else if (operator.getTrim() != null) {
					new TrimControl(operator.getTrim(), this);
				} else if (operator.getReplace() != null) {
					new ReplaceControl(operator.getReplace(), this);
				} else if (operator.getConcat() != null) {
					new ConcatControl(operator.getConcat(), this);
				} else if (operator.getSubstring() != null) {
					new SubstringControl(operator.getSubstring(), this);
				} else if (operator.getTryParse() != null) {
					new TryParseControl(operator.getTryParse(), this);
				} else if (operator.getStartsWith() != null) {
					new StartsWithControl(operator.getStartsWith(), this);
				} else if (operator.getEndsWith() != null) {
					new EndsWithControl(operator.getEndsWith(), this);
				} else if (operator.getToUpperCase() != null) {
					new ToUpperCaseControl(operator.getToUpperCase(), this);
				} else if (operator.getToLowerCase() != null) {
					new ToLowerCaseControl(operator.getToLowerCase(), this);
				}
			} else if (e.getSource() instanceof TryParseControl) {
				tryParseControl = TryParseControl.class.cast(e.getSource());
				addChild(tryParseControl);
			} else if (e.getSource() instanceof SubstringControl) {
				substringControl = SubstringControl.class.cast(e.getSource());
				addChild(substringControl);
			} else if (e.getSource() instanceof ConcatControl) {
				concatControl = ConcatControl.class.cast(e.getSource());
				addChild(concatControl);
			} else if (e.getSource() instanceof ReplaceControl) {
				replaceControl = ReplaceControl.class.cast(e.getSource());
				addChild(replaceControl);
			} else if (e.getSource() instanceof TrimControl) {
				removeControl = TrimControl.class.cast(e.getSource());
				addChild(removeControl);
			} else if (e.getSource() instanceof EqualsControl) {
				equalsControl = EqualsControl.class.cast(e.getSource());
				addChild(equalsControl);
			} else if (e.getSource() instanceof ContainsControl) {
				containsControl = ContainsControl.class.cast(e.getSource());
				addChild(containsControl);
			} else if (e.getSource() instanceof StartsWithControl) {
				startsWithControl = StartsWithControl.class.cast(e.getSource());
				addChild(startsWithControl);
			} else if (e.getSource() instanceof EndsWithControl) {
				endsWithControl = EndsWithControl.class.cast(e.getSource());
				addChild(endsWithControl);
			} else if (e.getSource() instanceof ToUpperCaseControl) {
				toUpperCaseControl = ToUpperCaseControl.class.cast(e.getSource());
				addChild(toUpperCaseControl);
			} else if (e.getSource() instanceof ToLowerCaseControl) {
				toLowerCaseControl = ToLowerCaseControl.class.cast(e.getSource());
				addChild(toLowerCaseControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource() instanceof TryParseControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.TRY_PARSE, e.getValue());
				}
			} else if (e.getSource() instanceof SubstringControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.SUBSTRING, e.getValue());
				}
			} else if (e.getSource() instanceof ConcatControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONCAT, e.getValue());
				}
			} else if (e.getSource() instanceof ReplaceControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.REPLACE, e.getValue());
				}
			} else if (e.getSource() instanceof TrimControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.TRIM, e.getValue());
				}
			} else if (e.getSource() instanceof EqualsControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.EQUALS, e.getValue());
				}
			} else if (e.getSource() instanceof ContainsControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTAINS, e.getValue());
				}
			} else if (e.getSource() instanceof StartsWithControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.STARTS_WITH, e.getValue());
				}
			} else if (e.getSource() instanceof EndsWithControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.ENDS_WITH, e.getValue());
				}
			} else if (e.getSource() instanceof ToUpperCaseControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.TO_UPPER_CASE, e.getValue());
				}
			} else if (e.getSource() instanceof ToLowerCaseControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.TO_LOWER_CASE, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}