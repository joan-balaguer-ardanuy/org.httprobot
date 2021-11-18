package org.httprobot;

import java.util.Map;

import org.httprobot.config.Configuration;
import org.httprobot.config.ConfigurationManager;
import org.httprobot.config.ServiceConnection;
import org.httprobot.config.ServiceConnectionManager;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRoot;
import org.httprobot.content.FieldRef;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.TemplateLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.datatype.DataSource;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;
import org.openqa.selenium.WebDriver;

public class Precursor
	extends XML
		implements ManagerListener {

	/**
	 * 322360610738419712L
	 */
	private static final long serialVersionUID = 322360610738419712L;

	WebDriver driver;
	
	ServiceConnectionManager serviceConnectionManager;
	ServiceConnection serviceConnection;
	ConfigurationManager configurationManager;
	Configuration configuration;
	
	@Override
	public ManagerListener getParent() {
		return null;
	}
	@Override
	public void setParent(ManagerListener parent) {
		
	}
	@Override
	public ContentTypeRoot getContentTypeRoot() {
		return configurationManager.getContentTypeRoot();
	}
	@Override
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		configurationManager.setContentTypeRoot(contentTypeRoot);
	}
	@Override
	public DocumentLibrary getDocumentLibrary() {
		return configurationManager.getDocumentLibrary();
	}
	@Override
	public void setDocumentLibrary(DocumentLibrary documentLibrary) {
		configurationManager.setDocumentLibrary(documentLibrary);
	}
	@Override
	public TemplateLibrary getTemplateLibrary() {
		return configurationManager.getTemplateLibrary();
	}
	@Override
	public void setTemplateLibrary(TemplateLibrary templateLibrary) {
		configurationManager.setTemplateLibrary(templateLibrary);
	}
	@Override
	public Map<String, String> getConstants() {
		return configurationManager.getConstants();
	}
	@Override
	public void setConstants(Map<String, String> constants) {
		configurationManager.setConstants(constants);
	}
	@Override
	public Map<String, String> getBannedWords() {
		return configurationManager.getBannedWords();
	}
	@Override
	public void setBannedWords(Map<String, String> bannedWords) {
		configurationManager.setBannedWords(bannedWords);
	}
	@Override
	public WebDriver getWebDriver() {
		return driver;
	}
	@Override
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public Precursor() {
		super();
	}
	public Precursor(ServiceConnection message) {
		super(message.getUuid());
		serviceConnection = message;
		serviceConnectionManager = new ServiceConnectionManager(message, this);
	}
	@Override
	public void start() {
		serviceConnectionManager.start();
	}
	@Override
	public void stop() {
		
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(this.configurationManager)) {
				for(DataSource dataSource : this.configurationManager) {
					//Get data source's content type reference
					ContentTypeRef contentTypeRef = dataSource.getContentTypeRef();
					//Look for matching content type
					for(ContentType contentType : this.configuration.getContentTypeRoot().getContentType())
					{
						//Match UUID
						if(contentTypeRef.getUuid().equals(contentType.getUuid()))
						{	
							InputDocument templateDocument = configurationManager.getTemplateLibrary().get(contentTypeRef);
							FieldLibrary<FieldRef> fieldTemplates = configurationManager.getTemplateLibrary().getTemplateFieldLibrary();
							//Initialize document library
							DocumentLibrary documentLibrary = new DocumentLibrary(contentTypeRef, templateDocument, fieldTemplates);
							//Put data
							this.configurationManager.put(dataSource, documentLibrary);
							
							break;
						}
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(serviceConnectionManager)) {
				WebService webService = serviceConnectionManager.getValue();
				configuration = webService.getConfiguration();
				configurationManager = new ConfigurationManager(configuration, this);
				configurationManager.start();
			}
			break;
		default:
			break;
		}
	}
	
}