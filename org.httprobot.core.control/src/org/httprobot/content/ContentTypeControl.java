package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

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
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			// Cast message
			ContentType contentType = ContentType.class.cast(e.getMessage());

			fieldRefControl = new LinkedHashSet<FieldRefControl>();
			contentTypeRefControl = new LinkedHashSet<ContentTypeRefControl>();
			
			// Check ContentTypeRef list
			for (ContentTypeRef contentTypeRef : contentType.getContentTypeRef()) {
				// Instantiate ContentTypeRefControl for each ContentTypeRef message
				new ContentTypeRefControl(contentTypeRef, this);
			}
			// Check FieldRef list
			for (FieldRef fieldRef : contentType.getFieldRef()) {
				// Instantiate FieldRefControl for each FieldRef message
				new FieldRefControl(fieldRef, this);
			}
		} else if(e.getSource() instanceof FieldRefControl) {
			FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
			//Store control
			getFieldRefControl().add(fieldRefControl);						
			addChildControl(fieldRefControl);
		} else if(e.getSource() instanceof ContentTypeRefControl) {
			ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(e.getSource());
			//Store control
			getContentTypeRefControl().add(contentTypeRefControl);						
			addChildControl(contentTypeRefControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		
		if (e.getSource().equals(this)) {
			// Cast source
			ContentType contentType = ContentType.class.cast(e.getMessage());

			// Iterate through child controls and set it's messages.
			if (hasChildControls()) {
				while (hasNext()) {
					Control control = next();

					// Check if FieldRef has been stored before.
					if (getFieldRefControl().contains(control) ? 
							!contentType.getFieldRef().isEmpty() 
							: false) {

						// Get the FieldRef message control.
						FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);

						for (FieldRef fieldRef : contentType.getFieldRef()) {
							// Match by UUID.
							if (fieldRefControl.getName().equals(fieldRef.getName())) {
								// Load the message
								fieldRefControl.loadControl();
								break;
							}
						}
					}
					// Check if ContentTypeRef has been stored before.
					if (getContentTypeRefControl().contains(control) ? 
							!contentType.getContentTypeRef().isEmpty()
							: false) {

						// Cast control.
						ContentTypeRefControl contentTypeRefControl = ContentTypeRefControl.class.cast(control);

						for (ContentTypeRef contentTypeRef : contentType.getContentTypeRef()) {
							// Match by UUID.
							if (contentTypeRefControl.getName().equals(contentTypeRef.getName())) {
								// Load the message
								contentTypeRefControl.loadControl();
								break;
							}
						}
					}
				}
				reset();
				SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			} else {
				throw new Error("ContentTypeControl.OnControlLoaded: XML message controls missing.");
			}
		} else if (e.getSource() instanceof FieldRefControl) {
			if (getChildren().contains(e.getSource())) {
				put(Data.FIELD_REF, e.getMessage());
			}
		} else if (e.getSource() instanceof ContentTypeRefControl) {
			if (getChildren().contains(e.getSource())) {
				put(Data.CONTENT_TYPE_REF, e.getMessage());
			}
		}
	}
}