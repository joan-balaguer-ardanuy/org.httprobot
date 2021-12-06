package org.httprobot;

import java.util.Map;

import org.httprobot.configuration.Selenium;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

/**
 * Manager listener interface. Inherits {@link MessageListener}.
 * All implementing instances will be able to listen any XML message manager.
 * @author joan
 *
 */
public interface ManagerListener extends MessageListener {
	
	/**
	 * Starts {@link ManagerListener}.
	 */
	void start();
	/**
	 * Stops {@link ManagerListener}
	 */
	void stop();
	
	/**
	 * Gets the parent {@link ManagerListener}.
	 * @return the parent {@link ManagerListener}.
	 */
	ManagerListener getParent();
	/**
	 * Sets the parent {@link ManagerListener}.
	 * @param parent {@link ManagerListener} the parent to be set.
	 */
	void setParent(ManagerListener parent);

	Selenium getSelenium();
	void setSelenium(Selenium robot);
	
	/**
	 * Returns the {@link ContentTypeRoot} set in {@code source} configuration XML message.
	 * @return the {@link ContentTypeRoot} set in {@code source} configuration XML message.
	 */
	ContentTypeRoot getContentTypeRoot();
	void setContentTypeRoot(ContentTypeRoot contentTypeRoot);
	
	DocumentLibrary getDocumentLibrary();
	void setDocumentLibrary(DocumentLibrary documentLibrary);
	
	TemplateLibrary getTemplateLibrary();
	void setTemplateLibrary(TemplateLibrary templateLibrary);
	
	Map<String,String> getConstants();
	void setConstants(Map<String,String> constants);
	
	void OnManagerEvent(ManagerEventArgs e);
}