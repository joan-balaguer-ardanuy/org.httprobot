package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.HtmlControl;
import org.httprobot.placeholder.HttpAddressControl;

@XmlRootElement
public final class FieldControl
	extends AbstractControl<Field> {

	/**
	 * 7351340591317735119L
	 */
	private static final long serialVersionUID = 7351340591317735119L;

	HttpAddressControl httpAddressControl;
	HtmlControl htmlUnitControl;
	
	@XmlElement
	public HttpAddressControl getHttpAddressControl() {
		return httpAddressControl;
	}
	public void setHttpAddressControl(HttpAddressControl httpAddressControl) {
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
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			
			Field field = Field.class.cast(e.getMessage());
			
			if(field.getHttpAddress() != null) {
				new HttpAddressControl(field.getHttpAddress(), this);
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
			addChildControl(htmlUnitControl);
		}
		else if(e.getSource() instanceof HttpAddressControl) {
			httpAddressControl = HttpAddressControl.class.cast(e.getSource());
			addChildControl(httpAddressControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Field field = Field.class.cast(e.getMessage());
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					Control control = next();
					
					if(control instanceof HttpAddressControl ?
							field.getHttpAddress() != null ?
									httpAddressControl.equals(control)
									: false : false) {
						httpAddressControl.loadControl();
					}
					else if(control instanceof HtmlControl ?
							field.getHtmlUnit() != null ?
									htmlUnitControl.equals(control)
									: false : false) {
						htmlUnitControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				SendEvent(new CommandEventArgs(this, Command.CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof HtmlControl) {
			if(getChildren().contains(e.getSource())) {
				put(Data.HTML_UNIT, e.getMessage());
			}	
		}
		else if(e.getSource() instanceof HttpAddressControl) {
			if(getChildren().contains(e.getSource())) {
				put(Data.HTTP_ADDRESS, e.getMessage());
			}	
		}
	}
}