package org.httprobot;

import java.util.Map;

import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.openqa.selenium.WebDriver;

/**
 * The parent interface. Parent instances will map keys to values
 * and execute whole function of the program.
 * @author joan
 *
 */
public interface Parent extends Listener<Parent> {
	/**
	 * Parent start method.
	 */
	void start();
	/**
	 * Parent stop method.
	 */
	void stop();
	/**
	 * Returns the {@link ContentTypeRoot} property.
	 * @return the {@link ContentTypeRoot} property.
	 */
	ContentTypeRoot getContentTypeRoot();
	/**
	 * Sets the {@link ContentTypeRoot} property.
	 * @param contentTypeRoot the {@link ContentTypeRoot} property.
	 */
	void setContentTypeRoot(ContentTypeRoot contentTypeRoot);
	/**
	 * Returns the {@link DocumentLibrary} property.
	 * @return the {@link DocumentLibrary} property.
	 */
	DocumentLibrary getDocumentLibrary();
	/**
	 * Sets the {@link DocumentLibrary} property.
	 * @param documentLibrary the {@link DocumentLibrary} property.
	 */
	void setDocumentLibrary(DocumentLibrary documentLibrary);
	/**
	 * Returns the {@link TemplateLibrary} property.
	 * @return the {@link TemplateLibrary} property.
	 */
	TemplateLibrary getTemplateLibrary();
	/**
	 * Sets the {@link TemplateLibrary} property.
	 * @param templateLibrary the {@link TemplateLibrary} property.
	 */
	void setTemplateLibrary(TemplateLibrary templateLibrary);
	
	/**
	 * Returns the {@link Map} of constants.
	 * @return the {@link Map} of constants.
	 */
	Map<String, String> getConstants();
	/**
	 * Returns the {@link Map} of constants.
	 * @param constants the {@link Map} of constants.
	 */
	void setConstants(Map<String, String> constants);
	/**
	 * Returns the WebDriver driver of the robot.
	 * @return the WebDriver driver of the robot.
	 */
	WebDriver getWebDriver();
	/**
	 * Sets the WebDriver driver of the robot.
	 * @param webDriver {@link WebDriver} the driver of the robot.
	 */
	void setWebDriver(WebDriver webDriver);
}