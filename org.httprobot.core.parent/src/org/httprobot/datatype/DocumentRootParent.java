package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Data;
import org.httprobot.ParentListener;
import org.httprobot.MappingParent;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefParent;
import org.httprobot.content.FieldRef;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionParent;

public class DocumentRootParent
	extends MappingParent<Set<HtmlPage>, Map<InputDocument, HtmlPage>, DocumentRootControl> {

	/**
	 * 9134669471992617702L
	 */
	private static final long serialVersionUID = 9134669471992617702L;

	ActionParent actionParent;
	ContentTypeRefParent contentTypeRefParent;
	FieldRootParent fieldRootParent;
	DocumentParent documentParent;
	
	HtmlPage currentResponse;
	
	public DocumentRootParent() {
		super();
	}
	public DocumentRootParent(DocumentRoot message, ParentListener parent) {
		super(message, DocumentRootControl.class, parent);
	}
	
	@Override
	public Map<InputDocument, HtmlPage> put(Set<HtmlPage> key, Map<InputDocument, HtmlPage> value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		for(HtmlPage htmlPage : key) {
			actionParent.put(htmlPage, new LinkedHashSet<HtmlPage>());
		}
		return super.put(key, value);
	}

	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if(e.getSource().equals(contentTypeRefParent)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					if (contentType.getUuid().equals(contentTypeRefParent.getUuid())) {
						contentTypeRefParent.setValue(contentType);
					}
				}
			}
			break;
		case FINISHED:
			if(e.getSource().equals(contentTypeRefParent)) {
				InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefParent.getKey());
				FieldLibrary<FieldRef> templateFields = getTemplateLibrary().getTemplateFieldLibrary();
				setDocumentLibrary(new DocumentLibrary(contentTypeRefParent.getKey(), templateDocument, templateFields));
			} else if (e.getSource().equals(actionParent)) {
				Map<InputDocument, HtmlPage> documentOutputData = new LinkedHashMap<InputDocument, HtmlPage>();
				documentParent.put(getValue(), documentOutputData);
			}
			break;
		case ACTION_WEB_LOADED:
			if(e.getSource().equals(actionParent)) {
				try {
					currentResponse = (HtmlPage) e.getValue();
					
					InputDocument rootTemplateDocument = getTemplateLibrary().get(contentTypeRefParent.getKey());
					getValue().put(rootTemplateDocument, currentResponse);
					getDocumentLibrary().put(currentResponse, rootTemplateDocument);
					fieldRootParent.put(rootTemplateDocument, currentResponse);
				} catch (ClassCastException exception) {
					throw new Error("DocumentRootManager.OnManagerEvent: Unable to cast java.util.Map.Entry<WebRequest,HtmlPage>.", exception);
				}
			}
			break;
		case DOCUMENT_COMPLETED:
			if(e.getSource().equals(documentParent)) {
				InputDocument childDocument = InputDocument.class.cast(e.getValue());
				InputDocument currentDocument = getDocumentLibrary().get(currentResponse);
				currentDocument.addChildDocument(childDocument);
			} else if(e.getSource().equals(fieldRootParent)) {
				for(InputDocument inputDocument : fieldRootParent) {
					HtmlPage htmlPage = fieldRootParent.get(inputDocument);
					getDocumentLibrary().get(htmlPage).addInputDocument(inputDocument);
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
				contentTypeRefParent = new ContentTypeRefParent(contentTypeRef, this);
				addChildManager(contentTypeRefParent);
			}
			break;
		case ACTION_CONTROL_LOADED:
			Action action = ActionControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.ACTION).equals(action)) {
				actionParent = new ActionParent(action, this);
				addChildManager(actionParent);
			}
			break;
		case FIELD_ROOT_CONTROL_LOADED:
			FieldRoot fieldRoot = FieldRootControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.FIELD_ROOT).equals(fieldRoot)) {
				fieldRootParent = new FieldRootParent(fieldRoot, this);
				addChildManager(fieldRootParent);
			}
			break;
		case DOCUMENT_CONTROL_LOADED:
			Document document = DocumentControl.class.cast(e.getSource()).getMessage();
			if(getControl().get(Data.DOCUMENT).equals(document)) {
				documentParent = new DocumentParent(document, this);
				addChildManager(documentParent);
			}
			break;
		default:
			break;
		}
	}
}