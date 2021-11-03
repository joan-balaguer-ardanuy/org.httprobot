package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.AbstractPlaceholder;
import org.httprobot.Enums.Data;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.EndsWithControl;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.StartsWithControl;
import org.httprobot.placeholder.string.ConcatControl;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.ToLowerCaseControl;
import org.httprobot.placeholder.string.ToUpperCaseControl;
import org.httprobot.placeholder.string.TryParseControl;

public abstract class AbstractPlaceholderControl<TMessage extends AbstractPlaceholder>
	extends Control<TMessage> {

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
	
	public AbstractPlaceholderControl() {
		super();
	}
	public AbstractPlaceholderControl(TMessage message, ControlListener parent) {
		super(message, parent);
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			
			AbstractPlaceholder placeholder = AbstractPlaceholder.class.cast(e.getMessage());
			
			if (placeholder.getContains() != null) {
				new ContainsControl(placeholder.getContains(), this);
			} else if (placeholder.getEquals() != null) {
				new EqualsControl(placeholder.getEquals(), this);
			} else if (placeholder.getTrim() != null) {
				new TrimControl(placeholder.getTrim(), this);
			} else if (placeholder.getReplace() != null) {
				new ReplaceControl(placeholder.getReplace(), this);
			} else if (placeholder.getConcat() != null) {
				new ConcatControl(placeholder.getConcat(), this);
			} else if (placeholder.getSubstring() != null) {
				new SubstringControl(placeholder.getSubstring(), this);
			} else if (placeholder.getTryParse() != null) {
				new TryParseControl(placeholder.getTryParse(), this);
			} else if (placeholder.getStartsWith() != null) {
				new StartsWithControl(placeholder.getStartsWith(), this);
			} else if (placeholder.getEndsWith() != null) {
				new EndsWithControl(placeholder.getEndsWith(), this);
			} else if (placeholder.getToUpperCase() != null) {
				new ToUpperCaseControl(placeholder.getToUpperCase(), this);
			} else if (placeholder.getToLowerCase() != null) {
				new ToLowerCaseControl(placeholder.getToLowerCase(), this);
			}
		} else if (e.getSource() instanceof TryParseControl) {
			tryParseControl = TryParseControl.class.cast(e.getSource());
			addChildControl(tryParseControl);
		} else if (e.getSource() instanceof SubstringControl) {
			substringControl = SubstringControl.class.cast(e.getSource());
			addChildControl(substringControl);
		} else if (e.getSource() instanceof ConcatControl) {
			concatControl = ConcatControl.class.cast(e.getSource());
			addChildControl(concatControl);
		} else if (e.getSource() instanceof ReplaceControl) {
			replaceControl = ReplaceControl.class.cast(e.getSource());
			addChildControl(replaceControl);
		} else if (e.getSource() instanceof TrimControl) {
			removeControl = TrimControl.class.cast(e.getSource());
			addChildControl(removeControl);
		} else if (e.getSource() instanceof EqualsControl) {
			equalsControl = EqualsControl.class.cast(e.getSource());
			addChildControl(equalsControl);
		} else if (e.getSource() instanceof ContainsControl) {
			containsControl = ContainsControl.class.cast(e.getSource());
			addChildControl(containsControl);
		} else if (e.getSource() instanceof StartsWithControl) {
			startsWithControl = StartsWithControl.class.cast(e.getSource());
			addChildControl(startsWithControl);
		} else if (e.getSource() instanceof EndsWithControl) {
			endsWithControl = EndsWithControl.class.cast(e.getSource());
			addChildControl(endsWithControl);
		} else if (e.getSource() instanceof ToUpperCaseControl) {
			toUpperCaseControl = ToUpperCaseControl.class.cast(e.getSource());
			addChildControl(toUpperCaseControl);
		} else if (e.getSource() instanceof ToLowerCaseControl) {
			toLowerCaseControl = ToLowerCaseControl.class.cast(e.getSource());
			addChildControl(toLowerCaseControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {

			// Check if control has child XML message controls
			if (hasChildControls()) {
				// Iterate through child XML message controls
				while (hasNext()) {
					// Get next child XML message control
					ControlListener control = next();

					if (control instanceof ContainsControl ? 
							containsControl.equals(control) : false) {
						containsControl.loadControl();
					} else if (control instanceof EqualsControl ? 
							equalsControl.equals(control) : false) {
						equalsControl.loadControl();
					} else if (control instanceof TrimControl ? 
							removeControl.equals(control) : false) {
						removeControl.loadControl();
					} else if (control instanceof ReplaceControl ? 
							replaceControl.equals(control) : false) {
						replaceControl.loadControl();
					} else if (control instanceof ConcatControl ? 
							concatControl.equals(control) : false) {
						concatControl.loadControl();
					} else if (control instanceof SubstringControl ? 
							substringControl.equals(control) : false) {
						substringControl.loadControl();
					} else if (control instanceof TryParseControl ? 
							tryParseControl.equals(control) : false) {
						tryParseControl.loadControl();
					} else if (control instanceof StartsWithControl ? 
							startsWithControl.equals(control) : false) {
						startsWithControl.loadControl();
					} else if (control instanceof EndsWithControl ? 
							endsWithControl.equals(control) : false) {
						endsWithControl.loadControl();
					} else if (control instanceof ToUpperCaseControl ? 
							toUpperCaseControl.equals(control) : false) {
						toUpperCaseControl.loadControl();
					} else if (control instanceof ToLowerCaseControl ? 
							toLowerCaseControl.equals(control) : false) {
						toLowerCaseControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
			}
		} else if (e.getSource() instanceof TryParseControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.TRY_PARSE, e.getMessage());
			}
		} else if (e.getSource() instanceof SubstringControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.SUBSTRING, e.getMessage());
			}
		} else if (e.getSource() instanceof ConcatControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONCAT, e.getMessage());
			}
		} else if (e.getSource() instanceof ReplaceControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.REPLACE, e.getMessage());
			}
		} else if (e.getSource() instanceof TrimControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.TRIM, e.getMessage());
			}
		} else if (e.getSource() instanceof EqualsControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.EQUALS, e.getMessage());
			}
		} else if (e.getSource() instanceof ContainsControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTAINS, e.getMessage());
			}
		} else if (e.getSource() instanceof StartsWithControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.STARTS_WITH, e.getMessage());
			}
		} else if (e.getSource() instanceof EndsWithControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.ENDS_WITH, e.getMessage());
			}
		} else if (e.getSource() instanceof ToUpperCaseControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.TO_UPPER_CASE, e.getMessage());
			}
		} else if (e.getSource() instanceof ToLowerCaseControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.TO_LOWER_CASE, e.getMessage());
			}
		}
	}
}