package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.httprobot.Data;
import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefParent;
import org.httprobot.content.FieldRef;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.data.field.FieldLibrary;
import org.httprobot.event.EventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionParent;

public class DocumentParent
	extends ParentEntry<Map<InputDocument, HtmlPage>, Map<InputDocument, HtmlPage>> {

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
	public DocumentParent(Document message, Parent parent) {
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
	public void OnEventReceived(EventArgs e) {
		switch (e.getEventType()) {
		case PARENT_STARTED:
			if(e.getSource().equals(contentTypeRefParent)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					if (contentType.getName().equals(contentTypeRefParent.getName())) {
						contentTypeRefParent.setValue(contentType);
					}
				}
			}
			break;
		case PARENT_FINISHED:
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
				//ManagerEvent(e);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof ActionControl) {
				Action action = ActionControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.ACTION).equals(action)) {
					actionParent = new ActionParent(action, this);
					addChild(actionParent);
				}
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
					contentTypeRefParent = new ContentTypeRefParent(contentTypeRef, this);
					addChild(contentTypeRefParent);
				}
			} else if(e.getSource() instanceof FieldRootControl) {
				FieldRoot fieldRoot = FieldRootControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.FIELD_ROOT).equals(fieldRoot)) {
					fieldRootParent = new FieldRootParent(fieldRoot, this);
					addChild(fieldRootParent);
				}
			} else if(e.getSource() instanceof DocumentControl) {
				Document document = DocumentControl.class.cast(e.getSource()).getMessage();
				if(getControl().get(Data.DOCUMENT).equals(document)) {
					DocumentParent documentManager = new DocumentParent(document, this);
					addChild(documentManager);
				}
			}
			break;
		default:
			break;
		}
	}
}