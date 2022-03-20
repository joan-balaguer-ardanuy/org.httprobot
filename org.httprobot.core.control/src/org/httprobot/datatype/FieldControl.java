package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;
import org.httprobot.operator.HtmlControl;
import org.httprobot.operator.UrlControl;

@XmlRootElement
public final class FieldControl
	extends AbstractControl {

	/**
	 * 7351340591317735119L
	 */
	private static final long serialVersionUID = 7351340591317735119L;

	UrlControl urlControl;
	HtmlControl htmlControl;
	
	@XmlElement
	public UrlControl getUrlControl() {
		return urlControl;
	}
	public void setUrlControl(UrlControl httpAddressControl) {
		this.urlControl = httpAddressControl;
	}
	@XmlElement
	public HtmlControl getHtmlControl() {
		return htmlControl;
	}
	public void setHtmlControl(HtmlControl htmlUnitControl) {
		this.htmlControl = htmlUnitControl;
	}
	@Override
	public Field getMessage() {
		return (Field) super.getMessage();
	}
	
	public FieldControl() {
		super();
	}
	public FieldControl(Field message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				
				Field field = Field.class.cast(e.getValue());
				
				if(field.getUrl() != null) {
					new UrlControl(field.getUrl(), this);
				}
				else if(field.getHtml() != null) {
					new HtmlControl(field.getHtml(), this);
				}
				else {
					throw new Error("FieldControl.OnEventReceived: Inconsistent Field XML message.");
				}
			}
			else if(e.getSource() instanceof HtmlControl) {
				htmlControl = HtmlControl.class.cast(e.getSource());
				addChild(htmlControl);
			}
			else if(e.getSource() instanceof UrlControl) {
				urlControl = UrlControl.class.cast(e.getSource());
				addChild(urlControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource() instanceof HtmlControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.HTML, e.getValue());
				}	
			}
			else if(e.getSource() instanceof UrlControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.URL, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}