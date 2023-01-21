package org.httprobot.core.rml.controls;

import java.util.Vector;

import javax.xml.bind.annotation.XmlTransient;

import org.httprobot.common.definitions.Enums.RmlEventType;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.Rml;
import org.httprobot.common.rml.datatypes.Action;
import org.httprobot.common.rml.datatypes.DataView;
import org.httprobot.common.rml.datatypes.Field;
import org.httprobot.common.rml.datatypes.FieldRef;
import org.httprobot.common.rml.datatypes.Fields;
import org.httprobot.common.rml.datatypes.Rule;
import org.httprobot.common.rml.datatypes.Rules;
import org.httprobot.common.rml.datatypes.ServerInfo;
import org.httprobot.common.rml.datatypes.Step;
import org.httprobot.common.rml.datatypes.Steps;
import org.httprobot.common.rml.datatypes.WebOptions;
import org.httprobot.common.rml.datatypes.operators.GetField;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.datatypes.ActionControl;
import org.httprobot.core.rml.controls.datatypes.DataViewControl;
import org.httprobot.core.rml.controls.datatypes.FieldControl;
import org.httprobot.core.rml.controls.datatypes.FieldRefControl;
import org.httprobot.core.rml.controls.datatypes.FieldsControl;
import org.httprobot.core.rml.controls.datatypes.RuleControl;
import org.httprobot.core.rml.controls.datatypes.RulesControl;
import org.httprobot.core.rml.controls.datatypes.ServerInfoControl;
import org.httprobot.core.rml.controls.datatypes.StepControl;
import org.httprobot.core.rml.controls.datatypes.StepsControl;
import org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IFieldListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IFieldsListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IRuleListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IRulesListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IStepListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IStepsListener;
import org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener;
import org.httprobot.core.rml.controls.interfaces.IRmlDataTypeListener;


/**
 * @author Joan
 * RML DataType control class. Inherits {@link RmlControl}. Is {@link IRmlDataTypeListener}. 
 */
@XmlTransient
public abstract class RmlDataTypeControl extends RmlControl implements IRmlDataTypeListener
{
	/**
	 * -5117685502901358057L
	 */
	private static final long serialVersionUID = -5117685502901358057L;
	/**
	 * {@link Rule} DataView Listeners
	 */
	private Vector<IDataViewListener> data_view_listeners;
	/**
	 * {@link Rule} Listeners
	 */
	private Vector<IRuleListener> rule_listeners;
	/**
	 * Steps Listeners
	 */
	private Vector<IStepsListener> steps_listeners;
	/**
	 * Step Listeners
	 */
	private Vector<IStepListener> step_listeners;
	/**
	 * ServerInfo Listeners
	 */
	private Vector<IServerInfoListener> server_info_listeners;
	/**
	 * Rules Listeners
	 */
	private Vector<IRulesListener> rules_listeners;
	/**
	 * Fields Listeners
	 */
	private Vector<IFieldsListener> fields_listeners;	
	/**
	 * FieldRef listeners
	 */
	private Vector<IFieldRefListener> field_ref_listeners;
	/**
	 * Field Listeners
	 */
	private Vector<IFieldListener> field_listeners;
	/**
	 * Action Listeners
	 */
	private Vector<IActionListener> action_listeners;
	/**
	 * WebOptions Listeners
	 */
	private Vector<IWebOptionsListener> web_options_listeners;
	//	private Vector<IF>
	/**
	 * RML DataType control default constructor.
	 * Initializes and add event listeners.
	 */
	public RmlDataTypeControl()
	{
		super();
		//Initialize using data
		InitDataTypeControl(null, null);
	}
	/**
	 * RML DataType control class constructor. 
	 * Initializes and add event listeners.
	 * @param parent {@link IRmlListener} the parent listener
	 * @param message {@link Rml} the message
	 */
	public RmlDataTypeControl(IRmlListener parent, Rml message)
	{
		super(parent, message);
		
		//Initialize using data
		InitDataTypeControl(parent, message);
	}
	/**
	 * Adds {@link Action} event {@link IActionListener}.
	 * @param listener {@link IActionListener} the listener
	 */
	public final synchronized void addActionListener(IActionListener listener)
	{
		action_listeners.add(listener);
	}
	
	/**
	 * Adds {@link WebOptions} event {@link IWebOptionsListener}.
	 * @param listener {@link IWebOptionsListener} the listener
	 */
	public final synchronized void addWebOptionsListener(IWebOptionsListener listener)
	{
		web_options_listeners.add(listener);
	}
	/**
	 * Removes {@link WebOptions} event {@link IWebOptionsListener}.
	 * @param listener {@link IWebOptionsListener} the listener
	 */
	public final synchronized void removeWebOptionsListener(IWebOptionsListener listener)
	{
		web_options_listeners.remove(listener);
	}
	/**
	 * Adds {@link DataView} event {@link IDataViewListener}.
	 * @param listener {@link IActionListener} the listener
	 */
	public final synchronized void addDataViewListener(IDataViewListener listener)
	{
		data_view_listeners.add(listener);
	}
	/**
	 * Adds {@link Field} event {@link IFieldListener}.
	 * @param listener {@link IFieldListener} the listener
	 */
	public final synchronized void addFieldListener(IFieldListener listener)
	{
		field_listeners.add(listener);
	}
	/**
	 * Adds {@link FieldRef} event {@link IFieldRefListener}.
	 * @param listener {@link IFieldRefListener} the listener
	 */
	public final synchronized void addFieldRefListener(IFieldRefListener listener)
	{
		field_ref_listeners.add(listener);
	}
	/**
	 * Adds {@link Fields} event {@link IFieldsListener}.
	 * @param listener {@link IFieldsListener} the listener
	 */
	public final synchronized void addFieldsListener(IFieldsListener listener)
	{
		fields_listeners.add(listener);
	}
	/**
	 * Adds {@link Action} event {@link IRmlDataTypeListener}
	 * @param listener
	 */
	public final synchronized void addRuleListener(IRuleListener listener)
	{
		rule_listeners.add(listener);
	}
	
/**
	 * Adds {@link Rules} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addRulesListener(IRulesListener listener)
	{
		rules_listeners.add(listener);
	}
	/**
	 * Adds {@link ServerInfo} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addServerInfoListener(IServerInfoListener listener)
	{
		server_info_listeners.add(listener);
	}
	/**
	 * Adds {@link Step} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addStepListener(IStepListener listener)
	{
		step_listeners.add(listener);
	}
	/**
	 * Adds {@link Steps} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void addStepsListener(IStepsListener listener)
	{
		steps_listeners.add(listener);
	}
	/**
	 * Fires {@link Field} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlActionEvent(RmlEventArgs e) 
	{
		for (IActionListener listener : action_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnActionInit(this, e);
						break;
					case READ:
						listener.OnActionRead(this, e);
						break;
					case LOAD:
						listener.OnActionLoaded(this, e);
						break;
					case CHANGE:
						listener.OnActionChanged(this, e);
						break;
					case RENDER:
						listener.OnActionRendered(this, e);
						break;
					case WRITE:
						listener.OnActionWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link Field} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlWebOptionsEvent(RmlEventArgs e) 
	{
		for (IWebOptionsListener listener : web_options_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnWebOptionsInit(this, e);
						break;
					case READ:
						listener.OnWebOptionsRead(this, e);
						break;
					case LOAD:
						listener.OnWebOptionsLoaded(this, e);
						break;
					case CHANGE:
						listener.OnWebOptionsChanged(this, e);
						break;
					case RENDER:
						listener.OnWebOptionsRendered(this, e);
						break;
					case WRITE:
						listener.OnWebOptionsWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link DataView} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlDataViewEvent(RmlEventArgs e) 
	{
		for (IDataViewListener listener : data_view_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnDataViewInit(this, e);
						break;
					case READ:
						listener.OnDataViewRead(this, e);
						break;
					case LOAD:
						listener.OnDataViewLoaded(this, e);
						break;
					case CHANGE:
						listener.OnDataViewChanged(this, e);
						break;
					case RENDER:
						listener.OnDataViewRendered(this, e);
						break;
					case WRITE:
						listener.OnDataViewWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link Field} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlFieldEvent(RmlEventArgs e) 
	{
		for (IFieldListener listener : field_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnFieldInit(this, e);
						break;
					case READ:
						listener.OnFieldRead(this, e);
						break;
					case LOAD:
						listener.OnFieldLoaded(this, e);
						break;
					case CHANGE:
						listener.OnFieldChanged(this, e);
						break;
					case RENDER:
						listener.OnFieldRendered(this, e);
						break;
					case WRITE:
						listener.OnFieldWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link FieldRef} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlFieldRefEvent(RmlEventArgs e) 
	{
		for (IFieldRefListener listener : field_ref_listeners) 
		{
			try
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnFieldRefInit(this, e);
						break;
					case READ:
						listener.OnFieldRefRead(this, e);
						break;
					case LOAD:
						listener.OnFieldRefLoaded(this, e);
						break;
					case CHANGE:
						listener.OnFieldRefChanged(this, e);
						break;
					case RENDER:
						listener.OnFieldRefRendered(this, e);
						break;
					case WRITE:
						listener.OnFieldRefWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link Fields} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlFieldsEvent(RmlEventArgs e) 
	{
		for (IFieldsListener listener : fields_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnFieldsInit(this, e);
						break;
					case READ:
						listener.OnFieldsRead(this, e);		
						break;
					case LOAD:
						listener.OnFieldsLoaded(this, e);
						break;
					case CHANGE:
						listener.OnFieldsChanged(this, e);
						break;
					case RENDER:
						listener.OnFieldsRendered(this, e);
						break;
					case WRITE:
						listener.OnFieldsWrite(this, e);
						break;
					default:
						break;
				}				
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
	 * Fires {@link GetField} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlRuleEvent(RmlEventArgs e)
	{
		for (IRuleListener listener : rule_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnRuleInit(this, e);
						break;
					case READ:
						listener.OnRuleRead(this, e);
						break;
					case LOAD:
						listener.OnRuleLoaded(this, e);
						break;
					case CHANGE:
						listener.OnRuleChanged(this, e);
						break;
					case RENDER:
						listener.OnRuleRendered(this, e);
						break;
					case WRITE:
						listener.OnRuleWrite(this, e);
						break;
					default:
						break;
				}	
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
	 * Fires {@link Rules} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlRulesEvent(RmlEventArgs e) 
	{
		for (IRulesListener listener : rules_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnRulesInit(this, e);
						break;
					case READ:
						listener.OnRulesRead(this, e);
						break;
					case LOAD:
						listener.OnRulesLoaded(this, e);
						break;
					case CHANGE:
						listener.OnRulesChanged(this, e);
						break;
					case RENDER:
						listener.OnRulesRendered(this, e);
						break;
					case WRITE:
						listener.OnRulesWrite(this, e);
						break;
					default:
						break;
				}
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
	 * Fires {@link ServerInfo} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlServerInfoEvent(RmlEventArgs e) 
	{
		for (IServerInfoListener listener : server_info_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnServerInfoInit(this, e);
						break;
					case READ:
						listener.OnServerInfoRead(this, e);
						break;
					case LOAD:
						listener.OnServerInfoLoaded(this, e);
						break;
					case CHANGE:
						listener.OnServerInfoChanged(this, e);
						break;
					case RENDER:
						listener.OnServerInfoRendered(this, e);
						break;
					case WRITE:
						listener.OnServerInfoWrite(this, e);
						break;
					default:
						break;
				}				
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
	 * Fires {@link Step} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlStepEvent(RmlEventArgs e) 
	{
		for (IStepListener listener : step_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnStepInit(this, e);
						break;
					case READ:
						listener.OnStepRead(this, e);
						break;
					case LOAD:
						listener.OnStepLoaded(this, e);
						break;
					case CHANGE:
						listener.OnStepChanged(this, e);
						break;
					case RENDER:
						listener.OnStepRendered(this, e);
						break;
					case WRITE:
						listener.OnStepWrite(this, e);
						break;	
					default:
						break;
				}
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
	 * Fires {@link Steps} event.
	 * @param e {@link RmlEventArgs} the arguments
	 */
	protected final void ControlStepsEvent(RmlEventArgs e) 
	{
		for (IStepsListener listener : steps_listeners) 
		{
			try 
			{
				switch (e.getRmlEventType()) 
				{
					case INIT:
						listener.OnStepsInit(this, e);
						break;
					case READ:
						listener.OnStepsRead(this, e);
						break;
					case LOAD:
						listener.OnStepsLoaded(this, e);
						break;
					case CHANGE:
						listener.OnStepsChanged(this, e);
						break;
					case RENDER:
						listener.OnStepsRendered(this, e);
						break;
					case WRITE:
						listener.OnStepsWrite(this, e);
						break;
					default:
						break;
				}
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
	 * Initializes RML datatype control.
	 * @param parent {@link IRmlListener} the parent
	 * @param message {@link Rml} the message
	 */
	private final void InitDataTypeControl(IRmlListener parent, Rml message) 
	{
		action_listeners = new Vector<IActionListener>();
		fields_listeners = new Vector<IFieldsListener>();
		field_listeners = new Vector<IFieldListener>();
		field_ref_listeners = new Vector<IFieldRefListener>();	
		rule_listeners = new Vector<IRuleListener>();
		rules_listeners = new Vector<IRulesListener>();
		server_info_listeners = new Vector<IServerInfoListener>();		
		step_listeners = new Vector<IStepListener>();
		steps_listeners = new Vector<IStepsListener>();
		data_view_listeners = new Vector<IDataViewListener>();
		web_options_listeners = new Vector<IWebOptionsListener>();
		
		addRuleListener(this);	
		addFieldsListener(this);
		addFieldListener(this);
		addFieldRefListener(this);
		addRulesListener(this);
		addServerInfoListener(this);	
		addStepsListener(this);
		addStepListener(this);
		addActionListener(this);
		addDataViewListener(this);

		RmlEventArgs e = new RmlEventArgs(this, RmlEventType.INIT, message);
		
		if(e.getSource() instanceof DataViewControl)
		{
			ControlDataViewEvent(e);
		}
		if(e.getSource() instanceof ActionControl)
		{
			ControlActionEvent(e);
		}
		if(e.getSource() instanceof FieldControl)
		{
			ControlFieldEvent(e);
		}
		else if(e.getSource() instanceof FieldsControl)
		{
			ControlFieldsEvent(e);
		}
		else if(e.getSource() instanceof RuleControl)
		{
			ControlRuleEvent(e);
		}
		else if(e.getSource() instanceof RulesControl)
		{
			ControlRulesEvent(e);
		}
		else if(e.getSource() instanceof ServerInfoControl)
		{
			ControlServerInfoEvent(e);
		}
		else if(e.getSource() instanceof StepControl)
		{
			ControlStepEvent(e);
		}
		else if(e.getSource() instanceof StepsControl)
		{
			ControlStepsEvent(e);
		}
		else if(e.getSource() instanceof FieldRefControl)
		{
			ControlFieldRefEvent(e);
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IActionListener#OnActionWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnActionWrite(Object sender, RmlEventArgs e)throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnActionWrite not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public abstract void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public abstract void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException;		
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IDataViewControl#OnDataViewWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnDataViewWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnDataViewWrite not implemented method");
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldControl#OnFieldChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldChanged not implemented method");
	}		
	@Override
	public void OnFieldInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnFieldLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldControl#OnFieldRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefChanged not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldRefListener#OnFieldRefWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldRefWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRefWrite not implemented method");		
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnFieldRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnFieldRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldsControl#OnFieldsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldsChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldsControl#OnFieldsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldsInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnFieldsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnFieldsLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldsLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldsControl#OnFieldsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnFieldsRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnFieldsRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldsRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldsControl#OnFieldsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldsWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IFieldControl#OnFieldWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnFieldWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRulesControl#OnRulesChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRulesControl#OnRulesInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnRulesLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRulesLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesLoaded not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRulesControl#OnRulesRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnRulesRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnRulesRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRulesControl#OnRulesWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRulesWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IRuleControl#OnRuleWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnRuleWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoControl#OnServerInfoChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoControl#OnServerInfoInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnServerInfoLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnServerInfoLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoControl#OnServerInfoRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnServerInfoRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnServerInfoRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IServerInfoControl#OnServerInfoWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnServerInfoWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnServerInfoWrite not implemented method");
	}	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepControl#OnStepChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepControl#OnStepInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStepLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepControl#OnStepRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStepRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsControl#OnStepsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsControl#OnStepsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStepsLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepsLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsLoaded not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsControl#OnStepsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.interfaces.IRmlDataTypeListener#OnStepsRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepsRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsRendered not implemented method");		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepsControl#OnStepsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepsWrite not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IStepControl#OnStepWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnStepWrite not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsInit not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsRead not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsLoaded not implemented method");	
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsChanged not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsRendered not implemented method");
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.datatypes.interfaces.IWebOptionsListener#OnWebOptionsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnWebOptionsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		CommandLineInterface.ThrowNotImplementedException(this, "RmlDataTypeControl.OnWebOptionsWrite not implemented method");	
	}
	/**
	 * Removes {@link Action} event {@link IRmlDataTypeListener}
	 * @param listener
	 */
	public final synchronized void removeActionListener(IRmlDataTypeListener listener)
	{
		action_listeners.remove(listener);
	}
	/**
	 * Adds {@link DataView} event {@link IDataViewListener}.
	 * @param listener {@link IActionListener} the listener
	 */
	public final synchronized void removeDataViewListener(IDataViewListener listener)
	{
		data_view_listeners.remove(listener);
	}
	/**
	 * Removes {@link Field} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeFieldListener(IRmlDataTypeListener listener)
	{
		field_listeners.remove(listener);
	}
	/**
	 * Removes {@link FieldRef} event {@link IFieldRefListener}.
	 * @param listener {@link IFieldRefListener} the listener
	 */
	public final synchronized void removeFieldRefListener(IFieldRefListener listener)
	{
		field_ref_listeners.remove(listener);
	}
	/**
	 * Removes {@link Fields} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeFieldsListener(IRmlDataTypeListener listener)
	{
		fields_listeners.remove(listener);
	}
	/**
	 * Removes Rule event listener
	 * @param listener
	 */
	public final synchronized void removeRuleListener(IRmlDataTypeListener listener)
	{
		rule_listeners.remove(listener);
	}
	/**
	 * Removes {@link Rules} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeRulesListener(IRmlDataTypeListener listener)
	{
		rules_listeners.remove(listener);
	}
	/**
	 * Removes {@link ServerInfo} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeServerInfoListener(IRmlDataTypeListener listener)
	{
		server_info_listeners.remove(listener);
	}
	/**
	 * Removes {@link Step} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeStepListener(IRmlDataTypeListener listener)
	{
		step_listeners.remove(listener);
	}
	/**
	 * Removes {@link Steps} event {@link IRmlDataTypeListener}.
	 * @param listener {@link IRmlDataTypeListener} the listener
	 */
	public final synchronized void removeStepsListener(IRmlDataTypeListener listener)
	{
		steps_listeners.remove(listener);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#setIsRendered(java.lang.Boolean)
	 */
	@Override
	public void setIsRendered(Boolean value) 
	{
		super.setIsRendered(value);
		
		Rml message = this.getMessage();
		
		if(message instanceof Field)
		{
			ControlFieldEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Fields)
		{
			ControlFieldsEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Rules)
		{
			ControlRulesEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof ServerInfo)
		{
			ControlServerInfoEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Step)
		{
			ControlStepEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Steps)
		{
			ControlStepsEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof Action)
		{
			ControlActionEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof DataView)
		{
			ControlDataViewEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof FieldRef)
		{
			ControlFieldRefEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
		else if(message instanceof WebOptions)
		{
			ControlWebOptionsEvent(new RmlEventArgs(this, RmlEventType.RENDER, message));
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlControl#setMessage(org.httprobot.common.rml.RmlObject)
	 */
	@Override
	public void setMessage(Rml message)
	{	
		super.setMessage(message);
		
		if(message instanceof Field)
		{
			ControlFieldEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Fields)
		{
			ControlFieldsEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Rules)
		{
			ControlRulesEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof ServerInfo)
		{
			ControlServerInfoEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Step)
		{
			ControlStepEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Steps)
		{
			ControlStepsEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof Action)
		{
			ControlActionEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof DataView)
		{
			ControlDataViewEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
		else if(message instanceof WebOptions)
		{
			ControlWebOptionsEvent(new RmlEventArgs(this, RmlEventType.LOAD, message));
		}
	}
}