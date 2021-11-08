package org.httprobot;

import java.util.Map;

import org.httprobot.content.ContentTypeRoot;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.event.ManagerEventArgs;

public interface ManagerListener extends MessageListener {
	
	void start();
	void stop();
	
	ManagerListener getParent();
	void setParent(ManagerListener parent);
	
	ContentTypeRoot getContentTypeRoot();
	void setContentTypeRoot(ContentTypeRoot contentTypeRoot);
	
	DocumentLibrary getDocumentLibrary();
	void setDocumentLibrary(DocumentLibrary documentLibrary);
	
	TemplateLibrary getTemplateLibrary();
	void setTemplateLibrary(TemplateLibrary templateLibrary);
	
	Map<String,String> getConstants();
	void setConstants(Map<String,String> constants);
	
	Map<String,String> getBannedWords();
	void setBannedWords(Map<String,String> bannedWords);
	
	void OnManagerEvent(ManagerEventArgs e);
}