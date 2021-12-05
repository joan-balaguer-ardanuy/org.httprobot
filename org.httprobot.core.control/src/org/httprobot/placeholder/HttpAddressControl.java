package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class HttpAddressControl 
	extends AbstractPlaceholderControl<HttpAddress> {

	/**
	 * 1246127918758222588L
	 */
	private static final long serialVersionUID = 1246127918758222588L;

	public HttpAddressControl() {
		super();
	}
	public HttpAddressControl(HttpAddress message, ControlListener parent) {
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
		CommandLineEvent(new CommandEventArgs(this, Command.HTTP_ADDRESS_CONTROL_LOADED));
	}
}