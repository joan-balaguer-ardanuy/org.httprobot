package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class ContentTypeRootControl 
	extends Control<ContentTypeRoot> {

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
		
		setMessage(new ContentTypeRoot());
	}
	public ContentTypeRootControl(ContentTypeRoot message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getMessage());

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
			addChildControl(contentTypeControl);
		} else if (e.getSource() instanceof FieldRefControl) {
			// Cast child XML message control
			FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
			// Store child XML message control
			getFieldRefControl().add(fieldRefControl);
			addChildControl(fieldRefControl);
		} else if (e.getSource() instanceof ContentTypeRefControl) {
			ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			// Store XML message control
			getContentTypeRefControl().add(contentTypeRefControl);
			addChildControl(contentTypeRefControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {

			ContentTypeRoot contentTypeRoot = ContentTypeRoot.class.cast(e.getMessage());
			// Iterate through XML message child controls.
			if (hasChildControls()) {
				while (hasNext()) {
					ControlListener control = next();

					if (control instanceof FieldRefControl ?
							!contentTypeRoot.getFieldRef().isEmpty() ? 
									getFieldRefControl().contains(control) 
									: false : false) {

						FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);

						// Look for current FieldRef message control.
						for (FieldRef fieldRef : contentTypeRoot.getFieldRef()) {
							// By UUID
							if (fieldRefControl.getUuid().equals(fieldRef.getUuid())) {
								// Load XML message control.
								fieldRefControl.loadControl();
								break;
							}
						}
					} else if (control instanceof ContentTypeRefControl ? 
							!contentTypeRoot.getContentTypeRef().isEmpty() ?
									getContentTypeRefControl().contains(control)
									: false : false) {

						ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(control);

						// Look for current ContentTypeRef message control.
						for (ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef()) {
							// By UUID.
							if (contentTypeRefControl.getUuid().equals(contentTypeRef.getUuid())) {
								// Load XML message control.
								contentTypeRefControl.loadControl();
								break;
							}
						}
					} else if (control instanceof ContentTypeControl ? 
							!contentTypeRoot.getContentType().isEmpty() ? 
									getContentTypeControl().contains(control)
									: false : false) {

						ContentTypeControl contentTypeControl = ContentTypeControl.class.cast(control);

						// Look for current ContentType message control.
						for (ContentType contentType : contentTypeRoot.getContentType()) {
							// By UUID
							if (contentTypeControl.getUuid().equals(contentType.getUuid())) {
								// Load XML message control
								contentTypeControl.loadControl();
								break;
							}
						}
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.CONTENT_TYPE_ROOT_CONTROL_LOADED));
			}
		} else if (e.getSource() instanceof ContentTypeControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE, e.getMessage());
			}
		} else if (e.getSource() instanceof FieldRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.FIELD_REF, e.getMessage());
			}
		} else if (e.getSource() instanceof ContentTypeRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_REF, e.getMessage());
			}
		}
	}
}