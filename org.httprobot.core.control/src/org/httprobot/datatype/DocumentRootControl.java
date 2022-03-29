package org.httprobot.datatype;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.EventArgs;
import org.httprobot.unit.ActionControl;

/**
 * The document root XML message control class.
 * Inherits {@link AbstractControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class DocumentRootControl
	extends AbstractControl {

	/**
	 * 5728744970361838580L
	 */
	private static final long serialVersionUID = 5728744970361838580L;

	/**
	 * The action XML message control.
	 */
	ActionControl actionControl;
	/**
	 * The content type reference XML message control.
	 */
	ContentTypeRefControl contentTypeRefControl;
	/**
	 * The set of docuemnt XML message control
	 */
	LinkedHashSet<DocumentControl> documentControl;
	/**
	 * The field root XML message control.
	 */
	FieldRootControl fieldRootControl;
	
	/**
	 * Returns the {@link ActionControl} property.
	 * @return the {@link ActionControl} property.
	 */
	@XmlElement
	public ActionControl getActionControl() {
		return actionControl;
	}
	/**
	 * Sets the {@link ActionControl} property.
	 * @param actionControl {@link ActionControl} the action XML message control.
	 */
	public void setActionControl(ActionControl actionControl) {
		this.actionControl = actionControl;
	}
	/**
	 * Returns the {@link ContentTypeRefControl} property.
	 * @return the {@link ContentTypeRefControl} property.
	 */
	@XmlElement
	public ContentTypeRefControl getContentTypeRefControl() {
		return contentTypeRefControl;
	}
	/**
	 * Sets the {@link ContentTypeRefControl} property.
	 * @param contentTypeRefControl {@link ContentTypeRefControl} the content type reference message control property.
	 */
	public void setContentTypeRefControl(ContentTypeRefControl contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	/**
	 * Returns the {@link LinkedHashSet} of {@link DocumentControl}.
	 * @return the {@link LinkedHashSet} of {@link DocumentControl}.
	 */
	@XmlElement
	public LinkedHashSet<DocumentControl> getDocumentControl() {
		return documentControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link DocumentControl}.
	 * @param documentControl {@link LinkedHashSet} of {@link DocumentControl} the set of document message control property
	 */
	public void setDocumentControl(LinkedHashSet<DocumentControl> documentControl) {
		this.documentControl = documentControl;
	}
	/**
	 * Returns the {@link FieldRootControl} property.
	 * @return the {@link FieldRootControl} property.
	 */
	@XmlElement
	public FieldRootControl getFieldRootControl() {
		return fieldRootControl;
	}
	/**
	 * Sets the {@link FieldRootControl} property.
	 * @param fieldRootControl {@link FieldRootControl} field root property.
	 */
	public void setFieldRootControl(FieldRootControl fieldRootControl) {
		this.fieldRootControl = fieldRootControl;
	}
	@Override
	public DocumentRoot getMessage() {
		return (DocumentRoot) super.getMessage();
	}
	
	/**
	 * {@link DocumentRootControl} default class constructor.
	 */
	public DocumentRootControl() {
		super();
	}
	/**
	 * {@link DocumentRootControl} class constructor.
	 * @param message {@link DocumentRoot} the message
	 * @param parent {@link Control} the parent instance
	 */
	public DocumentRootControl(DocumentRoot message, Control parent) {
		super(message, parent);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast event's value
				DocumentRoot documentRoot = (DocumentRoot) e.getValue();
				// instance a set of document message control
				documentControl = new LinkedHashSet<DocumentControl>();
				
				if(documentRoot.getAction() != null) {
					// if action exists instance new action message control
					new ActionControl(documentRoot.getAction(), this);
				} else {
					// required member
					throw new Error("DocumentRootControl.OnEventReceived: Action XML message expected.");
				}
				if(documentRoot.getContentTypeRef() != null) {
					// if content type reference exists instance new content type reference message control
					new ContentTypeRefControl(documentRoot.getContentTypeRef(), this);
				} else {
					// required member
					throw new Error("DocumentRootControl.OnEventReceived: ContentTypeRef XML message expected.");
				}
				// for each document message instance new docuemnt message control.
				if(!documentRoot.getDocument().isEmpty()) {
					for(Document document : documentRoot.getDocument()) {
						new DocumentControl(document, this);
					}
				} else {
					throw new Error("DocumentRootControl.OnEventReceived: Document XML message expected.");
				}
				if(documentRoot.getFieldRoot() != null) {
					// if field root exists instance new field root message control
					new FieldRootControl(documentRoot.getFieldRoot(), this);
				}
			} else if(e.getSource() instanceof FieldRootControl) {
				// cast source and set data
				fieldRootControl = (FieldRootControl) e.getSource();
				// store property as child
				addChild(fieldRootControl);
			}
			else if(e.getSource() instanceof DocumentControl) {
				// cast source and set data
				DocumentControl childDocumentControl = (DocumentControl) e.getSource();
				documentControl.add(childDocumentControl);
				// store property as child
				addChild(childDocumentControl);
			}
			else if(e.getSource() instanceof ContentTypeRefControl) {
				// cast source and set data
				contentTypeRefControl = (ContentTypeRefControl) e.getSource();
				// store property as child
				addChild(contentTypeRefControl);
			}
			else if(e.getSource() instanceof ActionControl) {
				// cast source and set data
				actionControl = (ActionControl) e.getSource();
				// store property as child
				addChild(actionControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource() instanceof FieldRootControl) {
				// check event source is a child exension
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.FIELD_ROOT, e.getValue());
				}
			} else if (e.getSource() instanceof DocumentControl) {
				// check event source is a child exension
				if (getChildren().contains(e.getSource())) {
					// cast event's value
					Document document = (Document) e.getValue();
					// check if document's data exists
					if(get(Data.DOCUMENT) == null) {
						// instance new set
						Set<Message> set = new LinkedHashSet<Message>();
						// add first document value to set
						set.add(document);
						// set data
						put(Data.DOCUMENT, set);
					} else {
						// add message to data
						Object set = get(Data.DOCUMENT);
						// add document to set
						((Set<Message>) set).add(document);
					}
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				// check event source is a child exension
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			} else if (e.getSource() instanceof ActionControl) {
				// check event source is a child exension
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.ACTION, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}