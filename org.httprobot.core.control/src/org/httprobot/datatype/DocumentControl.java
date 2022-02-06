package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DocumentControl 
	extends Control<Document> {

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
		setMessage(new Document());
	}
	public DocumentControl(Document message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {

			Document document = Document.class.cast(e.getMessage());
			
			if(document.getAction() != null) {
				new ActionControl(document.getAction(), this);
			}
			else {
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
			addChildControl(documentControl);
		}
		else if (e.getSource() instanceof FieldRootControl) {
			fieldRootControl = FieldRootControl.class.cast(e.getSource());
			addChildControl(fieldRootControl);
		}
		else if (e.getSource() instanceof ContentTypeRefControl) {
			contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			addChildControl(contentTypeRefControl);
		} else if (e.getSource() instanceof ActionControl) {
			actionControl = ActionControl.class.cast(e.getSource());
			addChildControl(actionControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
		
			Document document = Document.class.cast(e.getMessage());
			
			if(hasChildControls()) {
				
				while(hasNext()) {
					
					ControlListener control = next();
					
					if(control instanceof ActionControl ?
							document.getAction() != null ?
									actionControl.equals(control)
									: false : false) {
						actionControl.loadControl();
					}
					else if(control instanceof ContentTypeRefControl ?
							document.getContentTypeRef() != null ?
									contentTypeRefControl.equals(control)
									: false : false) {
						contentTypeRefControl.loadControl();
					}
					else if(control instanceof FieldRootControl ?
							document.getFieldRoot() != null ?
									fieldRootControl.equals(control)
									: false : false) {
						fieldRootControl.loadControl();
					}
					else if(control instanceof DocumentControl ?
							document.getDocument() != null ?
									documentControl.equals(control)
									: false : false) {
						documentControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.DOCUMENT_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof DocumentControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.DOCUMENT, e.getMessage());
			}
		}
		else if(e.getSource() instanceof FieldRootControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.FIELD_ROOT, e.getMessage());
			}	
		}
		else if(e.getSource() instanceof ContentTypeRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_REF, e.getMessage());
			}	
		}
		else if(e.getSource() instanceof ActionControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.ACTION, e.getMessage());
			}	
		}
	}
}