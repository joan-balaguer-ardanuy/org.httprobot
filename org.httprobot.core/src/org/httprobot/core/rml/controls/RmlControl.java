package org.httprobot.core.rml.controls;

import java.util.EnumSet;
import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.RuntimeOptions;
import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.MarshallerEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IListener;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.core.interfaces.IControl;


/**
 * RML control class. Is {@link IRmlListener}.
 * @author Joan  
 */
/**
 * @author Joan
 *
 */
@XmlTransient
public abstract class RmlControl extends Rml implements IRmlListener, IControl
{
	/**
	 * 7259374887765950550L
	 */
	private static final long serialVersionUID = 7259374887765950550L;
	private Rml message;
	private IRmlListener parent;
	private Boolean isRendered = false;
	/**
	 * RmlControlRead Listeners
	 */
	private Vector<IRmlListener> read_event_listeners = null;
	/**
	 * RmlControlInit Listeners
	 */
	private Vector<IRmlListener> init_event_listeners = null;
	/**
	 * RmlControlLoad Listeners
	 */
	private Vector<IRmlListener> load_event_listeners = null;
	/**
	 * RmlControlChanged Listeners
	 */
	private Vector<IRmlListener> change_event_listeners = null;
	/**
	 * RmlControlRendered Listeners
	 */
	private Vector<IRmlListener> render_event_listeners = null;
	/**
	 * RmlControlInit Listeners
	 */
	private Vector<IRmlListener> write_event_listeners = null;	
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#getDestinationPath()
	 */
	@Override
	public final String getDestinationPath() 
	{
		if(super.getDestinationPath() != null)
		{
			return super.getDestinationPath();
		}
		else
		{
			return parent.getDestinationPath();
		}
	}	
	/**
	 * RML control default class constructor.
	 */
	public RmlControl()
	{
		super();
		InitControl(null);
	}	
	/**
	 * RML control class constructor.
	 * @param parent {@link IRmlListener} the parent
	 */
	public RmlControl(IRmlListener parent, Rml message)
	{
		super();
		this.parent = parent;
		InitControl(message);
	}
	/**
	 * Adds listener to this control.
	 * @param listener {@link IRmlListener} the listener
	 * @param event_type {@link RmlType} the event type to listen
	 */
	public final synchronized void addControlListener(IRmlListener listener, RmlEventType event_type)
	{
		switch (event_type) 
		{
			case READ:
				this.read_event_listeners.add(listener);
				break;
			case INIT:
				this.init_event_listeners.add(listener);
				break;
			case LOAD:	
				this.load_event_listeners.add(listener);
				break;
			case CHANGE:
				this.change_event_listeners.add(listener);
				break;
			case RENDER:	
				this.render_event_listeners.add(listener);
				break;
			case WRITE:
				this.write_event_listeners.add(listener);
				break;
			default:
				break;
		}
	}
	/**
	 * @param listener the listener to remove from listeners array
	 * @param event_type the event type
	 */
	public final synchronized void removeControlListener(IRmlListener listener, RmlEventType event_type)
	{
		switch (event_type) 
		{
			case INIT:
				this.init_event_listeners.remove(listener);
				break;
			case READ:
				this.read_event_listeners.remove(listener);
				break;			
			case LOAD:	
				this.load_event_listeners.remove(listener);
				break;
			case CHANGE:
				this.change_event_listeners.remove(listener);
				break;
			case RENDER:	
				this.render_event_listeners.remove(listener);
				break;
			case WRITE:
				this.write_event_listeners.remove(listener);
				break;
			default:
				break;
		}
	}
	/**
	 * Fires  {@link IRmlListener} ControlChanged event. event method to listeners.
	 * @param e {@link RmlEventArgs} the arguments
	 * @throws InconsistenMessageException 
	 */
	protected final void ControlChangedEvent(RmlEventArgs e) 
	{
		for (IRmlListener listener : change_event_listeners) 
		{
			try
			{
				listener.OnControlChanged(this, e);
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires ControlInit event method to listeners.
	 * @param e {@link RmlEventArgs}  the arguments
	 */
	protected final void ControlInitEvent(RmlEventArgs e) 
	{
		for (IRmlListener listener : init_event_listeners) 
		{
			try 
			{
				listener.OnControlInit(this, e);
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires {@link IRmlListener} ControlLoaded event.
	 * @param e {@link RmlEventArgs}  the arguments
	 */
	protected final void ControlLoadedEvent(RmlEventArgs e) 
	{
		for (IRmlListener listener : load_event_listeners) 
		{
			try {
				listener.OnControlLoaded(this, e);
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			}
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Fires ControlInit event method to listeners.
	 * @param e {@link RmlEventArgs}  the arguments
	 */
	protected final void ControlRenderEvent(RmlEventArgs e)
	{
		for (IRmlListener listener : render_event_listeners) 
		{
			try {
				listener.OnControlRendered(this, e);
			} catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Control read event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlReadEvent(RmlEventArgs e) 
	{
		for (IRmlListener listener : read_event_listeners) 
		{
			try 
			{
				listener.OnControlRead(this, e);
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Control write event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlWriteEvent(RmlEventArgs e) 
	{
		for (IRmlListener listener : write_event_listeners) 
		{
			try 
			{
				listener.OnControlWrite(this, e);
			} 
			catch (NotImplementedException e1) 
			{
				e1.printStackTrace();
			} 
			catch (InconsistenMessageException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	/**
	 * Changes the control. Fires ControlChangedEvent.
	 * @param new_value
	 */
	private final void ChangeControl(Rml new_value) 
	{		
		RmlEventArgs e = new RmlEventArgs(this, RmlEventType.CHANGE, new_value);
		ControlChangedEvent(e);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.IControl#getIsRendered()
	 */
	@Override
	public final Boolean getIsRendered() 
	{
		return this.isRendered;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.IControl#getMessage()
	 */
	@Override
	public final Rml getMessage() 
	{
		return message;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.IControl#getParent()
	 */
	@Override
	public final IRmlListener getParent() {
		return parent;
	}
	/**
	 * Initializes RML control. Fires ControlInitEvent.
	 */
	private final void InitControl(Rml message) 
	{
		//Initialize using data
		this.read_event_listeners = new Vector<IRmlListener>();
		this.write_event_listeners = new Vector<IRmlListener>();
		this.init_event_listeners = new Vector<IRmlListener>();
		this.load_event_listeners = new Vector<IRmlListener>();
		this.change_event_listeners = new Vector<IRmlListener>();
		this.render_event_listeners = new Vector<IRmlListener>();
		//Adds event listeners
		addControlListener(this, RmlEventType.INIT);
		addControlListener(this, RmlEventType.READ);
		addControlListener(this, RmlEventType.LOAD);
		addControlListener(this, RmlEventType.CHANGE);
		addControlListener(this, RmlEventType.RENDER);
		addControlListener(this, RmlEventType.WRITE);
		//Adds parent's inputs command listener from this.
		AddParentRmlListeners();
		//Initialize work flow
		RmlEventArgs e = new RmlEventArgs(this, RmlEventType.INIT, message);
		ControlInitEvent(e);
	}
	/**
	 * Adds event listeners to parent if not null.
	 */
	private void AddParentRmlListeners() 
	{
		if(parent != null)
		{
			addCommandInputListener(this.parent);
		}
	}
	public void AddComamandInputListener(IListener listener)
	{
		addCommandInputListener(listener);
	}
	
	/**
	 * Loads RML control. Fires ControlLoadedEvent.
	 */
	private final void LoadControl() 
	{
		ControlLoadedEvent(new RmlEventArgs(this, RmlEventType.LOAD, this.message));
	}

	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandInput(Object sender, CliEventArgs e)
	{
		super.OnCommandInput(sender, e);
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlCommand(java.lang.Object, org.httprobot.common.events.CommandEventArgs)
	 */
	@Override
	public void OnCommandOutput(Object sender, CliEventArgs e)
	{
		super.OnCommandOutput(sender, e);
	}
	@Override
	public abstract void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;;
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlLoaded(Object sender, RmlEventArgs e)throws NotImplementedException, InconsistenMessageException;;	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlListener#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlRendered(Object sender, RmlEventArgs e)throws NotImplementedException, InconsistenMessageException;;
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#OnControlWrite(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public abstract void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException ;
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectUnmarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public final void OnObjectUnmarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectUnmarshalled(sender, e);
		
		// If parent is set addCommandListener and CliListeners.
		AddParentRmlListeners();
		
		// Assign read data.
		Rml rml = e.getRml();
		EnumSet<RuntimeOptions> options = rml.getRuntimeOptions();
		
		this.setUuid(e.getUUID());
		this.setInherited(rml.getInherited());		
		this.setRuntimeOptions(options);		
		
		if (!rml.getInherited())
		{
			if(rml.getRuntimeOptions() != null)
			{
				setRuntimeOptions(rml.getRuntimeOptions());	
			}
			else
			{
				throw new InconsistenMessageException(this, "\n CLI options null when Inherited = false");
			}
		}
		ControlReadEvent(new RmlEventArgs(this, RmlEventType.READ, rml));
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.rml.Rml#OnObjectMarshalled(java.lang.Object, org.httprobot.common.events.MarshallerEventArgs)
	 */
	@Override
	public final void OnObjectMarshalled(Object sender, MarshallerEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		super.OnObjectMarshalled(sender, e);		
		ControlWriteEvent(new RmlEventArgs(this, RmlEventType.WRITE, this.message));
	}
	/**
	 * Renders the control. Fires ControlRenderedEvent.
	 */
	private final void RenderControl() 
	{
		ControlRenderEvent(new RmlEventArgs(this, RmlEventType.RENDER, this.message));
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.IControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void setIsRendered(Boolean value) 
	{
		this.isRendered = value;
		
		if(value==true)
		{
			RenderControl();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.IControl#setMessage(org.httprobot.common.rml.Rml)
	 */
	@Override
	public void setMessage(Rml message)
	{
		if(message == null)
		{
			this.message = message;
			addCommandOutputListener(this.message);
			LoadControl();
		}
		else if(this.message == null)
		{
			this.message = message;
			addCommandOutputListener(this.message);
			LoadControl();
		}
		else if(message != this.message)
		{
			this.message = message;
			addCommandOutputListener(this.message);
			LoadControl();
			ChangeControl(message);
		}
	}
	
	public void breakInheritance() throws InconsistenMessageException
	{
		if(this.getInherited())
		{
			this.setInherited(false);
			CliCommandOutputEvent(new CliEventArgs(this, Command.SET_INHERITANCE));			
			ChangeControl(this.getMessage());
		}		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.common.io.MarshalObject#getCliOptions()
	 */
	@Override
	public EnumSet<RuntimeOptions> getRuntimeOptions() 
	{
		if(this.getInherited())
		{
			return parent.getRuntimeOptions();
		}
		else
		{
			if(super.getRuntimeOptions() != null)
			{
				return super.getRuntimeOptions();
			}
			else 
			{
				return RuntimeOptions.DEFAULT_DEBUG;	
			}			
		}
	}
}