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
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DataSourceControl 
	extends AbstractControl<DataSource> {

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
		return constantControl;
	}
	public void setConstantControl(LinkedHashSet<ConstantControl> constantControl) {
		this.constantControl = constantControl;
	}
	
	public DataSourceControl() {
		super();
		constantControl = new LinkedHashSet<ConstantControl>();
	}
	public DataSourceControl(DataSource message, Control parent) {
		super(message, parent);
		constantControl = new LinkedHashSet<ConstantControl>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				DataSource dataSource = DataSource.class.cast(e.getValue());

				if (dataSource.getAction() != null) {
					new ActionControl(dataSource.getAction(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: Action XML message missing.");
				}
				if (dataSource.getContentTypeRef() != null) {
					new ContentTypeRefControl(dataSource.getContentTypeRef(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: ContentTypeRef XML message missing.");
				}
				if (dataSource.getDocumentRoot() != null) {
					new DocumentRootControl(dataSource.getDocumentRoot(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: DocumentRoot XML message missing.");
				}
				if(dataSource.getConstant() != null) {
					// instance constant control message set
					constantControl = new LinkedHashSet<ConstantControl>();
					// for each constant in DataSource XML message
					for(Constant constant : dataSource.getConstant()) {
						// This instance listens for it's OnCommandReceived event
						new ConstantControl(constant, this);
					}
				} else {
					throw new Error("DataSourceControl.OnEventReceived: Constants XML messages missing.");
				}
			} else if(e.getSource() instanceof DocumentRootControl) {
				documentRootControl = DocumentRootControl.class.cast(e.getSource());
				addChild(documentRootControl);
			} else if(e.getSource() instanceof ActionControl) {
				actionControl = ActionControl.class.cast(e.getSource());
				// save XML message control
				addChild(actionControl);
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				// save XML message control
				addChild(contentTypeRefControl);
			} else if(e.getSource() instanceof ConstantControl) {
				// cast event source
				ConstantControl constantControl = ConstantControl.class.cast(e.getSource());
				// save XML message control
				getConstantControl().add(constantControl);
				addChild(constantControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				DataSource dataSource = DataSource.class.cast(e.getValue());
				put(Data.SOURCE_NAME, dataSource.getSourceName());

				// check if control has child XML message controls
				if (hasChildren()) {
					// iterate through child XML message controls
					while (hasNext()) {
						// get next child XML message control
						Control control = (Control) next();

						if (control instanceof ActionControl ? 
								actionControl.equals(control) 
								: false) {
							actionControl.load();
						} else if (control instanceof ContentTypeRefControl ? 
								contentTypeRefControl.equals(control)
								: false) {
							contentTypeRefControl.load();
						} else if (control instanceof DocumentRootControl ? 
								documentRootControl.equals(control) 
								: false) {
							documentRootControl.load();
						} else if (control instanceof ConstantControl ? 
								constantControl.contains(control) 
								: false) {
							// cast message control
							ConstantControl constantControl = ConstantControl.class.cast(control);
							// look for matching constant control's XML message.
							for(Constant constant : dataSource.getConstant()) {
								// by UUID
								if(constantControl.getName().equals(constant.getName())) {
									// load XML message control.
									constantControl.load();
								}
							}
						}
					}
					// Set control ready to be iterated again.
					reset();
				}
			} else if (e.getSource() instanceof DocumentRootControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.DOCUMENT_ROOT, e.getValue());
				}
			} else if (e.getSource() instanceof ActionControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.ACTION, e.getValue());
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			} else if (e.getSource() instanceof ConstantControl) {
				if (getChildren().contains(e.getSource())) {
					if(get(Data.CONSTANT) == null) {
						Set<Message> set = new LinkedHashSet<Message>();
						set.add((Message) e.getValue());
						put(Data.CONSTANT, set);
					} else {
						Object set = get(Data.CONSTANT);
						((Set<Message>) set).add((Message) e.getValue());
					}
				}
			}
			break;
		default:
			break;
		}
	}
}