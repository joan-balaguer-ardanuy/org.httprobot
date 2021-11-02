package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class BannedWordControl 
	extends Control<BannedWord> {

	/**
	 * 5568669737410695966L
	 */
	private static final long serialVersionUID = 5568669737410695966L;
	
	public BannedWordControl() {
		super();
		setMessage(new BannedWord());
	}
	public BannedWordControl(BannedWord message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			BannedWord bannedWord = BannedWord.class.cast(e.getMessage());
			
			if(bannedWord.getKey() == null || bannedWord.getValue() == null) {
				throw new Error("BannedWordControl.OnControlInitialized: Inconsistent BannedWord XML message.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			BannedWord bannedWord = BannedWord.class.cast(e.getMessage());
			put(Data.KEY, bannedWord.getKey());
			put(Data.VALUE, bannedWord.getValue());
			//Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.BANNED_WORD_CONTROL_LOADED));
		}
	}
}