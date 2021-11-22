package org.httprobot.unit;

import org.httprobot.Manager;

import java.io.IOException;
import java.net.URL;

import org.httprobot.Enums.Data;
import org.httprobot.ManagerListener;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxDriver.Capability;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

public class WebLoaderManager
	extends Manager<URL, WebElement, WebLoaderControl> {

	/**
	 * 7605117314181749897L
	 */
	private static final long serialVersionUID = 7605117314181749897L;

	WebElement nextPageElement;
	PaginatorManager paginatorManager;
	
	public WebLoaderManager() {
		super();
	}
	public WebLoaderManager(WebLoader message, ManagerListener parent) {
		super(message, WebLoaderControl.class, parent);
		paginatorManager = new PaginatorManager(message.getPaginator(), this);
	}
	private void instanceWebDriver() {
		WebDriver driver;
		if ((Boolean) getControl().get(Data.DISALLOW_IMAGES)) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("permissions.default.image", 2);
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			options.setCapability(Capability.PROFILE, profile);
			driver = new FirefoxDriver(options);
		} else {
			driver = new FirefoxDriver();
		}
		setWebDriver(driver);
	}

	@Override
	public WebElement put(URL key, WebElement value) {
		if (value == null) {
			value = getPage(key);
			
			paginatorManager.put(value, null);

			waitPeriodTime();

			put(key, value);
		} else {
			keySet().add(key);
			setKey(key);
			setValue(value);
			super.put(key, value);

			if (nextPageElement != null) {
				try {
					WebDriver driver = getWebDriver();
					Actions action = new Actions(driver);
					
					clickElementAction(driver, action);

					key = new URL(nextPageElement.getAttribute((String) getControl().get(Data.NEXT_PAGE_ATTRIBUTE)));
					value = driver.findElement(By.xpath("/html"));

					paginatorManager.put(value, null);

					put(key, value);

				} catch (IOException e) {
					throw new Error("WebLoaderManager.put: bad next page anchor html element.", e);
				}
			}
		}
		return null;
	}
	private void clickElementAction(WebDriver driver, Actions action) {
		try {
			String script = getControl().get(Data.JAVASCRIPT) != null ?
					(String) getControl().get(Data.JAVASCRIPT) : null;
			
			if (script != null) {
				((JavascriptExecutor) driver).executeScript(script, nextPageElement);
			}
			waitPeriodTime();

			action.moveToElement(nextPageElement).click().perform();
		} catch (MoveTargetOutOfBoundsException ex) {
			// ignore this exception, repeat the process
			clickElementAction(driver, action);
		} catch(StaleElementReferenceException ex) {
			return;
		}
	}
	public void waitPeriodTime() {
		try {
			Thread.sleep((Integer) getControl().get(Data.TIME));
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
			if(e.getSource().equals(this)) {
				instanceWebDriver();
			}
			break;
		case FINISHED:
			if(e.getSource().equals(paginatorManager)) {
				nextPageElement = paginatorManager.getValue();
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
		return output;
	}
}
