package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.EventArgs;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DocumentControl 
	extends AbstractControl {

	/**
	 * 992409643204748504L
	 */
	private static final long serialVersionUID = 992409643204748504L;
	
	/**
	 * The action XML message control.
	 */
	ActionControl actionControl;
	/**
	 * The content type reference XML message control.
	 */
	ContentTypeRefControl contentTypeRefControl;
	/**
	 * The field root XML message control.
	 */
	FieldRootControl fieldRootControl;
	/**
	 * The document XML message control.
	 */
	DocumentControl documentControl;
	
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
	 * @param actionControl {@link ActionControl} the action XML message control
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
	 * @param contentTypeRefControl {@link ContentTypeRefControl} the content type reference XML message control
	 */
	public void setContentTypeRefControl(ContentTypeRefControl contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	/**
	 * Returns the {@link FieldRootControl} property
	 * @return the {@link FieldRootControl} property
	 */
	@XmlElement
	public FieldRootControl getFieldRootControl() {
		return fieldRootControl;
	}
	/**
	 * Sets the {@link FieldRootControl} property
	 * @param fieldRootControl {@link FieldRootControl} the field root XML message control
	 */
	public void setFieldRootControl(FieldRootControl fieldRootControl) {
		this.fieldRootControl = fieldRootControl;
	}
	/**
	 * Returns the {@link DocumentControl} property
	 * @return the {@link DocumentControl} property
	 */
	@XmlElement
	public DocumentControl getDocumentControl() {
		return documentControl;
	}
	/**
	 * Sets the {@link DocumentControl} property
	 * @param documentControl {@link DocumentControl} the document XML message control
	 */
	public void setDocumentControl(DocumentControl documentControl) {
		this.documentControl = documentControl;
	}
	@Override
	@XmlElement
	public Document getMessage() {
		return (Document) super.getMessage();
	}
	
	/**
	 * {@link DocumentControl} default class constructor.
	 */
	public DocumentControl() {
		super();
	}
	/**
	 * {@link DocumentControl} class constructor.
	 * @param message {@link Document} the message
	 * @param parent {@link Control} parent instance
	 */
	public DocumentControl(Document message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {

				Document document = (Document) e.getValue();
				
				if(document.getAction() != null) {
					new ActionControl(document.getAction(), this);
				} else {
					throw new Error("DocumentControl.OnEventReceived: Action XML message expected.");
				}
				if(document.getContentTypeRef() != null) {
					new ContentTypeRefControl(document.getContentTypeRef(), this);
				} else {
					throw new Error("DocumentControl.OnEventReceived: Content type reference XML message expected.");
				}
				if(document.getFieldRoot() != null) {
					new FieldRootControl(document.getFieldRoot(), this);
				} else {
					throw new Error("DocumentControl.OnEventReceived: field root XML message expected.");
				}
				if(document.getDocument() != null) {
					new DocumentControl(document.getDocument(), this);
				}
			}
			else if (e.getSource() instanceof DocumentControl) {
				// set document control property
				documentControl = (DocumentControl) e.getSource();
				// store document 
				addChild(documentControl);
			}
			else if (e.getSource() instanceof FieldRootControl) {
				// set field root control property
				fieldRootControl = (FieldRootControl) e.getSource();
				// store document 
				addChild(fieldRootControl);
			}
			else if (e.getSource() instanceof ContentTypeRefControl) {
				// set content type reference control property
				contentTypeRefControl = (ContentTypeRefControl) e.getSource();
				// store content type reference XML message control
				addChild(contentTypeRefControl);
			} else if (e.getSource() instanceof ActionControl) {
				// set action XML message control property
				actionControl = (ActionControl) e.getSource();
				// store action XML message control
				addChild(actionControl);
			}
			break;
		case CONTROL_LOADED:
			// check by instance type
			if(e.getSource() instanceof DocumentControl) {
				// check if source is a child member
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.DOCUMENT, e.getValue());
				}
			}
			else if(e.getSource() instanceof FieldRootControl) {
				// check if source is a child member
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.FIELD_ROOT, e.getValue());
				}
			}
			else if(e.getSource() instanceof ContentTypeRefControl) {
				// check if source is a child member
				if (getChildren().contains(e.getSource())) {
					// set data
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			}
			else if(e.getSource() instanceof ActionControl) {
				// check if source is a child member
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