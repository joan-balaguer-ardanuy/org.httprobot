package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Data;
import org.httprobot.Listener;
import org.httprobot.Parent;
import org.httprobot.ParentEntry;
import org.httprobot.Message;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefParent;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.event.EventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantParent;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionParent;

@XmlRootElement
public final class DataSourceParent 
	extends ParentEntry<ContentTypeRef, DocumentLibrary> {

	/**
	 * -8406916752533216986L
	 */
	private static final long serialVersionUID = -8406916752533216986L;

	/**
	 * The action XML message manager
	 */
	ActionParent actionParent;
	/**
	 * The content type reference XML message manager
	 */
	ContentTypeRefParent contentTypeRefParent;
	/**
	 * The document root XML message manager
	 */
	DocumentRootParent documentRootParent;
	/**
	 * The constant XML message managers
	 */
	Map<Constant, ConstantParent> constantManagers;

	@Override
	public DocumentLibrary put(ContentTypeRef key, DocumentLibrary value) {
		setDocumentLibrary(value);
		actionParent.put(null, new LinkedHashSet<HtmlPage>());
		return super.put(key, value);
	}
	
	public DataSourceParent() {
		super();
	}
	public DataSourceParent(DataSource message, Parent parent) {
		super(message, DataSourceControl.class, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_LOADED:
			if(e.getSource() instanceof ConstantControl) {
				Constant message = ConstantControl.class.cast(e.getSource()).getMessage();
				@SuppressWarnings("unchecked")
				Set<Message> set = (Set<Message>) getControl().get(Data.CONSTANT);
				if(set.contains(message)) {
					ConstantParent constantManager = new ConstantParent(message, this);
					constantManagers.put(message, constantManager);
					addChild(constantManager);
				}
			} else if (e.getSource() instanceof ActionControl) {
				Action action = ActionControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.ACTION).equals(action)) {
					actionParent = new ActionParent(action, this);
					addChild(actionParent);
				}
			} else if (e.getSource() instanceof DocumentRootControl) {
				DocumentRoot documentRoot = DocumentRootControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.DOCUMENT_ROOT).equals(documentRoot)) {
					documentRootParent = new DocumentRootParent(documentRoot, this);
					addChild(documentRootParent);
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRef contentTypeRef = ContentTypeRefControl.class.cast(e.getSource()).getMessage();
				if (getControl().get(Data.CONTENT_TYPE_REF).equals(contentTypeRef)) {
					contentTypeRefParent = new ContentTypeRefParent(contentTypeRef, this);
					addChild(contentTypeRefParent);
				}
			}
			break;
		case PARENT_STARTED:
			if (e.getSource().equals(contentTypeRefParent)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					if (contentTypeRefParent.getName().equals(contentType.getName())) {
						contentTypeRefParent.setValue(contentType);
					}
				}
			} else if(constantManagers.containsValue(e.getSource())) {
				ConstantParent constantManager = ConstantParent.class.cast(e.getSource());
				getConstants().put(constantManager.getKey(), constantManager.getValue());
			}
			break;
		case PARENT_FINISHED:
			if (e.getSource().equals(actionParent)) {
				// No more than one expected.
				for (HtmlPage key : actionParent) {
					// Get the returned pages by first request.
					Set<HtmlPage> actiontData = this.actionParent.get(key);
					// Put loaded data on document root message manager
					documentRootParent.put(actiontData, new LinkedHashMap<InputDocument, HtmlPage>());
				}
			}
			break;
		default:
			break;
		}
	}
}