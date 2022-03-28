package org.httprobot.content;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.Message;
import org.httprobot.XML;
import org.httprobot.event.EventArgs;

/**
 * {@link ContentTypeRoot} {@link Message} {@link Control} class.
 * Inherits {@link AbstractControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class ContentTypeRootControl 
	extends AbstractControl {

	/**
	 * 5044020805370638430L
	 */
	private static final long serialVersionUID = 5044020805370638430L;
	
	/**
	 * The linked hash set of content type controls.
	 */
	LinkedHashSet<ContentTypeControl> contentTypeControl;
	/**
	 * The linked hash set of field reference controls.
	 */
	LinkedHashSet<FieldRefControl> fieldRefControl;
	/**
	 * The linked hash set of content type reference controls.
	 */
	LinkedHashSet<ContentTypeRefControl> contentTypeRefControl;
	
	/**
	 * Returns the {@link LinkedHashSet} of {@link ContentTypeControl}.
	 * @return the {@link LinkedHashSet} of {@link ContentTypeControl}.
	 */
	@XmlElement
	public LinkedHashSet<ContentTypeControl> getContentTypeControl() {
		return contentTypeControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link ContentTypeControl}.
	 * @param contentTypeControl {@link LinkedHashSet} of {@link ContentTypeControl}.
	 */
	public void setContentTypeControl(LinkedHashSet<ContentTypeControl> contentTypeControl) {
		this.contentTypeControl = contentTypeControl;
	}
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
	 * @param fieldRefControlt {@link LinkedHashSet} of {@link FieldRefControl}.
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
	 * Sets the {@link LinkedHashSet} of {@link ContentTypeRefControl}.
	 * @param contentTypeRefControl {@link LinkedHashSet} of {@link ContentTypeRefControl}.
	 */
	public void setContentTypeRefControl(LinkedHashSet<ContentTypeRefControl> contentTypeRefControl) {
		this.contentTypeRefControl = contentTypeRefControl;
	}
	@Override
	@XmlElement
	public ContentTypeRoot getMessage() {
		return (ContentTypeRoot) super.getMessage();
	}
	
	/**
	 * The {@link ContentTypeRootControl} default class constructor.
	 */
	public ContentTypeRootControl() {
		super();
	}
	/**
	 * The {@link ContentTypeRootControl} class constructor.
	 * @param message {@link ContentTypeRoot} the message
	 * @param parent {@link Control} the parent instance
	 */
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
				// cast content type root XML message
				ContentTypeRoot contentTypeRoot = (ContentTypeRoot) e.getValue();
				// instance contents sets
				fieldRefControl = new LinkedHashSet<FieldRefControl>();
				contentTypeRefControl = new LinkedHashSet<ContentTypeRefControl>();
				contentTypeControl = new LinkedHashSet<ContentTypeControl>();
				// instance new field reference control for each field reference message
				for (FieldRef fieldRef : contentTypeRoot.getFieldRef()) {
					new FieldRefControl(fieldRef, this);
				}
				// instance new content type reference control for each content type reference message
				for (ContentTypeRef contentTypeRef : contentTypeRoot.getContentTypeRef()) {
					new ContentTypeRefControl(contentTypeRef, this);
				}
				// instance new content type control for each content type message
				for (ContentType contentType : contentTypeRoot.getContentType()) {
					new ContentTypeControl(contentType, this);
				}
			} else if (e.getSource() instanceof ContentTypeControl) {
				ContentTypeControl contentTypeControl = (ContentTypeControl) e.getSource();
				// store message control
				getContentTypeControl().add(contentTypeControl);
				addChild(contentTypeControl);
			} else if (e.getSource() instanceof FieldRefControl) {
				// cast child XML message control
				FieldRefControl fieldRefControl = (FieldRefControl) e.getSource();
				// store child XML message control
				getFieldRefControl().add(fieldRefControl);
				addChild(fieldRefControl);
			} else if (e.getSource() instanceof ContentTypeRefControl) {
				ContentTypeRefControl contentTypeRefControl = (ContentTypeRefControl) e.getSource();
				// store XML message control
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