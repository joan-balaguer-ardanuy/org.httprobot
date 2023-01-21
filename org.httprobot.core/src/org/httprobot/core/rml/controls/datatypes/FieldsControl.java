package org.httprobot.core.rml.controls.datatypes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.Field;
import org.httprobot.common.rml.datatypes.Fields;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;


/**
 * @author Joan
 * FieldsControl Class. Inherits RmlDataTypeControl.
 */
@XmlRootElement
public class FieldsControl extends RmlDataTypeControl
{
	/**
	 * -723575690933280498L
	 */
	private static final long serialVersionUID = -723575690933280498L;
	private Integer field_count;	
	private Fields fields;
	private ArrayList<Field> field_list;
	private ArrayList<FieldControl> field_control_list;
	/**
	 * Fields control default class constructor.
	 */
	public FieldsControl()
	{
		super();	
	}
	/**
	 * FieldsControl class constructor.
	 * @param parent {@link IRmlListener} the parent control
	 * @param fields {@link Fields} the fields to set
	 */
	public FieldsControl(IRmlListener parent, Fields fields)
	{
		super(parent, fields);
	}	
	/**
	 * Gets the field count.
	 * @return {@link Integer}
	 */
	public Integer getField_count() 
	{
		return field_count;
	}
	/**
	 * Gets the fields.
	 * @return {@link Fields}
	 */
	public Fields getFields()
	{
		return fields;
	}
	/**
	 * Gets the array of fields.
	 * @return {@link ArrayList} of {@link FieldControl}
	 */
	public ArrayList<FieldControl> getGet_fields()
	{
		return field_control_list;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.field_control_list = new ArrayList<FieldControl>();
			this.field_list = new ArrayList<Field>();
			this.fields = new Fields();
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.fields.setUuid(getUuid());
			this.fields.setInherited(getInherited());
			this.fields.setRuntimeOptions(getRuntimeOptions());
			
			//Associate message with control
			this.addCommandOutputListener(this.fields);
			
			Fields fields = Fields.class.cast(e.getMessage());
			
			if(fields != null) 
			{
				if(fields.getField() != null)
				{
					//Sets the count of servers for post back checks
					this.field_count = fields.getField().size();
					
					for(Field field : fields.getField())
					{
						//This instance listens for it's CliCommandInput
						FieldControl field_control = new FieldControl(this, field);
						
						//Associate child controls to parent.
						field_control.addFieldListener(this);
						this.addCommandOutputListener(field_control);					
						
						//Set the message
						this.field_control_list.add(field_control);
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "FieldsControl.OnControlInit: Field RML message array expected");
				}
			}
			else
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "FieldsControl.OnControlInit: Fields RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) 
	{
		if(e.getMessage() != null)
		{
			Fields fields = Fields.class.cast(e.getMessage());
			
			if(fields != null)
			{
				if(fields.getField() != null)
				{
					for(FieldControl fieldControl : this.field_control_list)
					{
						for(Field field : fields.getField())
						{
							if(field.getUuid() == fieldControl.getUuid())
							{
								fieldControl.setMessage(field);
							}
						}
					}
				}
			}
		}
	}
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e)  throws NotImplementedException, InconsistenMessageException
	{
		
	}
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnFieldLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			Field field = Field.class.cast(e.getMessage());
			
			this.field_list.add(field);
			
			//If it's the last Field message set Fields message
			if(field_count == this.field_list.size())
			{
				this.fields.setField(field_list);
				CliCommandInputEvent(new CliEventArgs(this, Command.FIELDS_CONTROL, e.getMessage()));
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnFieldsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnFieldsRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}	
}