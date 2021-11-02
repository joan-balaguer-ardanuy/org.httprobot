package org.httprobot.net;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;

public class Client 
	extends WebClient 
		implements WebWindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6223546451607661205L;

	public Client(BrowserVersion browserVersion) {
		super(browserVersion);
	}

	@Override
	public void webWindowOpened(WebWindowEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webWindowContentChanged(WebWindowEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void webWindowClosed(WebWindowEvent event) {
		// TODO Auto-generated method stub
		
	}

}
