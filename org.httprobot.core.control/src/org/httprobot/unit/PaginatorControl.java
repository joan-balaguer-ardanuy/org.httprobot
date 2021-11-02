package org.httprobot.unit;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class PaginatorControl 
	extends Control<Paginator> {

	/**
	 * 5730806131276434307L
	 */
	private static final long serialVersionUID = 5730806131276434307L;
	
	public PaginatorControl() {
		super();
		setMessage(new Paginator());
	}
	public PaginatorControl(Paginator message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this))
		{
			Paginator paginator = Paginator.class.cast(e.getMessage());
			
			if(paginator.getAnchorValue() == null || paginator.getPaginatorIncrement() == null) {
				throw new Error("PaginatorControl.OnControlInitialized: Paginator XML message missing fields");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this))
		{
			Paginator paginator = Paginator.class.cast(e.getMessage());
			
			if(paginator.getUuid() != null)
			{
				put(Data.PAGINATOR_ANCHOR, paginator.getAnchorValue());
				put(Data.PAGINATOR_HREF, paginator.getAnchorHrefAttributeValue());
				put(Data.PAGINATOR_INCREMENT, paginator.getPaginatorIncrement());
				put(Data.PAGINATOR_URL_PATTERN, paginator.getPaginatorUrlPattern());
				//Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.PAGINATOR_CONTROL_LOADED));
			}
			else
			{
				throw new Error("PaginatorControl.OnControlLoaded: UUID cannot be null.");
			}
		}
	}
}