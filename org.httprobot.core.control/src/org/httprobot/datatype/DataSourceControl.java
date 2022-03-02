package org.httprobot.datatype;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DataSourceControl 
	extends Control<DataSource> {

	/**
	 * -3180277017791845005L
	 */
	private static final long serialVersionUID = -3180277017791845005L;

	ActionControl actionControl;
	ContentTypeRefControl contentTypeRefControl;
	DocumentRootControl documentRootControl;
	LinkedHashSet<ConstantControl> constantControl;
	
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
	public DocumentRootControl getDocumentRootControl() {
		return documentRootControl;
	}
	public void setDocumentRootControl(DocumentRootControl documentRootControl) {
		this.documentRootControl = documentRootControl;
	}
	@XmlElement
	public LinkedHashSet<ConstantControl> getConstantControl() {
		if(constantControl == null) {
			constantControl = new LinkedHashSet<ConstantControl>();
		}
		return constantControl;
	}
	public void setConstantControl(LinkedHashSet<ConstantControl> constantControl) {
		this.constantControl = constantControl;
	}
	
	
	public DataSourceControl() {
		super();
	}
	public DataSourceControl(DataSource message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			DataSource dataSource = DataSource.class.cast(e.getMessage());

			if (dataSource.getAction() != null) {
				new ActionControl(dataSource.getAction(), this);
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: Action XML message missing.");
			}
			if (dataSource.getContentTypeRef() != null) {
				new ContentTypeRefControl(dataSource.getContentTypeRef(), this);
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: ContentTypeRef XML message missing.");
			}
			if (dataSource.getDocumentRoot() != null) {
				new DocumentRootControl(dataSource.getDocumentRoot(), this);
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: DocumentRoot XML message missing.");
			}
			if(dataSource.getConstant() != null) {
				// Instance constant control message set
				constantControl = new LinkedHashSet<ConstantControl>();
				// For each constant in DataSource XML message
				for(Constant constant : dataSource.getConstant()) {
					// This instance listens for it's OnCommandReceived event
					new ConstantControl(constant, this);
				}
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: Constants XML messages missing.");
			}
		} else if(e.getSource() instanceof DocumentRootControl) {
			documentRootControl = DocumentRootControl.class.cast(e.getSource());
			addChildControl(documentRootControl);
		} else if(e.getSource() instanceof ActionControl) {
			actionControl = ActionControl.class.cast(e.getSource());
			//Store XML message control
			addChildControl(actionControl);
		} else if(e.getSource() instanceof ContentTypeRefControl) {
			contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			//Store XML message control
			addChildControl(contentTypeRefControl);
		} else if(e.getSource() instanceof ConstantControl) {
			// Cast event source
			ConstantControl constantControl = ConstantControl.class.cast(e.getSource());
			// Store XML message control
			getConstantControl().add(constantControl);
			addChildControl(constantControl);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {

			DataSource dataSource = DataSource.class.cast(e.getMessage());
			put(Data.SOURCE_NAME, dataSource.getSourceName());

			// Check if control has child XML message controls
			if (hasChildControls()) {
				// Iterate through child XML message controls
				while (hasNext()) {
					// Get next child XML message control
					ControlListener control = next();

					if (control instanceof ActionControl ? 
							actionControl.equals(control) 
							: false) {
						actionControl.loadControl();
					} else if (control instanceof ContentTypeRefControl ? 
							contentTypeRefControl.equals(control)
							: false) {
						contentTypeRefControl.loadControl();
					} else if (control instanceof DocumentRootControl ? 
							documentRootControl.equals(control) 
							: false) {
						documentRootControl.loadControl();
					} else if (control instanceof ConstantControl ? 
							constantControl.contains(control) 
							: false) {
						// Cast message control
						ConstantControl constantControl = ConstantControl.class.cast(control);
						// Look for matching constant control's XML message.
						for(Constant constant : dataSource.getConstant()) {
							// By UUID
							if(constantControl.getUuid().equals(constant.getUuid())) {
								// Load XML message control.
								constantControl.loadControl();
							}
						}
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			}
		} else if (e.getSource() instanceof DocumentRootControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.DOCUMENT_ROOT, e.getMessage());
			}
		} else if (e.getSource() instanceof ActionControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.ACTION, e.getMessage());
			}
		} else if (e.getSource() instanceof ContentTypeRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_REF, e.getMessage());
			}
		} else if (e.getSource() instanceof ConstantControl) {
			if (getChildControls().contains(e.getSource())) {
				if(get(Data.CONSTANT) == null) {
					Set<Message> set = new LinkedHashSet<Message>();
					set.add(e.getMessage());
					put(Data.CONSTANT, set);
				} else {
					Object set = get(Data.CONSTANT);
					((Set<Message>) set).add(e.getMessage());
				}
			}
		}
	}
}