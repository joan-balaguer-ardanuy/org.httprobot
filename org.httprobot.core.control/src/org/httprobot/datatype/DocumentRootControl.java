package org.httprobot.datatype;

import java.util.LinkedHashSet;
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
public final class DocumentRootControl
	extends Control<DocumentRoot> {

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
		setMessage(new DocumentRoot());
	}
	public DocumentRootControl(DocumentRoot message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			
			DocumentRoot documentRoot = DocumentRoot.class.cast(e.getMessage());
			documentControl = new LinkedHashSet<DocumentControl>();
			
			if(documentRoot.getAction() != null) {
				new ActionControl(documentRoot.getAction(), this);
			}
			else {
				throw new Error("DocumentRootControl.OnControlInitialized: Action XML message expected.");
			}
			if(documentRoot.getContentTypeRef() != null) {
				new ContentTypeRefControl(documentRoot.getContentTypeRef(), this);
			}
			else {
				throw new Error("DocumentRootControl.OnControlInitialized: ContentTypeRef XML message expected.");
			}
			for(Document document : documentRoot.getDocument()) {
				new DocumentControl(document, this);
			}
			if(documentRoot.getFieldRoot() != null) {
				new FieldRootControl(documentRoot.getFieldRoot(), this);
			}
		}
		else if(e.getSource() instanceof FieldRootControl) {
			fieldRootControl = FieldRootControl.class.cast(e.getSource());
			addChildControl(fieldRootControl);
		}
		else if(e.getSource() instanceof DocumentControl) {
			DocumentControl childDocumentControl = DocumentControl.class.cast(e.getSource());
			documentControl.add(childDocumentControl);
			addChildControl(childDocumentControl);
		}
		else if(e.getSource() instanceof ContentTypeRefControl) {
			contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			addChildControl(contentTypeRefControl);
		}
		else if(e.getSource() instanceof ActionControl) {
			actionControl = ActionControl.class.cast(e.getSource());
			addChildControl(actionControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			DocumentRoot documentRoot = DocumentRoot.class.cast(e.getMessage());

			// Check if control has child XML message controls
			if (hasChildControls()) {
				// Iterate through child XML message controls
				while (hasNext()) {
					// Get next child XML message control
					ControlListener control = next();

					if (control instanceof ActionControl ? 
							documentRoot.getAction() != null ? 
									actionControl.equals(control) 
									: false : false) {
						actionControl.loadControl();
					} else if (control instanceof ContentTypeRefControl ? 
							documentRoot.getContentTypeRef() != null ? 
									contentTypeRefControl.equals(control) 
									: false : false) {
						contentTypeRefControl.loadControl();
					} else if (control instanceof DocumentControl ? 
							documentRoot.getDocument() != null ? 
									documentControl.contains(control) 
									: false : false) {
						DocumentControl childDocumentControl = DocumentControl.class.cast(control);

						for (Document document : documentRoot.getDocument()) {
							if (childDocumentControl.getUuid().equals(document.getUuid())) {
								childDocumentControl.loadControl();
								break;
							}
						}
					} else if (control instanceof FieldRootControl ? 
							documentRoot.getFieldRoot() != null ? 
									fieldRootControl.equals(control)
									: false : false) {
						fieldRootControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.DOCUMENT_ROOT_CONTROL_LOADED));
			}
		} else if (e.getSource() instanceof FieldRootControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.FIELD_ROOT, e.getMessage());
			}
		} else if (e.getSource() instanceof DocumentControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.DOCUMENT, e.getMessage());
			}
		} else if (e.getSource() instanceof ContentTypeRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_REF, e.getMessage());
			}
		} else if (e.getSource() instanceof ActionControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.ACTION, getMessage());
			}
		}
	}
}