package org.httprobot.datatype;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class FieldRootControl
	extends Control<FieldRoot> {

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
		fieldControl = new LinkedHashSet<FieldControl>();
	}
	public FieldRootControl(FieldRoot message, ControlListener parent) {
		super(message, parent);

	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			FieldRoot fieldRoot = FieldRoot.class.cast(e.getMessage());
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
			addChildControl(childFieldControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			FieldRoot fieldRoot = FieldRoot.class.cast(e.getMessage());
			
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					ControlListener control = next();
					
					if(control instanceof FieldControl ?
							!fieldRoot.getField().isEmpty() ?
									fieldControl.contains(control)
									: false : false) {
						
						FieldControl childFieldControl = FieldControl.class.cast(control);
						
						for(Field field : fieldRoot.getField()) {
							if(field.getUuid().equals(childFieldControl.getUuid())) {
								childFieldControl.loadControl();
								break;
							}
						}
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.FIELD_ROOT_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof FieldControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.FIELD, e.getMessage());
			}
		}
	}
}
