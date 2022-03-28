package org.httprobot.datatype;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.content.ContentTypeRefControl;
import org.httprobot.event.EventArgs;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;
import org.httprobot.unit.ActionControl;

@XmlRootElement
public final class DataSourceControl 
	extends AbstractControl {

	/**
	 * -3180277017791845005L
	 */
	private static final long serialVersionUID = -3180277017791845005L;

	/**
	 * The action control
	 */
	ActionControl actionControl;
	/**
	 * The content type reference control
	 */
	ContentTypeRefControl contentTypeRefControl;
	/**
	 * The document root control
	 */
	DocumentRootControl documentRootControl;
	/**
	 * The constant control data set
	 */
	LinkedHashSet<ConstantControl> constantControl;
	
	/**
	 * Returns the {@link ActionControl} of current instance.
	 * @return the {@link ActionControl} of current instance.
	 */
	@XmlElement
	public ActionControl getActionControl() {
		return actionControl;
	}
	/**
	 * Sets the {@link ActionControl} of current instance.
	 * @param actionControl {@link ActionControl} the action control.
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
	 * @param contentTypeRefControl {@link ContentTypeRefControl} content type reference control
	 */
	public void setContentTypeRefControl(ContentTypeRefControl contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	/**
	 * Returns the {@link DocumentRootControl} property.
	 * @return the {@link DocumentRootControl} property.
	 */
	@XmlElement
	public DocumentRootControl getDocumentRootControl() {
		return documentRootControl;
	}
	/**
	 * Sets the {@link DocumentRootControl} property.
	 * @param documentRootControl {@link DocumentRootControl} the document root XML message control.
	 */
	public void setDocumentRootControl(DocumentRootControl documentRootControl) {
		this.documentRootControl = documentRootControl;
	}
	/**
	 * Returns the {@link LinkedHashSet} of {@link ConstantControl}.
	 * @return the {@link LinkedHashSet} of {@link ConstantControl}.
	 */
	@XmlElement
	public LinkedHashSet<ConstantControl> getConstantControl() {
		return constantControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link ConstantControl}.
	 * @param constantControl {@link LinkedHashSet} of {@link ConstantControl} the constant XML message controls
	 */
	public void setConstantControl(LinkedHashSet<ConstantControl> constantControl) {
		this.constantControl = constantControl;
	}
	
	@Override
	public DataSource getMessage() {
		return (DataSource) super.getMessage();
	}
	
	/**
	 * {@link DataSourceControl} default class constructor.
	 */
	public DataSourceControl() {
		super();
	}
	/**
	 * {@link DataSourceControl} class constructor.
	 * @param message {@link DataSource} the message
	 * @param parent {@link Control} the parent instance
	 */
	public DataSourceControl(DataSource message, Control parent) {
		super(message, parent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				DataSource dataSource = (DataSource) e.getValue();

				if (dataSource.getAction() != null) {
					// if action exists instance new control
					new ActionControl(dataSource.getAction(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: Action XML message missing.");
				}
				if (dataSource.getContentTypeRef() != null) {
					// if content type reference exists instance new control
					new ContentTypeRefControl(dataSource.getContentTypeRef(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: ContentTypeRef XML message missing.");
				}
				if (dataSource.getDocumentRoot() != null) {
					// if document root exists instance new control
					new DocumentRootControl(dataSource.getDocumentRoot(), this);
				} else {
					throw new Error("DataSourceControl.OnEventReceived: DocumentRoot XML message missing.");
				}
				if(dataSource.getConstant() != null) {
					// instance constant control message set
					constantControl = new LinkedHashSet<ConstantControl>();
					// for each constant in DataSource XML message
					for(Constant constant : dataSource.getConstant()) {
						// This instance listens for it's OnEventReceived event
						new ConstantControl(constant, this);
					}
				} else {
					throw new Error("DataSourceControl.OnEventReceived: Constants XML messages missing.");
				}
			} else if(e.getSource() instanceof DocumentRootControl) {
				// cast event source and set property
				documentRootControl = (DocumentRootControl) e.getSource();
				// store XML message control
				addChild(documentRootControl);
			} else if(e.getSource() instanceof ActionControl) {
				// cast event source and set property
				actionControl = (ActionControl) e.getSource();
				// store XML message control
				addChild(actionControl);
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				// cast event source and set property
				contentTypeRefControl = (ContentTypeRefControl) e.getSource();
				// store XML message control
				addChild(contentTypeRefControl);
			} else if(e.getSource() instanceof ConstantControl) {
				// cast event source
				ConstantControl constantControl = (ConstantControl) e.getSource();
				// store XML message control
				getConstantControl().add(constantControl);
				addChild(constantControl);
			}
			break;
		case CONTROL_LOADED:
			// check by instance type
			if (e.getSource() instanceof DocumentRootControl) {
				// check if source is a child
				if (getChildren().contains(e.getSource())) {
					// set data to control
					put(Data.DOCUMENT_ROOT, e.getValue());
				}
			} else if (e.getSource() instanceof ActionControl) {
				// check if source is a child
				if (getChildren().contains(e.getSource())) {
					// set data to control
					put(Data.ACTION, e.getValue());
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				// check if source is a child
				if (getChildren().contains(e.getSource())) {
					// set data to control
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			} else if (e.getSource() instanceof ConstantControl) {
				// check if source is a child
				if (getChildren().contains(e.getSource())) {
					// check if property exists
					if(get(Data.CONSTANT) == null) {
						// instance new set of XML messages
						Set<XML> set = new LinkedHashSet<XML>();
						// set data to set
						set.add((XML) e.getValue());
						// set data
						put(Data.CONSTANT, set);
					} else {
						// get property data
						Object set = get(Data.CONSTANT);
						// set data to XML message set
						((Set<XML>) set).add((XML) e.getValue());
					}
				}
			}
			break;
		default:
			break;
		}
	}
}