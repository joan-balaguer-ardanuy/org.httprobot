package org.httprobot.datatype;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.EventArgs;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DocumentRootControl
	extends AbstractControl<DocumentRoot> {

	/**
	 * 5728744970361838580L
	 */
	private static final long serialVersionUID = 5728744970361838580L;

	ActionControl actionControl;
	ContentTypeRefControl contentTypeRefControl;
	LinkedHashSet<DocumentControl> documentControl;
	FieldRootControl fieldRootControl;
	
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
	public LinkedHashSet<DocumentControl> getDocumentControl() {
		return documentControl;
	}
	public void setDocumentControl(LinkedHashSet<DocumentControl> documentControl) {
		this.documentControl = documentControl;
	}
	@XmlElement
	public FieldRootControl getFieldRootControl() {
		return fieldRootControl;
	}
	public void setFieldRootControl(FieldRootControl fieldRootControl) {
		this.fieldRootControl = fieldRootControl;
	}
	
	public DocumentRootControl() {
		super();
	}
	public DocumentRootControl(DocumentRoot message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				
				DocumentRoot documentRoot = DocumentRoot.class.cast(e.getValue());
				documentControl = new LinkedHashSet<DocumentControl>();
				
				if(documentRoot.getAction() != null) {
					new ActionControl(documentRoot.getAction(), this);
				} else {
					throw new Error("DocumentRootControl.OnEventReceived: Action XML message expected.");
				}
				if(documentRoot.getContentTypeRef() != null) {
					new ContentTypeRefControl(documentRoot.getContentTypeRef(), this);
				} else {
					throw new Error("DocumentRootControl.OnEventReceived: ContentTypeRef XML message expected.");
				}
				for(Document document : documentRoot.getDocument()) {
					new DocumentControl(document, this);
				}
				if(documentRoot.getFieldRoot() != null) {
					new FieldRootControl(documentRoot.getFieldRoot(), this);
				}
			} else if(e.getSource() instanceof FieldRootControl) {
				fieldRootControl = FieldRootControl.class.cast(e.getSource());
				addChild(fieldRootControl);
			}
			else if(e.getSource() instanceof DocumentControl) {
				DocumentControl childDocumentControl = DocumentControl.class.cast(e.getSource());
				documentControl.add(childDocumentControl);
				addChild(childDocumentControl);
			}
			else if(e.getSource() instanceof ContentTypeRefControl) {
				contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				addChild(contentTypeRefControl);
			}
			else if(e.getSource() instanceof ActionControl) {
				actionControl = ActionControl.class.cast(e.getSource());
				addChild(actionControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource() instanceof FieldRootControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.FIELD_ROOT, e.getValue());
				}
			} else if (e.getSource() instanceof DocumentControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.DOCUMENT, e.getValue());
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			} else if (e.getSource() instanceof ActionControl) {
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