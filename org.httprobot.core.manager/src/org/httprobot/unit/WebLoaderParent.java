package org.httprobot.unit;

import org.httprobot.MappingParent;

import java.util.List;

import org.httprobot.Data;
import org.httprobot.ManagerEventType;
import org.httprobot.NextPageMethod;
import org.httprobot.ParentListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class WebLoaderParent
	extends MappingParent<String, HtmlPage, WebLoaderControl> {

	/**
	 * 7605117314181749897L
	 */
	private static final long serialVersionUID = 7605117314181749897L;

	WebElement nextPageElement;
	Integer pageNumber;
	
	public WebLoaderParent() {
		super();
	}
	public WebLoaderParent(WebLoader message, ParentListener parent) {
		super(message, WebLoaderControl.class, parent);
		pageNumber = message.getStartIndex();
	}
	@Override
	public HtmlPage put(String key, HtmlPage value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		super.put(key, value);
		waitTime();
		
		WebDriver driver = getWebDriver();
		NextPageMethod method = (NextPageMethod) getControl().get(Data.NEXT_PAGE_METHOD);
		
		switch (method) {
		case CLICK_NEXT_ELEMENT:
			setNextPageElement(driver);

			if (nextPageElement != null) {

				Actions action = new Actions(driver);
				clickElementAction(driver, action);

				key = driver.getCurrentUrl();
				value = new HtmlPage(key, driver.findElement(By.xpath("/html")).getAttribute("outerHTML"));

				put(key, value);
			}
			else {
				ManagerEvent(new ManagerEventArgs(this, ManagerEventType.ALL_PAGES_LOADED));
			}
			break;
		case INCREMENTAL_HTTP_GET:
			if (!isNextPageMissing(driver)) {
				String url = key + ((String) getControl().get(Data.URL_PATTERN)).replace("[#]", pageNumber.toString());
				pageNumber++;
				driver.get(url);
				value = new HtmlPage(url, driver.findElement(By.xpath("/html")).getAttribute("outerHTML"));
				put(key, value);
			} else {
				ManagerEvent(new ManagerEventArgs(this, ManagerEventType.ALL_PAGES_LOADED));
			}
			break;
		default:
			break;
		}
		return null;
	}
	private boolean isNextPageMissing(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath((String) getControl().get(Data.XPATH)));
		if (elements.size() > 0) {
			WebElement element = elements.get(0);
			if (element.isDisplayed()) {
				if(getControl().get(Data.NEXT_PAGE_TEXT) != null) {
					if (element.getText().contains((String) getControl().get(Data.NEXT_PAGE_TEXT))) {
						return false;
					} else {
						return true;
					}
				}
				else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	private void setNextPageElement(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath((String) getControl().get(Data.XPATH)));
		if (elements.size() > 0) {
			WebElement element = elements.get(0);
			if (element.isDisplayed()) {
				if (getControl().get(Data.NEXT_PAGE_TEXT) != null) {
					if (element.getText().contains((String) getControl().get(Data.NEXT_PAGE_TEXT))) {
						nextPageElement = element;
					} else {
						nextPageElement = null;
					}
				} else {
					nextPageElement = element;
				}
			} else {
				nextPageElement = null;
			}
		} else {
			nextPageElement = null;
		}
	}
	private void clickElementAction(WebDriver driver, Actions action) {
		try {			
			if (getControl().get(Data.JAVASCRIPT) != null) {
				((JavascriptExecutor) driver).executeScript((String) getControl().get(Data.JAVASCRIPT), nextPageElement);
			}
			waitTime();

			action.moveToElement(nextPageElement).click().perform();
		} catch (MoveTargetOutOfBoundsException ex) {
			// ignore this exception, repeat the process
			clickElementAction(driver, action);
		} catch(StaleElementReferenceException ex) {
			return;
		}
	}
	public void waitTime() {
		try {
			Thread.sleep((Integer) getControl().get(Data.TIME));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			break;
		case FINISHED:
			
			break;
		default:
			break;
		}
	}
}