package org.httprobot.content;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.Message;
import org.httprobot.event.EventArgs;

/**
 * {@link ContentType} {@link XML} {@link Control} class. 
 * Inherits {@link AbstractControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class ContentTypeControl 
	extends AbstractControl {

	/**
	 * -3238737080863615932L
	 */
	private static final long serialVersionUID = -3238737080863615932L;
	
	/**
	 * The field reference control.
	 */
	LinkedHashSet<FieldRefControl> fieldRefControl;
	/**
	 * The content type reference control
	 */
	LinkedHashSet<ContentTypeRefControl> contentTypeRefControl;
	
	/**
	 * Returns the {@link LinkedHashSet} of {@link FieldRefControl}.
	 * @return the {@link LinkedHashSet} of {@link FieldRefControl}.
	 */
	@XmlElement
	public LinkedHashSet<FieldRefControl> getFieldRefControl() {
		return fieldRefControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link FieldRefControl}.
	 * @param fieldRefControl {@link LinkedHashSet} of {@link FieldRefControl} the field reference to be set.
	 */
	public void setFieldRefControl(LinkedHashSet<FieldRefControl> fieldRefControl) {
		this.fieldRefControl = fieldRefControl;
	}
	/**
	 * Returns the {@link LinkedHashSet} of {@link ContentTypeRefControl}.
	 * @return the {@link LinkedHashSet} of {@link ContentTypeRefControl}.
	 */
	@XmlElement
	public LinkedHashSet<ContentTypeRefControl> getContentTypeRefControl() {
		return contentTypeRefControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link ContentTypeControl}.
	 * @param contentTypeRefControl {@link LinkedHashSet} of {@link ContentTypeRefControl} the content type reference control to be set.
	 */
	public void setContentTypeRefControl(LinkedHashSet<ContentTypeRefControl> contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	@Override
	@XmlElement
	public ContentType getMessage() {
		return (ContentType) super.getMessage();
	}
	
	/**
	 * {@link ContentTypeControl} default class constructor.
	 */
	public ContentTypeControl() {
		super();
	}
	/**
	 * {@link ContentTypeControl} class constructor.
	 * @param message {@link ContentType} the message
	 * @param parent {@link Control} the parent instance
	 */
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
				ContentType contentType = (ContentType) e.getValue();
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
				FieldRefControl fieldRefControl = (FieldRefControl) e.getSource();
				// store control
				getFieldRefControl().add(fieldRefControl);						
				addChild(fieldRefControl);
			} else if(e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = (ContentTypeRefControl) e.getSource();
				// strore control
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
						Set<Message> set = new LinkedHashSet<Message>();
						// add first field reference value to set
						set.add(fieldRef);
						// set data
						put(Data.FIELD_REF, set);
					} else {
						// add message to data
						Object set = get(Data.FIELD_REF);
						((Set<Message>) set).add(fieldRef);
					}
				}
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				// cast event's source value
				ContentTypeRef contentTypeRef = (ContentTypeRef) e.getValue();
				if(get(Data.CONTENT_TYPE_REF) == null) {
					// instance new set
					Set<Message> set = new LinkedHashSet<Message>();
					// add first content type reference value to set
					set.add(contentTypeRef);
					// set data
					put(Data.CONTENT_TYPE_REF, set);
				} else {
					// add message to data
					Object set = get(Data.CONTENT_TYPE_REF);
					((Set<Message>) set).add(contentTypeRef);
				}
			}
			break;
		default:
			break;
		}
	}
}