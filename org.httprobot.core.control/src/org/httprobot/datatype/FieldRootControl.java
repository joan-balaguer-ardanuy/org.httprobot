package org.httprobot.datatype;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class FieldRootControl
	extends AbstractControl<FieldRoot> {

	/**
	 * 2468967859806729944L
	 */
	private static final long serialVersionUID = 2468967859806729944L;
	
	LinkedHashSet<FieldControl> fieldControl;
	
	@XmlElement
	public LinkedHashSet<FieldControl> getFieldControl() {
		return fieldControl;
	}
	public void setFieldControl(LinkedHashSet<FieldControl> fieldControl) {
		this.fieldControl = fieldControl;
	}
	
	public FieldRootControl() {
		super();
	}
	public FieldRootControl(FieldRoot message, Control parent) {
		super(message, parent);
	}
	@Override
	public void OnEventReceived(EventArgs e) {
		super.OnEventReceived(e);
		switch (e.getEventType()) {
		case CONTROL_INITIALIZED:
			if(e.getSource().equals(this)) {
				FieldRoot fieldRoot = FieldRoot.class.cast(e.getValue());
				fieldControl = new LinkedHashSet<FieldControl>();
				
				if(!fieldRoot.getField().isEmpty()) {
					for(Field field : fieldRoot.getField()) {
						new FieldControl(field, this);
					}
				}
			}
			else if(e.getSource() instanceof FieldControl) {
				FieldControl childFieldControl = FieldControl.class.cast(e.getSource());
				fieldControl.add(childFieldControl);
				addChild(childFieldControl);
			}
			break;
		case CONTROL_LOADED:
			if(e.getSource().equals(this)) {
				FieldRoot fieldRoot = FieldRoot.class.cast(e.getValue());
				
				//Check if control has child XML message controls
				if(hasChildren())
				{
					//Iterate through child XML message controls
					while(hasNext())
					{
						//Get next child XML message control
						Control control = next();
						
						if(control instanceof FieldControl ?
								!fieldRoot.getField().isEmpty() ?
										fieldControl.contains(control)
										: false : false) {
							for(Field field : fieldRoot.getField()) {
								if(field.getName().equals(control.getName())) {
									control.load();
									break;
								}
							}
						}
					}
					// Set control ready to be iterated again.
					reset();
				}
			}
			else if(e.getSource() instanceof FieldControl) {
				if(getChildren().contains(e.getSource())) {
					put(Data.FIELD, e.getValue());
				}
			}
			break;
		default:
			break;
		}
	}
}
