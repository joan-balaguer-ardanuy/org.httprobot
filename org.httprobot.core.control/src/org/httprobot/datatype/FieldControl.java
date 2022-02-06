package org.httprobot.datatype;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.HtmlUnitControl;
import org.httprobot.placeholder.HttpAddressControl;

@XmlRootElement
public final class FieldControl
	extends Control<Field> {

	/**
	 * 7351340591317735119L
	 */
	private static final long serialVersionUID = 7351340591317735119L;

	HttpAddressControl httpAddressControl;
	HtmlUnitControl htmlUnitControl;
	
	@XmlElement
	public HttpAddressControl getHttpAddressControl() {
		return httpAddressControl;
	}
	public void setHttpAddressControl(HttpAddressControl httpAddressControl) {
		this.httpAddressControl = httpAddressControl;
	}
	@XmlElement
	public HtmlUnitControl getHtmlUnitControl() {
		return htmlUnitControl;
	}
	public void setHtmlUnitControl(HtmlUnitControl htmlUnitControl) {
		this.htmlUnitControl = htmlUnitControl;
	}
	
	public FieldControl() {
		super();
		setMessage(new Field());
	}
	public FieldControl(Field message, ControlListener parent) {
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
				new HtmlUnitControl(field.getHtmlUnit(), this);
			}
			else {
				throw new Error("FieldControl.OnControlInitialized: Inconsistent Field XML message.");
			}
		}
		else if(e.getSource() instanceof HtmlUnitControl) {
			htmlUnitControl = HtmlUnitControl.class.cast(e.getSource());
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
					ControlListener control = next();
					
					if(control instanceof HttpAddressControl ?
							field.getHttpAddress() != null ?
									httpAddressControl.equals(control)
									: false : false) {
						httpAddressControl.loadControl();
					}
					else if(control instanceof HtmlUnitControl ?
							field.getHtmlUnit() != null ?
									htmlUnitControl.equals(control)
									: false : false) {
						htmlUnitControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.FIELD_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof HtmlUnitControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.HTML_UNIT, e.getMessage());
			}	
		}
		else if(e.getSource() instanceof HttpAddressControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.HTTP_ADDRESS, e.getMessage());
			}	
		}
	}
}