package org.httprobot.unit;

import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

public class WebLoaderControl extends Control<WebLoader> {

	/**
	 * -1373660850780191708L
	 */
	private static final long serialVersionUID = -1373660850780191708L;
	
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
			if(webLoader.getNextPageMethod() == null) {
				throw new Error("WebLoaderControl.OnControlInitialized: GetMethod element is missing.");
			}
		}
	}

	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			put(Data.TIME, getMessage().getTime());
			put(Data.NEXT_PAGE_METHOD, getMessage().getNextPageMethod());
			put(Data.JAVASCRIPT, getMessage().getJavaScript());
			put(Data.NEXT_PAGE_ATTRIBUTE, getMessage().getNextPageAttribute());
			put(Data.NEXT_PAGE_TEXT, getMessage().getNextPageText());
			put(Data.XPATH, getMessage().getXPath());
			put(Data.URL_PATTERN, getMessage().getUrlPattern());
			put(Data.START_INDEX, getMessage().getStartIndex());
			
			
			// Send event to parent
			CommandListenerEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
		}
	}

}
