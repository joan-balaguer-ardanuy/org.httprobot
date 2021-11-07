package org.httprobot.unit;

import org.httprobot.MappingManager;

import java.io.IOException;

import org.httprobot.Enums.Data;
import org.httprobot.Manager;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.Client;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebLoaderManager
	extends MappingManager<WebRequest, HtmlPage, WebLoaderControl> {

	/**
	 * 7605117314181749897L
	 */
	private static final long serialVersionUID = 7605117314181749897L;

	Client client;
	HtmlAnchor nextPageAnchor;
	PaginatorManager paginatorManager;
	
	public WebLoaderManager() {
		super();
	}
	public WebLoaderManager(WebLoader message, Manager<?> parent) {
		super(message, WebLoaderControl.class, parent);
		paginatorManager = new PaginatorManager(message.getPaginator(), this);
	}

	@Override
	public HtmlPage put(WebRequest key, HtmlPage value) {
		if (value == null) {
			keySet().add(key);
			setKey(key);
			setValue(getPage(key));
			
			paginatorManager.put(getValue(), null);

			waitPeriodTime();

			put(getKey(), getValue());
		} else {
			keySet().add(key);
			setKey(key);
			setValue(value);
			super.put(key, value);

			if (nextPageAnchor != null) {
				try {
					setValue(nextPageAnchor.click());
					setKey(new WebRequest(getValue().getUrl()));

					paginatorManager.put(getValue(), null);

					waitPeriodTime();

					put(getKey(), getValue());

				} catch (IOException e) {
					throw new Error("WebLoaderManager.put: bad next page anchor html element.", e);
				}
			}
		}
		return null;
	}
	public void waitPeriodTime() {
		try {
			Thread.sleep(getControl().getMessage().getTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case PAGINATOR_CONTROL_LOADED:
			if(e.getSource() instanceof PaginatorControl) {
				Paginator paginator = PaginatorControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.PAGINATOR).equals(paginator)) {
					paginatorManager = new PaginatorManager();
					addChildManager(paginatorManager);
				}
			}
			break;

		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			
			break;
		case FINISHED:
			if(e.getSource().equals(paginatorManager)) {
				nextPageAnchor = paginatorManager.getValue();
			}
			break;
		default:
			break;
		}
	}
	public HtmlPage getPage(WebRequest request) {
		client = new Client(getControl().getMessage().getBrowserVersion());
		client.getOptions().setUseInsecureSSL(true);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		try {
			HtmlPage output = client.getPage(request);
			client.close();
			return output;
		} catch (FailingHttpStatusCodeException | IOException e) {
			return null;
		}
	}
}
