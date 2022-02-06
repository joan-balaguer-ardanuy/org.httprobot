package org.httprobot.content;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class DataViewControl 
	extends Control<DataView> {

	/**
	 * -362395516569615434L
	 */
	private static final long serialVersionUID = -362395516569615434L;
	LinkedHashSet<FieldRefControl> fieldRefControl;
	
	@XmlElement
	public LinkedHashSet<FieldRefControl> getFieldRefControl() {
		return fieldRefControl;
	}
	public void setFieldRefControl(LinkedHashSet<FieldRefControl> fieldRefControl) {
		this.fieldRefControl = fieldRefControl;
	}
	public DataViewControl() {
		super();
		setMessage(new DataView());
	}
	public DataViewControl(DataView message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			DataView dataView = DataView.class.cast(e.getMessage());

			fieldRefControl = new LinkedHashSet<FieldRefControl>();
			
			if (dataView.getFieldRef() != null) {
				for (FieldRef fieldRef : dataView.getFieldRef()) {
					// Instantiate FieldRefControl for each FieldRef message
					new FieldRefControl(fieldRef, this);
				}
			} else {
				throw new Error("DataViewControl.OnControlInitialized: FieldRef XML message set empty");
			}
		} else if(e.getSource() instanceof FieldRefControl) {
			//Cast source
			FieldRefControl fieldRefControl = FieldRefControl.class.cast(e.getSource());
			//Store control
			getFieldRefControl().add(fieldRefControl);
			addChildControl(fieldRefControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			DataView dataView = DataView.class.cast(e.getMessage());

			if (hasChildControls()) {
				while (hasNext()) {
					ControlListener control = next();

					if (getFieldRefControl().contains(control) ? 
							dataView.getFieldRef() != null 
							: false) {
						// Get the FieldRef message control.
						FieldRefControl fieldRefControl = FieldRefControl.class.cast(control);

						for (FieldRef fieldRef : dataView.getFieldRef()) {
							// Match by UUID.
							if (fieldRefControl.getUuid().equals(fieldRef.getUuid())) {
								fieldRefControl.loadControl();
								break;
							}
						}
					}
				}
				// Set XML message control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.DATA_VIEW_CONTROL_LOADED));
			} else {
				throw new Error("DataViewControl.OnControlLoaded: child XML message controls expected");
			}
		} else if (e.getSource() instanceof FieldRefControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.FIELD_REF, e.getMessage());
			}
		}
	}
}