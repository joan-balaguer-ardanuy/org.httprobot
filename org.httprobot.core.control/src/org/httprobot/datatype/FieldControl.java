package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.placeholder.HtmlControl;
import org.httprobot.placeholder.UrlControl;

/**
 * {@link Field} message {@link Control}.
 * Inherits {@link AbstractControl}.
 * @author joan
 *
 */
@XmlRootElement
public final class FieldControl
	extends AbstractControl {

	/**
	 * 7351340591317735119L
	 */
	private static final long serialVersionUID = 7351340591317735119L;

	/**
	 * The URL message control.
	 */
	UrlControl urlControl;
	/**
	 * The HTML message control.
	 */
	HtmlControl htmlControl;
	
	/**
	 * Returns the URL message control.
	 * @return the URL message control.
	 */
	@XmlElement
	public UrlControl getUrlControl() {
		return urlControl;
	}
	/**
	 * Sets the URL message control.
	 * @param httpAddressControl
	 */
	public void setUrlControl(UrlControl httpAddressControl) {
		this.urlControl = httpAddressControl;
	}
	/**
	 * Returns the HTML message control.
	 * @return the HTML message control.
	 */
	@XmlElement
	public HtmlControl getHtmlControl() {
		return htmlControl;
	}
	/**
	 * Sets the HTML message control.
	 * @param htmlUnitControl {@link HtmlControl} the html mr
	 */
	public void setHtmlControl(HtmlControl htmlUnitControl) {
		this.htmlControl = htmlUnitControl;
	}
	@Override
	public Field getMessage() {
		return (Field) super.getMessage();
	}
	
	/**
	 * {@link FieldControl} default class constructor.
	 */
	public FieldControl() {
		super();
	}
	/**
	 * {@link FieldControl} class constructor.
	 * @param message {@link Field} the message
	 * @param parent {@link Control} the parent instance
	 */
	public FieldControl(Field message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				// cast event's source
				Field field = (Field) e.getValue();
				
				if(field.getUrl() != null) {
					// if URL exists instance URL message control
					new UrlControl(field.getUrl(), this);
				}
				else if(field.getHtml() != null) {
					// if HTML exists instance HTML message control
					new HtmlControl(field.getHtml(), this);
				}
				else {
					// only one message property must be non-null
					throw new Error("FieldControl.OnEventReceived: Inconsistent Field XML message.");
				}
			}
			else if(e.getSource() instanceof HtmlControl) {
				// set property
				htmlControl = (HtmlControl) e.getSource();
				// store control
				addChild(htmlControl);
			}
			else if(e.getSource() instanceof UrlControl) {
				// set property
				urlControl = (UrlControl) e.getSource();
				// store control
				addChild(urlControl);
			}
			break;
		case CONTROL_LOADED:
			// check by instance type
			if(e.getSource() instanceof HtmlControl) {
				// check source is a child of current instance
				if(getChildren().contains(e.getSource())) {
					// set data
					put(Data.HTML, e.getValue());
				}	
			}
			else if(e.getSource() instanceof UrlControl) {
				// check source is a child of current instance
				if(getChildren().contains(e.getSource())) {
					// set data
					put(Data.URL, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}