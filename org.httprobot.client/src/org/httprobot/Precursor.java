package org.httprobot;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.configuration.Robot;
import org.httprobot.configuration.Source;
import org.httprobot.configuration.SourceManager;
import org.httprobot.configuration.ServiceConnection;
import org.httprobot.configuration.ServiceConnectionManager;
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
import org.openqa.selenium.firefox.FirefoxDriver;

@XmlRootElement
public final class Precursor
	extends XML
		implements ManagerListener {

	/**
	 * 322360610738419712L
	 */
	private static final long serialVersionUID = 322360610738419712L;

	WebDriver driver;
	
	Robot robot;
	ServiceConnectionManager serviceConnectionManager;
	ServiceConnection serviceConnection;
	SourceManager sourceManager;
	Source source;
	
	@Override
	public ManagerListener getParent() {
		return null;
	}
	@Override
	public void setParent(ManagerListener parent) {
		
	}
	@Override
	public ContentTypeRoot getContentTypeRoot() {
		return sourceManager.getContentTypeRoot();
	}
	@Override
	public void setContentTypeRoot(ContentTypeRoot contentTypeRoot) {
		sourceManager.setContentTypeRoot(contentTypeRoot);
	}
	@Override
	public DocumentLibrary getDocumentLibrary() {
		return sourceManager.getDocumentLibrary();
	}
	@Override
	public void setDocumentLibrary(DocumentLibrary documentLibrary) {
		sourceManager.setDocumentLibrary(documentLibrary);
	}
	@Override
	public TemplateLibrary getTemplateLibrary() {
		return sourceManager.getTemplateLibrary();
	}
	@Override
	public void setTemplateLibrary(TemplateLibrary templateLibrary) {
		sourceManager.setTemplateLibrary(templateLibrary);
	}
	@Override
	public Map<String, String> getConstants() {
		return sourceManager.getConstants();
	}
	@Override
	public void setConstants(Map<String, String> constants) {
		sourceManager.setConstants(constants);
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
	public Precursor(Robot message) {
		super(message.getUuid());
		serviceConnection = message.getServiceConnection();
		serviceConnectionManager = new ServiceConnectionManager(message.getServiceConnection(), this);
		driver = new FirefoxDriver();
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
			if(e.getSource().equals(sourceManager)) {
				for(DataSource dataSource : sourceManager) {
					//Get data source's content type reference
					ContentTypeRef contentTypeRef = dataSource.getContentTypeRef();
					//Look for matching content type
					for(ContentType contentType : source.getContentTypeRoot().getContentType())
					{
						//Match UUID
						if(contentTypeRef.getUuid().equals(contentType.getUuid()))
						{	
							InputDocument templateDocument = sourceManager.getTemplateLibrary().get(contentTypeRef);
							FieldLibrary<FieldRef> fieldTemplates = sourceManager.getTemplateLibrary().getTemplateFieldLibrary();
							//Initialize document library
							DocumentLibrary documentLibrary = new DocumentLibrary(contentTypeRef, templateDocument, fieldTemplates);
							//Put data
							sourceManager.put(dataSource, documentLibrary);
							
							break;
						}
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(serviceConnectionManager)) {
				WebService webService = serviceConnectionManager.getValue();
				source = webService.getConfiguration();
				sourceManager = new SourceManager(source, this);
				sourceManager.start();
			}
			break;
		default:
			break;
		}
	}
}