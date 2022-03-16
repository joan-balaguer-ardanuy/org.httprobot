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
	extends AbstractControl<Document> {

	/**
	 * 992409643204748504L
	 */
	private static final long serialVersionUID = 992409643204748504L;
	
	ActionControl actionControl;
	ContentTypeRefControl contentTypeRefControl;
	FieldRootControl fieldRootControl;
	DocumentControl documentControl;
	
	@XmlElement
	public ActionControl getActionControl() {
		return actionControl;
	}
	public void setActionControl(ActionControl actionControl) {
		this.actionControl = actionControl;
	}
	@XmlElement
	public ContentTypeRefControl getContentTypeRefControl() {
		return contentTypeRefControl;
	}
	public void setContentTypeRefControl(ContentTypeRefControl contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	@XmlElement
	public FieldRootControl getFieldRootControl() {
		return fieldRootControl;
	}
	public void setFieldRootControl(FieldRootControl fieldRootControl) {
		this.fieldRootControl = fieldRootControl;
	}
	@XmlElement
	public DocumentControl getDocumentControl() {
		return documentControl;
	}
	public void setDocumentControl(DocumentControl documentControl) {
		this.documentControl = documentControl;
	}
	
	public DocumentControl() {
		super();
	}
	public DocumentControl(Document message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {

				Document document = Document.class.cast(e.getValue());
				
				if(document.getAction() != null) {
					new ActionControl(document.getAction(), this);
				} else {
					throw new Error("DocumentControl.OnControlInitialized: Action XML message expected.");
				}
				if(document.getContentTypeRef() != null) {
					new ContentTypeRefControl(document.getContentTypeRef(), this);
				}
				if(document.getFieldRoot() != null) {
					new FieldRootControl(document.getFieldRoot(), this);
				}
				if(document.getDocument() != null) {
					new DocumentControl(document.getDocument(), this);
				}
			}
			else if (e.getSource() instanceof DocumentControl) {
				documentControl = DocumentControl.class.cast(e.getSource());
				addChild(documentControl);
			}
			else if (e.getSource() instanceof FieldRootControl) {
				fieldRootControl = FieldRootControl.class.cast(e.getSource());
				addChild(fieldRootControl);
			}
			else if (e.getSource() instanceof ContentTypeRefControl) {
				contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				addChild(contentTypeRefControl);
			} else if (e.getSource() instanceof ActionControl) {
				actionControl = ActionControl.class.cast(e.getSource());
				addChild(actionControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof DocumentControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.DOCUMENT, e.getValue());
				}
			}
			else if(e.getSource() instanceof FieldRootControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.FIELD_ROOT, e.getValue());
				}	
			}
			else if(e.getSource() instanceof ContentTypeRefControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}	
			}
			else if(e.getSource() instanceof ActionControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.ACTION, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}