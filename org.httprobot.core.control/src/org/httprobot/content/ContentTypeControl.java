package org.httprobot.content;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
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
	
	@SuppressWarnings("unchecked")
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
			if (e.getSource() instanceof FieldRefControl) {
				if (getChildren().contains(e.getSource())) {
					// cast event's source value
					FieldRef fieldRef = (FieldRef) e.getValue();
					if(get(Data.FIELD_REF) == null) {
						// instance new set
						Set<XML> set = new LinkedHashSet<XML>();
						// add first field reference value to set
						set.add(fieldRef);
						// set data
						put(Data.FIELD_REF, set);
					} else {
						// add message to data
						Object set = get(Data.FIELD_REF);
						((Set<XML>) set).add(fieldRef);
					}
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				// cast event's source value
				ContentTypeRef contentTypeRef = (ContentTypeRef) e.getValue();
				if(get(Data.CONTENT_TYPE_REF) == null) {
					// instance new set
					Set<XML> set = new LinkedHashSet<XML>();
					// add first content type reference value to set
					set.add(contentTypeRef);
					// set data
					put(Data.CONTENT_TYPE_REF, set);
				} else {
					// add message to data
					Object set = get(Data.CONTENT_TYPE_REF);
					((Set<XML>) set).add(contentTypeRef);
				}
			}
			break;
		default:
			break;
		}
	}
}