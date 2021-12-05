package org.httprobot.unit;

import java.util.LinkedHashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Control;
import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.parameter.Constant;
import org.httprobot.parameter.ConstantControl;

@XmlRootElement
public final class ActionControl
	extends Control<Action> {

	/**
	 * -3447883786428247992L
	 */
	private static final long serialVersionUID = -3447883786428247992L;
	
	LinkedHashSet<ConstantControl> constant;
	WebLoaderControl webLoaderControl;
	ElementControl elementControl;
	
	@XmlElement
	public WebLoaderControl getWebLoaderControl() {
		return webLoaderControl;
	}
	public void setWebLoaderControl(WebLoaderControl paginatorControl) {
		this.webLoaderControl = paginatorControl;
	}
	@XmlElement
	public LinkedHashSet<ConstantControl> getConstant() {
		return constant;
	}
	public void setConstant(LinkedHashSet<ConstantControl> constant) {
		this.constant = constant;
	}
	@XmlElement
	public ElementControl getElementControl() {
		return elementControl;
	}
	public void setElementControl(ElementControl elementControl) {
		this.elementControl = elementControl;
	}
	
	public ActionControl() {
		super();
	}
	public ActionControl(Action message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if (e.getSource().equals(this)) {
			Action action = Action.class.cast(e.getMessage());

			constant = new LinkedHashSet<ConstantControl>();

			if (action.getClearQuery() == null) {
				throw new Error("ActionControl.OnControlInitialized: strictMode XML message attribute expected.");
			}
			if (action.getHttpAddress() == null) {
				throw new Error("ActionControl.OnControlInitialized: httpAddress XML message element expected.");
			}
			if(action.getElement() != null) {
				new ElementControl(action.getElement(), this);
			}
			for (Constant constant : action.getConstant()) {
				new ConstantControl(constant, this);
			}
			
		} else if (e.getSource() instanceof ConstantControl) {
			ConstantControl childConstant = ConstantControl.class.cast(e.getSource());
			constant.add(childConstant);
			addChildControl(childConstant);
		} else if(e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			Action action = Action.class.cast(e.getMessage());
			
			//Set non-controled data
			put(Data.CLEAR_QUERY, action.getClearQuery());
			put(Data.HTTP_ADDRESS, action.getHttpAddress());
			put(Data.METHOD, action.getMethod());
			
			//Check if control has child XML message controls
			if(hasChildControls())
			{
				//Iterate through child XML message controls
				while(hasNext())
				{
					//Get next child XML message control
					ControlListener control = next();
					
					if(control instanceof ElementControl ?
							elementControl.equals(control) : false) { 
						elementControl.loadControl();
					} else if(control instanceof ConstantControl ?
							!action.getConstant().isEmpty() ?
									constant.contains(control)
									: false : false) {
						ConstantControl childConstantControl = ConstantControl.class.cast(control);
						for(Constant constant : action.getConstant()) {
							if(childConstantControl.getUuid().equals(constant.getUuid())) {
								childConstantControl.loadControl();
							}
						}		
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.ACTION_CONTROL_LOADED));
			}
		}
		else if (e.getSource() instanceof ElementControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.ELEMENT, e.getMessage());
			}
		} else if (e.getSource() instanceof ConstantControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.CONSTANT, e.getMessage());
			}
		}
	}
}