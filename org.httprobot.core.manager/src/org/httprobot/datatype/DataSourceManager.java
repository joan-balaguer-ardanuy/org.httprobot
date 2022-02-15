package org.httprobot.datatype;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Data;
import org.httprobot.EntryManager;
import org.httprobot.ManagerListener;
import org.httprobot.Message;
import org.httprobot.content.ContentType;
import org.httprobot.content.ContentTypeRef;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.content.ContentTypeRefManager;
import org.httprobot.data.DocumentLibrary;
import org.httprobot.data.document.InputDocument;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.HtmlPage;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.parameter.ConstantManager;
import org.httprobot.unit.Action;
import org.httprobot.unit.ActionControl;
import org.httprobot.unit.ActionManager;

@XmlRootElement
public final class DataSourceManager 
	extends EntryManager<ContentTypeRef, DocumentLibrary, DataSourceControl> {

	/**
	 * -8406916752533216986L
	 */
	private static final long serialVersionUID = -8406916752533216986L;

	/**
	 * The action XML message manager
	 */
	ActionManager actionManager;
	/**
	 * The content type reference XML message manager
	 */
	ContentTypeRefManager contentTypeRefManager;
	/**
	 * The document root XML message manager
	 */
	DocumentRootManager documentRootManager;
	/**
	 * The constant XML message managers
	 */
	Map<Constant, ConstantManager> constantManagers;

	@Override
	public ContentTypeRef getKey() {
		return contentTypeRefManager.getKey();
	}
	@Override
	public DocumentLibrary getValue() {
		return super.getValue();
	}
	@Override
	public DocumentLibrary setValue(DocumentLibrary value) {
		setDocumentLibrary(value);
		actionManager.put(null, new LinkedHashSet<HtmlPage>());
		return super.setValue(value);
	}
	
	public DataSourceManager() {
		super();
	}
	public DataSourceManager(DataSource message, ManagerListener parent) {
		super(message, DataSourceControl.class, parent);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		switch (e.getManagerEventType()) {
		case STARTED:
			if (e.getSource().equals(contentTypeRefManager)) {
				for (ContentType contentType : getContentTypeRoot().getContentType()) {
					if (contentTypeRefManager.getUuid().equals(contentType.getUuid())) {
						contentTypeRefManager.setValue(contentType);
					}
				}
			} else if(constantManagers.containsValue(e.getSource())) {
				ConstantManager constantManager = ConstantManager.class.cast(e.getSource());
				getConstants().put(constantManager.getKey(), constantManager.getValue());
			}
			break;
		case FINISHED:
			if (e.getSource().equals(actionManager)) {
				// No more than one expected.
				for (HtmlPage key : actionManager) {
					// Get the returned pages by first request.
					Set<HtmlPage> actiontData = this.actionManager.get(key);
					// Put loaded data on document root message manager
					documentRootManager.put(actiontData, new LinkedHashMap<InputDocument, HtmlPage>());
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
		case CONSTANT_CONTROL_LOADED:
			if(e.getSource() instanceof ConstantControl) {
				Constant message = ConstantControl.class.cast(e.getSource()).getMessage();
				@SuppressWarnings("unchecked")
				Set<Message> set = (Set<Message>) getControl().get(Data.CONSTANT);
				if(set.contains(message)) {
					ConstantManager constantManager = new ConstantManager(message, this);
					constantManagers.put(message, constantManager);
					addChildManager(constantManager);
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