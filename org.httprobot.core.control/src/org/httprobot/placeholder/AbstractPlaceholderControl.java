package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.AbstractPlaceholder;
import org.httprobot.Enums.Data;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.string.ContainsControl;
import org.httprobot.placeholder.string.EqualsControl;
import org.httprobot.placeholder.string.TrimControl;
import org.httprobot.placeholder.string.ReplaceControl;
import org.httprobot.placeholder.string.ConcatControl;
import org.httprobot.placeholder.string.SubstringControl;
import org.httprobot.placeholder.string.TryParseControl;

public abstract class AbstractPlaceholderControl<TMessage extends AbstractPlaceholder>
	extends Control<TMessage> {

	/**
	 * -138070185044904871L
	 */
	private static final long serialVersionUID = -138070185044904871L;

	protected ContainsControl containsControl;
	protected EqualsControl equalsControl;
	protected TrimControl removeControl;
	protected ReplaceControl replaceControl;
	protected ConcatControl splitControl;
	protected SubstringControl substringControl;
	protected TryParseControl tryParseControl;
	
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
	public ConcatControl getSplitControl() {
		return splitControl;
	}
	public void setSplitControl(ConcatControl splitControl) {
		this.splitControl = splitControl;
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
			
			if(placeholder.getContains() != null) {
				new ContainsControl(placeholder.getContains(), this);
			}
			else if(placeholder.getEquals() != null) {
				new EqualsControl(placeholder.getEquals(), this);
			}
			else if(placeholder.getTrim() != null) {
				new TrimControl(placeholder.getTrim(), this);
			}
			else if(placeholder.getReplace() != null) {
				new ReplaceControl(placeholder.getReplace(), this);
			}
			else if(placeholder.getSplit() != null) {
				new ConcatControl(placeholder.getSplit(), this);
			}
			else if(placeholder.getSubstring() != null) {
				new SubstringControl(placeholder.getSubstring(), this);
			}
			else if(placeholder.getTryParse() != null) {
				new TryParseControl(placeholder.getTryParse(), this);
			}
		} else if(e.getSource() instanceof TryParseControl) {
			tryParseControl = TryParseControl.class.cast(e.getSource());
			addChildControl(tryParseControl);
		} else if(e.getSource() instanceof SubstringControl) {
			substringControl = SubstringControl.class.cast(e.getSource());
			addChildControl(substringControl);
		} else if(e.getSource() instanceof ConcatControl) {
			splitControl = ConcatControl.class.cast(e.getSource());
			addChildControl(splitControl);
		} else if(e.getSource() instanceof ReplaceControl) {
			replaceControl = ReplaceControl.class.cast(e.getSource());
			addChildControl(replaceControl);
		} else if(e.getSource() instanceof TrimControl) {
			removeControl = TrimControl.class.cast(e.getSource());
			addChildControl(removeControl);
		} else if(e.getSource() instanceof EqualsControl) {
			equalsControl = EqualsControl.class.cast(e.getSource());
			addChildControl(equalsControl);
		} else if(e.getSource() instanceof ContainsControl) {
			containsControl = ContainsControl.class.cast(e.getSource());
			addChildControl(containsControl);
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
							splitControl.equals(control) : false) {
						splitControl.loadControl();
					} else if (control instanceof SubstringControl ? 
							substringControl.equals(control) : false) {
						substringControl.loadControl();
					} else if (control instanceof TryParseControl ? 
							tryParseControl.equals(control) : false) {
						tryParseControl.loadControl();
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
				put(Data.SPLIT, e.getMessage());
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
		}
	}
}