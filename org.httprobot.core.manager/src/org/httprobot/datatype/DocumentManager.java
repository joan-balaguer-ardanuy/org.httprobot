package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Enums.Data;
import org.httprobot.Manager;
import org.httprobot.MappingManager;
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
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionManager;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class DocumentManager
	extends MappingManager<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>, DocumentControl> {

	/**
	 * -7967388989379246404L
	 */
	private static final long serialVersionUID = -7967388989379246404L;

	ActionManager actionManager;
	ContentTypeRefManager contentTypeRefManager;
	FieldRootManager fieldRootManager;
	DocumentManager documentManager;
	
	WebRequest currentRequest;
	HtmlPage currentResponse;
	
	public DocumentManager() {
		super();
	}
	public DocumentManager(Document message, Manager<?> parent) {
		super(message, DocumentControl.class, parent);
	}
	
	@Override
	public Map<InputDocument, HtmlPage> put(Map<InputDocument, HtmlPage> key, Map<InputDocument, HtmlPage> value) {
		keySet().add(key);
		
		setKey(key);
		setValue(value);
		
		for(InputDocument inputDocument : key.keySet()) {
			HtmlPage pageKey = key.get(inputDocument);
			Set<HtmlPage> actionOutput = new LinkedHashSet<HtmlPage>();
			actionManager.put(pageKey, actionOutput);
		}
		
		return super.put(key, value);
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		switch (e.getCommand()) {
		case ACTION_CONTROL_LOADED:
			if(e.getSource() instanceof ActionControl) {
				Action action = ActionControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ACTION).equals(action)) {
					actionManager = new ActionManager(action, this);
					addChildManager(actionManager);
				}
			}
			break;
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
					contentTypeRefManager = new ContentTypeRefManager(contentTypeRef, this);
					addChildManager(contentTypeRefManager);
				}
			}
			break;
		case FIELD_ROOT_CONTROL_LOADED:
			if(e.getSource() instanceof FieldRootControl) {
				FieldRoot fieldRoot = FieldRootControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.FIELD_ROOT).equals(fieldRoot)) {
					fieldRootManager = new FieldRootManager(fieldRoot, this);
					addChildManager(fieldRootManager);
				}
			}
			break;
		case DOCUMENT_CONTROL_LOADED:
			if(e.getSource() instanceof DocumentControl) {
				Document document = DocumentControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.DOCUMENT).equals(document)) {
					DocumentManager documentManager = new DocumentManager(document, this);
					addChildManager(documentManager);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(contentTypeRefManager)) {
				for(ContentType contentType : getContentTypeRoot().getContentType()) {
					if(contentType.getUuid().equals(contentTypeRefManager.getUuid())) {
						contentTypeRefManager.setValue(contentType);
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(actionManager)) {
				Map<InputDocument, HtmlPage> documentOutputData = new LinkedHashMap<InputDocument, HtmlPage>();
				documentManager.put(getValue(), documentOutputData);
			} else if(e.getSource().equals(contentTypeRefManager)) {
				InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefManager.getKey());
				FieldLibrary<FieldRef> templateFields = getTemplateLibrary().getTemplateFieldLibrary();
				setDocumentLibrary(new DocumentLibrary(contentTypeRefManager.getKey(), templateDocument, templateFields));
			}
			break;
		case ACTION_WEB_LOADED:
			if (e.getSource().equals(actionManager)) {
				try {
					@SuppressWarnings("unchecked")
					Entry<WebRequest, HtmlPage> entry = (Entry<WebRequest, HtmlPage>) e.getValue();
					currentRequest = entry.getKey();
					currentResponse = entry.getValue();

					InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefManager.getKey());
					getDocumentLibrary().put(currentResponse, templateDocument);
					getValue().put(templateDocument, currentResponse);
					fieldRootManager.put(templateDocument, currentResponse);
				} catch (ClassCastException exception) {
					throw new Error("DocumentManager.OnManagerEvent: Unable to cast java.util.Map.Entry<WebRequest,HtmlPage>.", exception);
				}
			}
			break;
		case DOCUMENT_COMPLETED:
			if(e.getSource().equals(documentManager)) {
				InputDocument childDocument = InputDocument.class.cast(e.getValue());
				InputDocument currentDocument = getDocumentLibrary().get(currentResponse);
				currentDocument.addChildDocument(childDocument);
			} else if(e.getSource().equals(fieldRootManager)) {
				InputDocument inputDocument = InputDocument.class.cast(e.getValue());
				getDocumentLibrary().put(currentResponse, inputDocument);
				ManagerEvent(e);
			}
			break;
		default:
			break;
		}
	}
}