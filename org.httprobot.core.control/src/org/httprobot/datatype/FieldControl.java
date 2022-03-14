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
	extends AbstractControl<Field> {

	/**
	 * 7351340591317735119L
	 */
	private static final long serialVersionUID = 7351340591317735119L;

	UrlControl httpAddressControl;
	HtmlControl htmlUnitControl;
	
	@XmlElement
	public UrlControl getHttpAddressControl() {
		return httpAddressControl;
	}
	public void setHttpAddressControl(UrlControl httpAddressControl) {
		this.httpAddressControl = httpAddressControl;
	}
	@XmlElement
	public HtmlControl getHtmlUnitControl() {
		return htmlUnitControl;
	}
	public void setHtmlUnitControl(HtmlControl htmlUnitControl) {
		this.htmlUnitControl = htmlUnitControl;
	}
	
	public FieldControl() {
		super();
		setMessage(new Field());
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
				
				if(field.getHttpAddress() != null) {
					new UrlControl(field.getHttpAddress(), this);
				}
				else if(field.getHtmlUnit() != null) {
					new HtmlControl(field.getHtmlUnit(), this);
				}
				else {
					throw new Error("FieldControl.OnControlInitialized: Inconsistent Field XML message.");
				}
			}
			else if(e.getSource() instanceof HtmlControl) {
				htmlUnitControl = HtmlControl.class.cast(e.getSource());
				addChild(htmlUnitControl);
			}
			else if(e.getSource() instanceof UrlControl) {
				httpAddressControl = UrlControl.class.cast(e.getSource());
				addChild(httpAddressControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				Field field = Field.class.cast(e.getValue());
				//Check if control has child XML message controls
				if(hasChildren())
				{
					//Iterate through child XML message controls
					while(hasNext())
					{
						//Get next child XML message control
						Control control = next();
						
						if(control instanceof UrlControl ?
								field.getHttpAddress() != null ?
										httpAddressControl.equals(control)
										: false : false) {
							control.load();
						}
						else if(control instanceof HtmlControl ?
								field.getHtmlUnit() != null ?
										htmlUnitControl.equals(control)
										: false : false) {
							control.load();
						}
					}
					// Set control ready to be iterated again.
					reset();
				}
			}
			else if(e.getSource() instanceof HtmlControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.HTML_UNIT, e.getValue());
				}	
			}
			else if(e.getSource() instanceof UrlControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.HTTP_ADDRESS, e.getValue());
				}	
			}
			break;
		default:
			break;
		}
	}
}