package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Enums.Data;
import org.httprobot.Manager;
import org.httprobot.ManagerListener;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefManager;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebDocument;
import org.httprobot.parameter.ServerUrl;
import org.httprobot.parameter.ServerUrlControl;
import org.httprobot.parameter.ServerUrlManager;
import org.httprobot.parameter.StartUrl;
import org.httprobot.parameter.StartUrlControl;
import org.httprobot.parameter.StartUrlManager;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionManager;

@XmlRootElement
public final class DataSourceManager 
	extends Manager<ContentTypeRef, DocumentLibrary, DataSourceControl> {

	/**
	 * -8406916752533216986L
	 */
	private static final long serialVersionUID = -8406916752533216986L;

	ActionManager actionManager;
	ContentTypeRefManager contentTypeRefManager;
	DocumentRootManager documentRootManager;
	ServerUrlManager serverUrlManager;
	StartUrlManager startUrlManager;
	
	ContentType currentContentType;
	
	public DataSourceManager() {
		super();
	}
	public DataSourceManager(DataSource message, ManagerListener parent) {
		super(message, DataSourceControl.class, parent);
	}

	@Override
	public DocumentLibrary put(ContentTypeRef key, DocumentLibrary value) {
		if(keySet().contains(key)) {
			setDocumentLibrary(value);
			actionManager.put(null, new LinkedHashSet<WebDocument>());
		}
		return super.put(key, value);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if (e.getSource().equals(contentTypeRefManager)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					for(ContentTypeRef contentTypeRef : contentTypeRefManager) {
						if (contentTypeRefManager.getUuid().equals(contentType.getUuid())) {
							contentTypeRefManager.put(contentTypeRef, contentType);
						}
					}
				}
			} else if(e.getSource().equals(serverUrlManager)) {
				getConstants().put(serverUrlManager.getKey(), serverUrlManager.getValue());
			} else if(e.getSource().equals(startUrlManager)) {
				getConstants().put(startUrlManager.getKey(), startUrlManager.getValue());
			}
			break;
		case FINISHED:
			if (e.getSource().equals(contentTypeRefManager)) {
				for(ContentTypeRef contentTypeRef : this.contentTypeRefManager) {
					//Set current content type
					this.currentContentType = this.contentTypeRefManager.get(contentTypeRef);	
				}
			} else if (e.getSource().equals(actionManager)) {
				// No more than one expected.
				for (WebDocument key : actionManager) {
					// Get the returned pages by first request.
					Set<WebDocument> actiontData = this.actionManager.get(key);
					// Put loaded data on document root message manager
					documentRootManager.put(actiontData, new LinkedHashMap<InputDocument, WebDocument>());
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {

		switch (e.getCommand()) {
		case SERVER_URL_CONTROL_LOADED:
			if(e.getSource() instanceof ServerUrlControl) {
				ServerUrl serverUrl = ServerUrlControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.SERVER_URL).equals(serverUrl)) {
					serverUrlManager = new ServerUrlManager(serverUrl, this);
					addChildManager(serverUrlManager);
				}
			}
			break;
		case START_URL_CONTROL_LOADED:
			if(e.getSource() instanceof StartUrlControl) {
				StartUrl startUrl = StartUrlControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.START_URL).equals(startUrl)) {
					startUrlManager = new StartUrlManager(startUrl, this);
					addChildManager(startUrlManager);
				}
			}
			break;
		case ACTION_CONTROL_LOADED:
			if (e.getSource() instanceof ActionControl) {
				Action action = ActionControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.ACTION).equals(action)) {
					actionManager = new ActionManager(action, this);
					addChildManager(actionManager);
				}
			}
			break;
		case DOCUMENT_ROOT_CONTROL_LOADED:
			if (e.getSource() instanceof DocumentRootControl) {
				DocumentRoot documentRoot = DocumentRootControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.DOCUMENT_ROOT).equals(documentRoot)) {
					documentRootManager = new DocumentRootManager(documentRoot, this);
					addChildManager(documentRootManager);
				}
			}
			break;
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if (e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				keySet().add(contentTypeRef);
				if (getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
					contentTypeRefManager = new ContentTypeRefManager(contentTypeRef, this);
					addChildManager(contentTypeRefManager);
				}
			}
			break;
		default:
			break;
		}
	}
}