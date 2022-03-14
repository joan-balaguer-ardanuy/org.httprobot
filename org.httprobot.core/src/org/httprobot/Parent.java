package org.httprobot;

import java.util.Map;

import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.openqa.selenium.WebDriver;

public interface Parent extends Listener<Parent>, Message {
	void start();
	void stop();
	
	ContentTypeRoot getContentTypeRoot();
	void setContentTypeRoot(ContentTypeRoot contentTypeRoot);
	DocumentLibrary getDocumentLibrary();
	void setDocumentLibrary(DocumentLibrary documentLibrary);
	TemplateLibrary getTemplateLibrary();
	void setTemplateLibrary(TemplateLibrary templateLibrary);
	
	Map<String, String> getConstants();
	void setConstants(Map<String, String> constants);
	
	WebDriver getWebDriver();
	void setWebDriver(WebDriver webDriver);
}