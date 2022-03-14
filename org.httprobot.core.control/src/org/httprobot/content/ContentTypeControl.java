package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ContentTypeControl 
	extends AbstractControl<ContentType> {

	/**
	 * -3238737080863615932L
	 */
	private static final long serialVersionUID = -3238737080863615932L;
	
	LinkedHashSet<FieldRefControl> fieldRefControl;
	LinkedHashSet<ContentTypeRefControl> contentTypeRefControl;
	
	@XmlElement
	public LinkedHashSet<FieldRefControl> getFieldRefControl() {
		return fieldRefControl;
	}
	public void setFieldRefControl(LinkedHashSet<FieldRefControl> fieldRefControl) {
		this.fieldRefControl = fieldRefControl;
	}
	@XmlElement
	public LinkedHashSet<ContentTypeRefControl> getContentTypeRefControl() {
		return contentTypeRefControl;
	}
	public void setContentTypeRefControl(LinkedHashSet<ContentTypeRefControl> contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	public ContentTypeControl() {
		super();
	}
	public ContentTypeControl(ContentType message, Control parent) {
		super(message, parent);
	}
	
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				// cast message
				ContentType contentType = ContentType.class.cast(e.getValue());

				// instance fielRefControl and contentTypeRefControl sets
				fieldRefControl = new LinkedHashSet<FieldRefControl>();
				contentTypeRefControl = new LinkedHashSet<ContentTypeRefControl>();
				
				// check ContentTypeRef set
				for (ContentTypeRef contentTypeRef : contentType.getContentTypeRef()) {
					// instance ContentTypeRefControl for each ContentTypeRef message
					new ContentTypeRefControl(contentTypeRef, this);
				}
				// check FieldRef set
				for (FieldRef fieldRef : contentType.getFieldRef()) {
					// instance FieldRefControl for each FieldRef message
					new FieldRefControl(fieldRef, this);
				}
			} else if(e.getSource() instanceof FieldRefControl) {
				FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
				// save control
				getFieldRefControl().add(fieldRefControl);						
				addChild(fieldRefControl);
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				// save control
				getContentTypeRefControl().add(contentTypeRefControl);						
				addChild(contentTypeRefControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {
				// cast source
				ContentType contentType = ContentType.class.cast(e.getValue());
				// iterate through child controls and set it's messages.
				if (hasChildren()) {
					while (hasNext()) {
						Control control = next();
						// check if FieldRef has been saved before.
						if (getFieldRefControl().contains(control) ? 
								!contentType.getFieldRef().isEmpty() 
								: false) {
							// get the FieldRef message control.
							FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);

							for (FieldRef fieldRef : contentType.getFieldRef()) {
								// match by name.
								if (fieldRefControl.getName().equals(fieldRef.getName())) {
									// load the control
									control.load();
									break;
								}
							}
						}
						// Check if ContentTypeRef has been stored before.
						if (getContentTypeRefControl().contains(control) ? 
								!contentType.getContentTypeRef().isEmpty()
								: false) {

							for (ContentTypeRef contentTypeRef : contentType.getContentTypeRef()) {
								// Match by UUID.
								if (control.getName().equals(contentTypeRef.getName())) {
									// Load the message
									control.load();
									break;
								}
							}
						}
					}
					reset();
				} else {
					throw new Error("ContentTypeControl.OnControlLoaded: XML message controls missing.");
				}
			} else if (e.getSource() instanceof FieldRefControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.FIELD_REF, e.getValue());
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE_REF, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}