package org.httprobot;

import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.configuration.Source;
import org.httprobot.configuration.SourceParent;
import org.httprobot.configuration.ServiceConnection;
import org.httprobot.configuration.ServiceConnectionParent;
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

/**
 * Root class. Inherits {@link Message}.
 * This class is the parent of parents XML messages.
 * It is {@link Listener} and listens for
 * {@link ServiceConnectionParent} and {@link SourceParent}.
 * @author joan
 *
 */
@XmlRootElement
public final class Root
	extends Message
		implements Listener {

	/**
	 * 322360610738419712L
	 */
	private static final long serialVersionUID = 322360610738419712L;
	
	/**
	 * The current {@link WebDriver}.
	 */
	WebDriver webDriver;
	/**
	 * The {@link ServiceConnection} XML message {@link Entry}.
	 */
	ServiceConnectionParent serviceConnectionManager;
	/**
	 * The {@link ServiceConnection} XML message.
	 */
	ServiceConnection serviceConnection;
	/**
	 * The {@link Source} configuration XML message {@link Entry}.
	 */
	SourceParent sourceManager;
	/**
	 * The {@link Source} XML message.
	 */
	Source source;
	
	@Override
	public Listener getParent() {
		return null;
	}
	@Override
	public void setParent(Listener parent) {
		
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
		return webDriver;
	}
	@Override
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	/**
	 * {@link Root} default class constructor.
	 */
	public Root() {
		super();
	}
	/**
	 * {@link Root} class constructor.
	 * @param selenium the {@link Driver} configuration XML message
	 */
	public Root(ServiceConnection serviceConnection) {
		super(UUID.randomUUID());
		// Set ServiceConnection XML message
		this.serviceConnection = serviceConnection;
		// Instance ServiceConnectionManager
		serviceConnectionManager = new ServiceConnectionParent(serviceConnection, this);
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
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if (e.getSource().equals(sourceManager)) {
				for (DataSource dataSource : sourceManager) {
					// Get data source's content type reference
					ContentTypeRef contentTypeRef = dataSource.getContentTypeRef();
					// Look for matching content type
					for (ContentType contentType : source.getContentTypeRoot().getContentType()) {
						// Match UUID
						if (contentTypeRef.getName().equals(contentType.getName())) {
							InputDocument templateDocument = sourceManager.getTemplateLibrary().get(contentTypeRef);
							FieldLibrary<FieldRef> fieldTemplates = sourceManager.getTemplateLibrary().getTemplateFieldLibrary();
							// Initialize document library
							DocumentLibrary documentLibrary = new DocumentLibrary(contentTypeRef, templateDocument, fieldTemplates);
							// Put data
							sourceManager.put(dataSource, documentLibrary);
							break;
						}
					}
				}
			} else if (e.getSource().equals(serviceConnectionManager)) {
				serviceConnectionManager.setValue(new WebService(serviceConnection.getURL(), serviceConnection.getQName()));
			}
			break;
		case FINISHED:
			if(e.getSource().equals(serviceConnectionManager)) {
				WebService webService = serviceConnectionManager.getValue();
				source = webService.getSource();
				sourceManager = new SourceParent(source, this);
				sourceManager.start();
			}
			break;
		default:
			break;
		}
	}
}