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
	
	/**
	 * field control property set
	 */
	LinkedHashSet<FieldControl> fieldControl;
	
	/**
	 * Returns the {@link LinkedHashSet} of {@link FieldControl}.
	 * @return the {@link LinkedHashSet} of {@link FieldControl}.
	 */
	@XmlElement
	public LinkedHashSet<FieldControl> getFieldControl() {
		return fieldControl;
	}
	/**
	 * Sets the {@link LinkedHashSet} of {@link FieldControl}.
	 * @param fieldControl {@link LinkedHashSet} of {@link FieldControl} the field message control.
	 */
	public void setFieldControl(LinkedHashSet<FieldControl> fieldControl) {
		this.fieldControl = fieldControl;
	}
	@Override
	public FieldRoot getMessage() {
		return (FieldRoot) super.getMessage();
	}
	
	/**
	 * {@link FieldRootControl} default class constructor.
	 */
	public FieldRootControl() {
		super();
	}
	/**
	 * {@link FieldRootControl} class constructor.
	 * @param message {@link FieldRoot} the message
	 * @param parent {@link Control} the parent instance
	 */
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
				// cast event's value
				FieldRoot fieldRoot = (FieldRoot) e.getValue();
				// instance field message control set
				fieldControl = new LinkedHashSet<FieldControl>();
				// check message
				if(!fieldRoot.getField().isEmpty()) {
					for(Field field : fieldRoot.getField()) {
						// for each field message instance new field control message.
						new FieldControl(field, this);
					}
				}
			}
			else if(e.getSource() instanceof FieldControl) {
				// cast event's source
				FieldControl childFieldControl = (FieldControl) e.getSource();
				// set property
				fieldControl.add(childFieldControl);
				// store control
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
