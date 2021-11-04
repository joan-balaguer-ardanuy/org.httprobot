package org.httprobot.placeholder;

import java.net.URL;

import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

public class HttpAddressManager 
	extends AbstractPlaceholderManager<URL, HttpAddressControl> {

	/**
	 * 5782528861553476045L
	 */
	private static final long serialVersionUID = 5782528861553476045L;
	
	public HttpAddressManager() {
		super();
	}
	public HttpAddressManager(HttpAddress message, Manager<?> parent) {
		super(message, HttpAddressControl.class, parent);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		super.OnCommandReceived(e);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}