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

public class DocumentRootManager
	extends MappingManager<Set<HtmlPage>, Map<InputDocument, HtmlPage>, DocumentRootControl> {

	/**
	 * 9134669471992617702L
	 */
	private static final long serialVersionUID = 9134669471992617702L;

	ActionManager actionManager;
	ContentTypeRefManager contentTypeRefManager;
	FieldRootManager fieldRootManager;
	DocumentManager documentManager;
	
	WebRequest currentRequest;
	HtmlPage currentResponse;
	
	public DocumentRootManager() {
		super();
	}
	public DocumentRootManager(DocumentRoot message, Manager<?> parent) {
		super(message, DocumentRootControl.class, parent);
	}
	
	@Override
	public Map<InputDocument, HtmlPage> put(Set<HtmlPage> key, Map<InputDocument, HtmlPage> value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		for(HtmlPage htmlPage : key) {
			actionManager.put(htmlPage, new LinkedHashSet<HtmlPage>());
		}
		return super.put(key, value);
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
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(contentTypeRefManager)) {
				for(ContentType contentType : getContentTypeRoot().getContentType()) {
					if(contentType.getUuid().equals(contentTypeRefManager.getKey().getUuid())) {
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
					@SuppressWarnings("unchecked")
					Entry<WebRequest,HtmlPage> entry = (Entry<WebRequest,HtmlPage>)e.getValue();
					currentRequest = entry.getKey();
					currentResponse = entry.getValue();
					
					InputDocument rootTemplateDocument = getTemplateLibrary().get(contentTypeRefManager.getKey());
					getValue().put(rootTemplateDocument, currentResponse);
					getDocumentLibrary().put(currentResponse, rootTemplateDocument);
					fieldRootManager.put(rootTemplateDocument, currentResponse);
				}
				catch (ClassCastException exception) {
					throw new Error("DocumentRootManager.OnManagerEvent: Unable to cast java.util.Map.Entry<WebRequest,HtmlPage>.", exception);
				}
			}
			break;
		case DOCUMENT_COMPLETED:
			if(e.getSource().equals(documentManager)) {
				InputDocument childDocument = InputDocument.class.cast(e.getValue());
				InputDocument currentDocument = getDocumentLibrary().get(currentResponse);
				currentDocument.addChildDocument(childDocument);
			}
			else if(e.getSource().equals(fieldRootManager)) {
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
}