package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class SplitControl
	extends AbstractOperatorControl<Split> {

	/**
	 * 4853020141248338937L
	 */
	private static final long serialVersionUID = 4853020141248338937L;

	DelimiterControl delimiterControl;
	
	@XmlElement
	public DelimiterControl getDelimiterControl() {
		return delimiterControl;
	}
	public void setDelimiterControl(DelimiterControl delimiterControl) {
		this.delimiterControl = delimiterControl;
	}

	public SplitControl() {
		super();
		setMessage(new Split());
	}
	public SplitControl(Split message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Split split = Split.class.cast(e.getMessage());
			
			if(split.getDelimiter() != null) {
				new DelimiterControl(split.getDelimiter(), this);
			}
			else {
				throw new Error("SplitControl.OnControlInitialized: Delimiter XML message missing.");
			}
		} else if(e.getSource() instanceof DelimiterControl) {
			delimiterControl = DelimiterControl.class.cast(e.getSource());
			addChildControl(delimiterControl);
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
					
					if(control instanceof DelimiterControl ?
							delimiterControl.equals(control) : false) {
						delimiterControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.SPLIT_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof DelimiterControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.DELIMITER, e.getMessage());
			}
		}
	}
}