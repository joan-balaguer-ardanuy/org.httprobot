package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class SubstringControl
	extends AbstractStringControl<Substring> {

	/**
	 * -8786207254600689526L
	 */
	private static final long serialVersionUID = -8786207254600689526L;
	
	StartIndexControl startIndexControl;
	EndIndexControl endIndexControl;
	
	@XmlElement
	public StartIndexControl getStartIndexControl() {
		return startIndexControl;
	}
	public void setStartIndexControl(StartIndexControl startIndexControl) {
		this.startIndexControl = startIndexControl;
	}
	@XmlElement
	public EndIndexControl getEndIndexControl() {
		return endIndexControl;
	}
	public void setEndIndexControl(EndIndexControl endIndexControl) {
		this.endIndexControl = endIndexControl;
	}
	
	public SubstringControl() {
		super();
		setMessage(new Substring());
	}
	public SubstringControl(Substring message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if (e.getSource().equals(this)) {

			Substring substring = Substring.class.cast(e.getMessage());

			if (substring.getStartIndex() != null) {
				new StartIndexControl(substring.getStartIndex(), this);
			} else {
				throw new Error("SubstringControl.OnControlInitialized: StartIndex XML element message is missing.");
			}
			if (substring.getEndIndex() != null) {
				new EndIndexControl(substring.getEndIndex(), this);
			} else {
				throw new Error("SubstringControl.OnControlInitialized: EndIndex XML element message is missing.");
			}
		} else if(e.getSource() instanceof StartIndexControl) {
			startIndexControl = StartIndexControl.class.cast(e.getSource());
			addChildControl(startIndexControl);
		} else if(e.getSource() instanceof EndIndexControl) {
			endIndexControl = EndIndexControl.class.cast(e.getSource());
			addChildControl(endIndexControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {

			// Check if control has child XML controls
			if (hasChildControls()) {
				// Iterate through child controls
				while (hasNext()) {
					// Get next XML child control
					ControlListener control = next();

					if (control instanceof StartIndexControl ? startIndexControl.equals(control) : false) {
						startIndexControl.loadControl();
					} else if (control instanceof EndIndexControl ? endIndexControl.equals(control) : false) {
						endIndexControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.SUBSTRING_CONTROL_LOADED));
			}
		} else if(e.getSource() instanceof StartIndexControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.START_INDEX, e.getMessage());
			}
		} else if(e.getSource() instanceof EndIndexControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.END_INDEX, e.getMessage());
			}
		}
	}
}