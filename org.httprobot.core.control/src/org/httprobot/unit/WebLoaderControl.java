package org.httprobot.unit;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

public class WebLoaderControl extends AbstractControl {

	/**
	 * -1373660850780191708L
	 */
	private static final long serialVersionUID = -1373660850780191708L;
	
	@Override
	public WebLoader getMessage() {
		return (WebLoader) super.getMessage();
	}
	
	public WebLoaderControl() {
		super();
	}
	public WebLoaderControl(WebLoader message, Control parent) {
		super(message, parent);
	}

	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				WebLoader webLoader = WebLoader.class.cast(e.getValue());
				
				if(webLoader.getTime() == null) {
					throw new Error("WebLoaderControl.OnEventReceived: Time element is missing.");
				}
				if(webLoader.getNextPageMethod() == null) {
					throw new Error("WebLoaderControl.OnEventReceived: GetMethod element is missing.");
				}
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				put(Data.TIME, getMessage().getTime());
				put(Data.NEXT_PAGE_METHOD, getMessage().getNextPageMethod());
				put(Data.JAVASCRIPT, getMessage().getJavaScript());
				put(Data.NEXT_PAGE_ATTRIBUTE, getMessage().getNextPageAttribute());
				put(Data.NEXT_PAGE_TEXT, getMessage().getNextPageText());
				put(Data.XPATH, getMessage().getXPath());
				put(Data.URL_PATTERN, getMessage().getUrlPattern());
				put(Data.START_INDEX, getMessage().getStartIndex());
			}
			break;
		default:
			break;
		}
	}
}
