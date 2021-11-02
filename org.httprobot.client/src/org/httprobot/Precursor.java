package org.httprobot;

import org.httprobot.config.Configuration;
import org.httprobot.config.ConfigurationManager;
import org.httprobot.config.ServiceConnection;
import org.httprobot.config.ServiceConnectionManager;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.FieldRef;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.datatype.DataSource;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebService;

public class Precursor
	extends XML
		implements ManagerListener {

	/**
	 * 322360610738419712L
	 */
	private static final long serialVersionUID = 322360610738419712L;

	ServiceConnectionManager serviceConnectionManager;
	ServiceConnection serviceConnection;
	ConfigurationManager configurationManager;
	Configuration configuration;
	
	public Precursor() {
		super();
	}
	public Precursor(ServiceConnection message) {
		super(message.getUuid());
		serviceConnection = message;
		serviceConnectionManager = new ServiceConnectionManager(message, null);
		serviceConnectionManager.addManagerListener(this);
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
				configurationManager = new ConfigurationManager(configuration, null);
				configurationManager.addManagerListener(this);
				configurationManager.start();
			}
			break;
		default:
			break;
		}
	}
}