package org.httprobot.placeholder.string;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.string.Concat;

@XmlRootElement
public final class ConcatControl
	extends AbstractStringControl<Concat> {

	/**
	 * 4853020141248338937L
	 */
	private static final long serialVersionUID = 4853020141248338937L;

	public ConcatControl() {
		super();
	}
	public ConcatControl(Concat message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Concat split = Concat.class.cast(e.getMessage());
			
			if(split.getValue() == null) {
				throw new Error("ConcatControl.OnControlInitialized: XML Concat message value missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if (e.getSource().equals(this)) {
			Concat concat = Concat.class.cast(e.getSource());
			
			put(Data.VALUE, concat.getValue());
			// Send event to parent
			SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
		
	}
}