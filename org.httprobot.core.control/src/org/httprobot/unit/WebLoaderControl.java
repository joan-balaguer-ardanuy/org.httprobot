package org.httprobot.unit;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

public class WebLoaderControl extends Control<WebLoader> {

	/**
	 * -1373660850780191708L
	 */
	private static final long serialVersionUID = -1373660850780191708L;

	PaginatorControl paginatorControl;
	
	public WebLoaderControl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			WebLoader webLoader = WebLoader.class.cast(e.getMessage());
			
			if(webLoader.getTime() == null) {
				throw new Error("WebLoaderControl.OnControlInitialized: Time element is missing.");
			}
			if(webLoader.getPaginator() != null) {
				new PaginatorControl(webLoader.getPaginator(), this);
			} else {
				throw new Error("ActionControl.OnControlInitialized: WebOptions XML message element expected.");
			}
		} else if (e.getSource() instanceof PaginatorControl) {
			paginatorControl = PaginatorControl.class.cast(e.getSource());
			addChildControl(paginatorControl);
		}
	}

	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			put(Data.TIME, getMessage().getTime());

			// Check if control has child XML message controls
			if (hasChildControls()) {
				// Iterate through child XML message controls
				while (hasNext()) {
					// Get next child XML message control
					ControlListener control = next();

					if (control instanceof PaginatorControl ? 
							paginatorControl.equals(control) : false) {
						paginatorControl.loadControl();
					}
					// Set control ready to be iterated again.
					reset();
					// Send event to parent
					CommandLineEvent(new CommandEventArgs(this, Command.WEB_LOADER_CONTROL_LOADED));
				}
			}
		} else if (e.getSource() instanceof PaginatorControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.PAGINATOR, e.getMessage());
			}
		}
	}

}
