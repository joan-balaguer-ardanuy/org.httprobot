package org.httprobot;

import java.util.Map;

import org.apache.solr.common.SolrInputDocument;
import org.httprobot.configuration.Selenium;
import org.httprobot.configuration.Source;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebDriver;

/**
 * Manager listener interface. Inherits {@link Message}.
 * All implementing instances will be able to listen any XML message manager.
 * @author joan
 *
 */
public interface ManagerListener extends Message {
	
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

	/**
	 * Returns the Selenium's {@link WebDriver}.
	 * @return the Selenium's {@link WebDriver}.
	 */
	WebDriver getWebDriver();
	/**
	 * Sets the {@link Selenium} configurationa XML message.
	 * @param selenium the {@link Selenium} configurationa XML message.
	 */
	void setWebDriver(WebDriver webDriver);
	
	/**
	 * Returns the {@link ContentTypeRoot} set in {@link Source} configuration XML message.
	 * @return the {@link ContentTypeRoot} set in {@link Source} configuration XML message.
	 */
	ContentTypeRoot getContentTypeRoot();
	/**
	 * @param contentTypeRoot
	 */
	void setContentTypeRoot(ContentTypeRoot contentTypeRoot);
	
	/**
	 * Returns the {@link SolrInputDocument} library.
	 * @return the {@link SolrInputDocument} library.
	 */
	DocumentLibrary getDocumentLibrary();
	/**
	 * @param documentLibrary
	 */
	void setDocumentLibrary(DocumentLibrary documentLibrary);
	
	TemplateLibrary getTemplateLibrary();
	void setTemplateLibrary(TemplateLibrary templateLibrary);
	
	Map<String,String> getConstants();
	void setConstants(Map<String,String> constants);
	
	/**
	 * The XML message manager event.
	 * @param e {@link ManagerEventArgs} the XML message manager event arguments.
	 */
	void OnManagerEvent(ManagerEventArgs e);
}