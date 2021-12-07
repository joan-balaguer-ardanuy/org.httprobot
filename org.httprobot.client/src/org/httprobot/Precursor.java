package org.httprobot;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.configuration.Selenium;
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

/**
 * Precursor class. Inherits {@link XML}.
 * This class is the parent of parents XML messages.
 * It is {@link ManagerListener} and listens for
 * {@link ServiceConnectionManager} and {@link SourceManager}.
 * @author joan
 *
 */
@XmlRootElement
public final class Precursor
	extends XML
		implements ManagerListener {

	/**
	 * 322360610738419712L
	 */
	private static final long serialVersionUID = 322360610738419712L;
	
	/**
	 * The {@link Selenium} configuration XML message.
	 */
	Selenium selenium;
	/**
	 * The {@link ServiceConnection} XML message {@link Manager}.
	 */
	ServiceConnectionManager serviceConnectionManager;
	/**
	 * The {@link ServiceConnection} XML message.
	 */
	ServiceConnection serviceConnection;
	/**
	 * The {@link Source} configuration XML message {@link Manager}.
	 */
	SourceManager sourceManager;
	/**
	 * The {@link Source} XML message.
	 */
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
	public Selenium getSelenium() {
		return selenium;
	}
	@Override
	public void setSelenium(Selenium selenium) {
		this.selenium = selenium;
		this.selenium.loadWebDriver();
	}
	
	/**
	 * {@link Precursor} default class constructor.
	 */
	public Precursor() {
		super();
	}
	/**
	 * {@link Precursor} class constructor.
	 * @param selenium the {@link Selenium} configuration XML message
	 */
	public Precursor(ServiceConnection serviceConnection) {
		super(serviceConnection.getUuid());
		// Set ServiceConnection XML message
		this.serviceConnection = serviceConnection;
		// Instance ServiceConnectionManager
		serviceConnectionManager = new ServiceConnectionManager(serviceConnection, this);
	}
	@Override
	public void start() {
		// Starts connecting to org.httprobot WebService server
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
					for(ContentType contentType : source.getContentTypeRoot().getContentType()) {
						//Match UUID
						if(contentTypeRef.getUuid().equals(contentType.getUuid())) {	
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
				source = webService.getSource();
				sourceManager = new SourceManager(source, this);
				sourceManager.start();
			}
			break;
		default:
			break;
		}
	}
}