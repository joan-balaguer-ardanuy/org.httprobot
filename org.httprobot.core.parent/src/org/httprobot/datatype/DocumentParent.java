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

public class DocumentParent
	extends MappingParent<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>, DocumentControl> {

	/**
	 * -7967388989379246404L
	 */
	private static final long serialVersionUID = -7967388989379246404L;

	ActionParent actionParent;
	ContentTypeRefParent contentTypeRefParent;
	FieldRootParent fieldRootParent;
	DocumentParent documentParent;
	
	HtmlPage currentResponse;
	
	public DocumentParent() {
		super();
	}
	public DocumentParent(Document message, ParentListener parent) {
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
			actionParent.put(pageKey, actionOutput);
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
			if(e.getSource().equals(actionParent)) {
				Map<InputDocument, HtmlPage> documentOutputData = new LinkedHashMap<InputDocument, HtmlPage>();
				documentParent.put(getValue(), documentOutputData);
			} else if(e.getSource().equals(contentTypeRefParent)) {
				InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefParent.getKey());
				FieldLibrary<FieldRef> templateFields = getTemplateLibrary().getTemplateFieldLibrary();
				setDocumentLibrary(new DocumentLibrary(contentTypeRefParent.getKey(), templateDocument, templateFields));
			}
			break;
		case ACTION_WEB_LOADED:
			if (e.getSource().equals(actionParent)) {
				currentResponse = (HtmlPage) e.getValue();

				InputDocument templateDocument = getTemplateLibrary().get(contentTypeRefParent.getKey());
				getDocumentLibrary().put(currentResponse, templateDocument);
				getValue().put(templateDocument, currentResponse);
				fieldRootParent.put(templateDocument, currentResponse);
			}
			break;
		case DOCUMENT_COMPLETED:
			if(e.getSource().equals(documentParent)) {
				InputDocument childDocument = InputDocument.class.cast(e.getValue());
				InputDocument currentDocument = getDocumentLibrary().get(currentResponse);
				currentDocument.addChildDocument(childDocument);
			} else if(e.getSource().equals(fieldRootParent)) {
				InputDocument inputDocument = InputDocument.class.cast(e.getValue());
				getDocumentLibrary().put(currentResponse, inputDocument);
				ManagerEvent(e);
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		switch (e.getCommand()) {
		case ACTION_CONTROL_LOADED:
			if(e.getSource() instanceof ActionControl) {
				Action action = ActionControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ACTION).equals(action)) {
					actionParent = new ActionParent(action, this);
					addChildManager(actionParent);
				}
			}
			break;
		case CONTENT_TYPE_REF_CONTROL_LOADED:
			if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
					contentTypeRefParent = new ContentTypeRefParent(contentTypeRef, this);
					addChildManager(contentTypeRefParent);
				}
			}
			break;
		case FIELD_ROOT_CONTROL_LOADED:
			if(e.getSource() instanceof FieldRootControl) {
				FieldRoot fieldRoot = FieldRootControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.FIELD_ROOT).equals(fieldRoot)) {
					fieldRootParent = new FieldRootParent(fieldRoot, this);
					addChildManager(fieldRootParent);
				}
			}
			break;
		case DOCUMENT_CONTROL_LOADED:
			if(e.getSource() instanceof DocumentControl) {
				Document document = DocumentControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.DOCUMENT).equals(document)) {
					DocumentParent documentManager = new DocumentParent(document, this);
					addChildManager(documentManager);
				}
			}
			break;
		default:
			break;
		}
	}
}