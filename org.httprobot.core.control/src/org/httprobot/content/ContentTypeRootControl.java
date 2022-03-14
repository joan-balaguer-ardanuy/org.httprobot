package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class ContentTypeRootControl 
	extends AbstractControl<ContentTypeRoot> {

	/**
	 * 5044020805370638430L
	 */
	private static final long serialVersionUID = 5044020805370638430L;
	
	LinkedHashSet<ContentTypeControl> contentTypeControl;
	LinkedHashSet<FieldRefControl> fieldRefControl;
	LinkedHashSet<ContentTypeRefControl> contentTypeRefControl;
	
	@XmlElement
	public LinkedHashSet<ContentTypeControl> getContentTypeControl() {
		return contentTypeControl;
	}
	public void setContentTypeControl(LinkedHashSet<ContentTypeControl> contentTypeControl) {
		this.contentTypeControl = contentTypeControl;
	}
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
	
	public ContentTypeRootControl() {
		super();
	}
	public ContentTypeRootControl(ContentTypeRoot message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if (e.getSource().equals(this)) {
				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getValue());

				fieldRefControl = new LinkedHashSet<FieldRefControl>();
				contentTypeRefControl = new LinkedHashSet<ContentTypeRefControl>();
				contentTypeControl = new LinkedHashSet<ContentTypeControl>();

				for (FieldRef fieldRef : contentTypeRoot.getFieldRef()) {
					new FieldRefControl(fieldRef, this);
				}
				for (ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef()) {
					new ContentTypeRefControl(contentTypeRef, this);
				}
				for (ContentType contentType : contentTypeRoot.getContentType()) {
					new ContentTypeControl(contentType, this);
				}
			} else if (e.getSource() instanceof ContentTypeControl) {
				ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(e.getSource());
				// Store message control
				getContentTypeControl().add(contentTypeControl);
				addChild(contentTypeControl);
			} else if (e.getSource() instanceof FieldRefControl) {
				// Cast child XML message control
				FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
				// Store child XML message control
				getFieldRefControl().add(fieldRefControl);
				addChild(fieldRefControl);
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
				// Store XML message control
				getContentTypeRefControl().add(contentTypeRefControl);
				addChild(contentTypeRefControl);
			}
			break;
		case CONTROL_LOADED:
			if (e.getSource().equals(this)) {

				ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getValue());
				// Iterate through XML message child controls.
				if (hasChildren()) {
					while (hasNext()) {
						Control control = next();

						if (control instanceof FieldRefControl ?
								!contentTypeRoot.getFieldRef().isEmpty() ? 
										getFieldRefControl().contains(control) 
										: false : false) {

							// Look for current FieldRef message control.
							for (FieldRef fieldRef : contentTypeRoot.getFieldRef()) {
								// By UUID
								if (control.getName().equals(fieldRef.getName())) {
									// Load XML message control.
									control.load();
									break;
								}
							}
						} else if (control instanceof ContentTypeRefControl ? 
								!contentTypeRoot.getContentTypeRef().isEmpty() ?
										getContentTypeRefControl().contains(control)
										: false : false) {

							// Look for current ContentTypeRef message control.
							for (ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef()) {
								// By UUID.
								if (control.getName().equals(contentTypeRef.getName())) {
									// Load XML message control.
									control.load();
									break;
								}
							}
						} else if (control instanceof ContentTypeControl ? 
								!contentTypeRoot.getContentType().isEmpty() ? 
										getContentTypeControl().contains(control)
										: false : false) {
							
							// Look for current ContentType message control.
							for (ContentType contentType : contentTypeRoot.getContentType()) {
								// By UUID
								if (control.getName().equals(contentType.getName())) {
									// Load XML message control
									control.load();
									break;
								}
							}
						}
					}
					// Set control ready to be iterated again.
					reset();
				}
			} else if (e.getSource() instanceof ContentTypeControl) {
				if (getChildren().contains(e.getSource())) {
					put(Data.CONTENT_TYPE, e.getValue());
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