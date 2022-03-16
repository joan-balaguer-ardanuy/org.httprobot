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
	
	@SuppressWarnings("unchecked")
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
			if (e.getSource() instanceof ContentTypeControl) {
				// check if control has been initialitzed in this control
				if (getChildren().contains(e.getSource())) {
					// cast event's source value
					ContentType contentType = (ContentType) e.getValue();
					if(get(Data.CONTENT_TYPE) == null) {
						// instance new set
						Set<XML> set = new LinkedHashSet<XML>();
						// add first content type value to set
						set.add(contentType);
						// set data
						put(Data.CONTENT_TYPE, set);
					} else {
						// add message to data
						Object set = get(Data.CONTENT_TYPE);
						((Set<XML>) set).add(contentType);
					}
				}
			} else if (e.getSource() instanceof FieldRefControl) {
				// check if control has been initialitzed in this control
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
				// check if control has been initialitzed in this control
				if (getChildren().contains(e.getSource())) {
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
			}
			break;
		default:
			break;
		}
	}
}