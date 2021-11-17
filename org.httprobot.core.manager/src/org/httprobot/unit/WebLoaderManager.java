package org.httprobot.unit;

import org.httprobot.Manager;

import java.io.IOException;
import java.net.URL;

import org.httprobot.Enums.Data;
import org.httprobot.ManagerListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class WebLoaderManager
	extends Manager<URL, WebElement, WebLoaderControl> {

	/**
	 * 7605117314181749897L
	 */
	private static final long serialVersionUID = 7605117314181749897L;

	WebElement nextPageAnchor;
	PaginatorManager paginatorManager;
	
	public WebLoaderManager() {
		super();
	}
	public WebLoaderManager(WebLoader message, ManagerListener parent) {
		super(message, WebLoaderControl.class, parent);
		paginatorManager = new PaginatorManager(message.getPaginator(), this);
		setWebDriver(new FirefoxDriver());
	}

	@Override
	public WebElement put(URL key, WebElement value) {
		if (value == null) {
			value = getPage(key);
			
			paginatorManager.put(value, null);

			waitPeriodTime();

			put(key, value);
		} else {
			super.put(key, value);

			if (nextPageAnchor != null) {
				try {
					Actions action = new Actions(getWebDriver());
					action.moveToElement(nextPageAnchor).click().perform();

					key = new URL(nextPageAnchor.getAttribute("href"));
					value = getWebDriver().findElement(By.xpath("/html"));

					paginatorManager.put(value, null);

					waitPeriodTime();

					put(key, value);

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
	public WebElement getPage(URL request) {
		setWebDriver(new FirefoxDriver());
		getWebDriver().get(request.toString());
		WebElement output = getWebDriver().findElement(By.xpath("/html"));
		getWebDriver().quit();
		return output;
	}
}
