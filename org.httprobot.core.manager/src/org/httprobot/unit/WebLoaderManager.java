package org.httprobot.unit;

import org.httprobot.MapManager;

import org.httprobot.Data;
import org.httprobot.NextPageMethod;
import org.httprobot.ManagerListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class WebLoaderManager
	extends MapManager<String, WebDocument, WebLoaderControl> {

	/**
	 * 7605117314181749897L
	 */
	private static final long serialVersionUID = 7605117314181749897L;

	WebElement nextPageElement;
	Integer pageNumber;
	
	public WebLoaderManager() {
		super();
	}
	public WebLoaderManager(WebLoader message, ManagerListener parent) {
		super(message, WebLoaderControl.class, parent);
		pageNumber = message.getStartIndex();
	}
	@Override
	public WebDocument put(String key, WebDocument value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		super.put(key, value);
		waitTime();
		
		WebDriver driver = getWebDriver().getWebDriver();
		NextPageMethod method = (NextPageMethod) getControl().get(Data.NEXT_PAGE_METHOD);
		
		switch (method) {
		case CLICK_NEXT_ELEMENT:
			findNextElement(driver);

			if (nextPageElement != null) {

				Actions action = new Actions(driver);
				clickElementAction(driver, action);

				key = nextPageElement.getAttribute((String) getControl().get(Data.NEXT_PAGE_ATTRIBUTE));
				value = new WebDocument(key, driver.findElement(By.xpath("/html")).getAttribute("outerHTML"));

				put(key, value);
			}
			break;
		case INCREMENTAL_HTTP_GET:
			String url = key + ((String) getControl().get(Data.URL_PATTERN)).replace("[#]", pageNumber.toString());;
			pageNumber++;
			driver.get(url);
			value = new WebDocument(url, driver.findElement(By.xpath("/html")).getAttribute("outerHTML"));
			put(key, value);
			break;
		default:
			break;
		}
		return null;
	}
	private void findNextElement(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath((String) getControl().get(Data.XPATH)));
		if (element.isDisplayed() || element.isEnabled()) {
			if (element.getText().contains((String) getControl().get(Data.NEXT_PAGE_TEXT))) {
				nextPageElement = element;
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
	public void OnManagerEvent(ManagerEventArgs e) {
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