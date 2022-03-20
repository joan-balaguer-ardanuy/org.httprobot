package org.httprobot.datatype;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractControl;
import org.httprobot.Control;
import org.httprobot.Data;
import org.httprobot.XML;
import org.httprobot.event.EventArgs;

@XmlRootElement
public final class FieldRootControl
	extends AbstractControl {

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
	@Override
	public FieldRoot getMessage() {
		return (FieldRoot) super.getMessage();
	}
	
	public FieldRootControl() {
		super();
	}
	public FieldRootControl(FieldRoot message, Control parent) {
		super(message, parent);
	}
	
	@SuppressWarnings("unchecked")
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
			if(e.getSource() instanceof FieldControl) {
				if(getChildren().contains(e.getSource())) {
					Field field = (Field) e.getValue();
					// check if data source's data exists
					if(get(Data.FIELD) == null) {
						// instance new set
						Set<XML> set = new LinkedHashSet<XML>();
						// add first data source value to set
						set.add(field);
						// set data
						put(Data.FIELD, set);
					} else {
						// add message to data
						Object set = get(Data.FIELD);
						((Set<XML>) set).add(field);
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
