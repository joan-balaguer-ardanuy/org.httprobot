package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.operator.Url;

@XmlRootElement
public final class HttpAddressControl 
	extends AbstractPlaceholderControl<Url> {

	/**
	 * 1246127918758222588L
	 */
	private static final long serialVersionUID = 1246127918758222588L;

	public HttpAddressControl() {
		super();
	}
	public HttpAddressControl(Url message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		// Send event to parent
		SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
	}
}