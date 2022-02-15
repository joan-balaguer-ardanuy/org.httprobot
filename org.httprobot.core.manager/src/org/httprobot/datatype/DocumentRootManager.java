package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Data;
import org.httprobot.ManagerListener;
import org.httprobot.MapManager;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefManager;
import org.httprobot.content.FieldRef;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionManager;

public class DocumentRootManager
	extends MapManager<Set<HtmlPage>, Map<InputDocument, HtmlPage>, DocumentRootControl> {

	/**
	 * 9134669471992617702L
	 */
	private static final long serialVersionUID = 9134669471992617702L;

	ActionManager actionManager;
	ContentTypeRefManager contentTypeRefManager;
	FieldRootManager fieldRootManager;
	DocumentManager documentManager;
	
	HtmlPage currentResponse;
	
	public DocumentRootManager() {
		super();
	}
	public DocumentRootManager(DocumentRoot message, ManagerListener parent) {
		super(message, DocumentRootControl.class, parent);
	}
	
	@Override
	public Map<InputDocument, HtmlPage> put(Set<HtmlPage> key, Map<InputDocument, HtmlPage> value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		for(HtmlPage webDocument : key) {
			actionManager.put(webDocument, new LinkedHashSet<HtmlPage>());
		}
		return super.put(key, value);
	}

	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(contentTypeRefManager)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					if (contentType.getUuid().equals(contentTypeRefManager.getUuid())) {
						contentTypeRefManager.setValue(contentType);
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(contentTypeRefManager)) {
				InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefManager.getKey());
				FieldLibrary<FieldRef> templateFields = getTemplateLibrary().getTemplateFieldLibrary();
				setDocumentLibrary(new DocumentLibrary(contentTypeRefManager.getKey(), templateDocument, templateFields));
			} else if (e.getSource().equals(actionManager)) {
				Map<InputDocument, HtmlPage> documentOutputData = new LinkedHashMap<InputDocument, HtmlPage>();
				documentManager.put(getValue(), documentOutputData);
			}
			break;
		case ACTION_WEB_LOADED:
			if(e.getSource().equals(actionManager)) {
				try {
					currentResponse = (HtmlPage) e.getValue();
					
					InputDocument rootTemplateDocument = getTemplateLibrary().get(contentTypeRefManager.getKey());
					getValue().put(rootTemplateDocument, currentResponse);
					getDocumentLibrary().put(currentResponse, rootTemplateDocument);
					fieldRootManager.put(rootTemplateDocument, currentResponse);
				} catch (ClassCastException exception) {
					throw new Error("DocumentRootManager.OnManagerEvent: Unable to cast java.util.Map.Entry<WebRequest,HtmlPage>.", exception);
				}
			}
			break;
		case DOCUMENT_COMPLETED:
			if(e.getSource().equals(documentManager)) {
				InputDocument childDocument = InputDocument.class.cast(e.getValue());
				InputDocument currentDocument = getDocumentLibrary().get(currentResponse);
				currentDocument.addChildDocument(childDocument);
			} else if(e.getSource().equals(fieldRootManager)) {
				for(InputDocument inputDocument : fieldRootManager) {
					HtmlPage htmlPage = fieldRootManager.get(inputDocument);
					getDocumentLibrary().get(htmlPage).addInputDocument(inputDocument);
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
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
				contentTypeRefManager = new ContentTypeRefManager(contentTypeRef, this);
				addChildManager(contentTypeRefManager);
			}
			break;
		case ACTION_CONTROL_LOADED:
			Action action = ActionControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.ACTION).equals(action)) {
				actionManager = new ActionManager(action, this);
				addChildManager(actionManager);
			}
			break;
		case FIELD_ROOT_CONTROL_LOADED:
			FieldRoot fieldRoot = FieldRootControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.FIELD_ROOT).equals(fieldRoot)) {
				fieldRootManager = new FieldRootManager(fieldRoot, this);
				addChildManager(fieldRootManager);
			}
			break;
		case DOCUMENT_CONTROL_LOADED:
			Document document = DocumentControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.DOCUMENT).equals(document)) {
				documentManager = new DocumentManager(document, this);
				addChildManager(documentManager);
			}
			break;
		default:
			break;
		}
	}
}