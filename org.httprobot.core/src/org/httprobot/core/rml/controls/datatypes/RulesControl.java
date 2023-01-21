package org.httprobot.core.rml.controls.datatypes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.Rule;
import org.httprobot.common.rml.datatypes.Rules;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;

/**
 * Rules Control Class. Inherits {@link RmlDataTypeControl}.
 * @author Joan
 * 
 */
@XmlRootElement
public class RulesControl extends RmlDataTypeControl
{
	/**
	 * -1514952998614337418L
	 */
	private static final long serialVersionUID = -1514952998614337418L;
	private Rules rules;
	private Integer rules_count;
	private ArrayList<Rule> rule_list;
	ArrayList<RuleControl> rules_control_list;	
	/**
	 * Gets the array of rules.
	 * @return {@link ArrayList} of {@link Rule}
	 */
	public Rules getRules() {
		return rules;
	}
	/**
	 * Gets the array of rules controls.
	 * @return {@link ArrayList} of {@link RuleControl}
	 */
	public ArrayList<RuleControl> getGet_rules() {
		return rules_control_list;
	}
	/**
	 * Rules control default constructor.
	 */
	public RulesControl()
	{
		super();		
	}
	/**
	 * RulesControl class constructor.
	 * @param parent {@link IRmlListener} the parent control
	 * @param rules {@link Rules} the rules
	 */
	public RulesControl(IRmlListener parent, Rules rules)
	{
		super(parent, rules);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				Rules rules = Rules.class.cast(e.getMessage());
				
				if(rules.getRule() != null)
				{
					for(RuleControl ruleControl : this.rules_control_list)
					{
						for(Rule rule : rules.getRule())
						{
							if(rule.getUuid() == ruleControl.getUuid())
							{
								ruleControl.setMessage(rule);
								break;
							}
						}
					}
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "\nRulesControl.OnControlLoaded: Rules RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.rules_control_list = new ArrayList<RuleControl>();
			this.rule_list = new ArrayList<Rule>();
			this.rules = new Rules();
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());			
			
			this.rules.setUuid(getUuid());
			this.rules.setInherited(getInherited());
			this.rules.setRuntimeOptions(getRuntimeOptions());
			
			//Associate message with control
			this.addCommandOutputListener(this.rules);
			
			try
			{
				Rules rules = Rules.class.cast(e.getMessage());
				
				if(rules.getRule() != null)
				{
					this.rules_count = rules.getRule().size();
					
					for(Rule rule : rules.getRule())
					{
						//This instance listens for it's CliCommandInput
						RuleControl rule_control = new RuleControl(this, rule);
						
						//Associate message to control
						rule_control.addRuleListener(this);
						this.addCommandOutputListener(rule_control);
						
						//Add controller for data
						this.rules_control_list.add(rule_control);
					}
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "\nRulesControl.OnControlInit: Rules RML message expected");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRulesWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRulesWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				this.rule_list.add(Rule.class.cast(e.getMessage()));

				if(this.rules_count == this.rule_list.size())
				{
					//Set loaded data
					this.rules.setRule(this.rule_list);
				}
				
				CliCommandInputEvent(new CliEventArgs(this, Command.RULE_CONTROL, e.getMessage()));
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "\nRulesControl.OnRuleLoaded: Rule RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{
		
	}
}