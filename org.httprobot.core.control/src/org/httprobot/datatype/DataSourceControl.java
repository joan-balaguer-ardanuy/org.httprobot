package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.parameter.ServerUrlControl;
import org.httprobot.parameter.StartUrlControl;
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
	ServerUrlControl serverUrlControl;
	StartUrlControl startUrlControl;
	
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
	public ServerUrlControl getServerUrlControl() {
		return serverUrlControl;
	}
	public void setServerUrlControl(ServerUrlControl serverUrlControl) {
		this.serverUrlControl = serverUrlControl;
	}
	@XmlElement
	public StartUrlControl getStartUrlControl() {
		return startUrlControl;
	}
	public void setStartUrlControl(StartUrlControl startUrlControl) {
		this.startUrlControl = startUrlControl;
	}
	
	public DataSourceControl() {
		super();
		
		setMessage(new DataSource());
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
			if (dataSource.getServerUrl() != null) {
				new ServerUrlControl(dataSource.getServerUrl(), this);
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: ServerUrl XML message missing.");
			}
			if (dataSource.getStartUrl() != null) {
				new StartUrlControl(dataSource.getStartUrl(), this);
			} else {
				throw new Error("DataSourceControl.OnControlInitialized: StartUrl XML message missing.");
			}
		}
		else if(e.getSource() instanceof DocumentRootControl) {
			documentRootControl = DocumentRootControl.class.cast(e.getSource());
			addChildControl(documentRootControl);
		}
		else if(e.getSource() instanceof ActionControl) {
			actionControl = ActionControl.class.cast(e.getSource());
			//Store XML message control
			addChildControl(actionControl);
		}
		else if(e.getSource() instanceof ContentTypeRefControl) {
			contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			//Store XML message control
			addChildControl(contentTypeRefControl);
		}
		else if(e.getSource() instanceof StartUrlControl) {
			//Cast event source
			startUrlControl = StartUrlControl.class.cast(e.getSource());
			//Store XML message control
			addChildControl(startUrlControl);
		}
		else if(e.getSource() instanceof ServerUrlControl) {
			serverUrlControl = ServerUrlControl.class.cast(e.getSource());
			addChildControl(serverUrlControl);
		}
	}
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
					} else if (control instanceof ServerUrlControl ? 
							serverUrlControl.equals(control) 
							: false) {
						serverUrlControl.loadControl();
					} else if (control instanceof StartUrlControl ? 
							startUrlControl.equals(control) 
							: false) {
						startUrlControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.DATA_SOURCE_CONTROL_LOADED));
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
		} else if (e.getSource() instanceof StartUrlControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.START_URL, e.getMessage());
			}
		} else if (e.getSource() instanceof ServerUrlControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.SERVER_URL, e.getMessage());
			}
		}
	}
}