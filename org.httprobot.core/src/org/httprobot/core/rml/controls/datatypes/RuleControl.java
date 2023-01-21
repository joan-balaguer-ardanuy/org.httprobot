package org.httprobot.core.rml.controls.datatypes;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.Rule;
import org.httprobot.common.rml.datatypes.placeholders.HttpRequest;
import org.httprobot.common.rml.datatypes.placeholders.HtmlBody;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;
import org.httprobot.core.rml.controls.placeholders.HttpRequestControl;
import org.httprobot.core.rml.controls.placeholders.HtmlBodyControl;
import org.httprobot.core.rml.controls.placeholders.interfaces.IPlaceholderListener;

/**
 * Rule Control Class. Inherits {@link RmlDataTypeControl}.
 * @author Joan
 * 
 */
@XmlRootElement
public class RuleControl extends RmlDataTypeControl implements IPlaceholderListener  
{
	/**
	 * 5930451995172932267L
	 */
	private static final long serialVersionUID = 5930451995172932267L;
	
	HttpRequestControl http_request_control;
	HtmlBodyControl html_body_control;

	Rule rule;
	/**
	 * @return the web_request_control
	 */
	public HttpRequestControl getWeb_request_control() {
		return http_request_control;
	}
	/**
	 * @param web_request_control the web_request_control to set
	 */
	public void setWeb_request_control(HttpRequestControl web_request_control) {
		this.http_request_control = web_request_control;
	}
	/**
	 * @return the web_response_control
	 */
	public HtmlBodyControl getWeb_response_control() {
		return html_body_control;
	}
	/**
	 * @param web_response_control the web_response_control to set
	 */
	public void setWeb_response_control(HtmlBodyControl web_response_control) {
		this.html_body_control = web_response_control;
	}

	/**
	 * Rule control default class constructor.
	 */
	public RuleControl()
	{
		super();
	}
	/**
	 * Rule control class constructor.
	 * @param parent {@link IRmlListener} the parent
	 * @param rule {@link Rule} the rule
	 */
	public RuleControl(IRmlListener parent, Rule rule)
	{
		super(parent, rule);
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				Rule rule = Rule.class.cast(e.getMessage());
				
				if(rule.getHttpRequest() != null)
				{
					if(http_request_control.getUuid() == rule.getHttpRequest().getUuid())
					{
						http_request_control.setMessage(rule.getHttpRequest());
					}
				}
				if(rule.getHtmlBody() != null)
				{
					if(html_body_control.getUuid() == rule.getHtmlBody().getUuid())
					{
						html_body_control.setMessage(rule.getHtmlBody());
					}
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "RuleControl.OnControlLoaded: Rule RML message expected");
			}			
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			//Initialize using data.
			this.rule = new Rule();
			
			//Associate message to control.
			this.addCommandOutputListener(this.rule);			
			
			//Set inherited data.
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());

			this.rule.setUuid(this.getUuid());
			this.rule.setInherited(this.getInherited());
			this.rule.setRuntimeOptions(this.getRuntimeOptions());

			try
			{
				Rule rule = Rule.class.cast(e.getMessage());
				
				if(rule.getHttpRequest() != null)
				{
					//If HTTP request message not null instantiate action message control.
					this.http_request_control = new HttpRequestControl(this, rule.getHttpRequest());
					
					//Associate child control to parent.
					this.http_request_control.addHttpRequestListener(this);
					this.addCommandOutputListener(this.http_request_control);
				}
				if(rule.getHtmlBody() != null)
				{
					//If HTML body message not null instantiate action message control.
					this.html_body_control = new HtmlBodyControl(this, rule.getHtmlBody());
					
					//Associate child control to parent.
					this.html_body_control.addHtmlBodyListener(this);
					this.addCommandOutputListener(this.html_body_control);
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "RuleControl.OnControlInit: Rule RML message expected");
			}
		}
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
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnRuleWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnRuleWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
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
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				this.rule.setHtmlBody(HtmlBody.class.cast(e.getMessage()));
			}
			catch (ClassCastException ex1) 
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "RuleControl.OnHtmlBodyLoaded: HtmlBody RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebResponseListener#OnWebResponseWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHtmlBodyWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException  
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException  
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			try
			{
				this.rule.setHttpRequest(HttpRequest.class.cast(e.getMessage()));
			}
			catch (ClassCastException ex1) 
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "RuleControl.OnHttpRequestLoaded: HttpRequest RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestRendered(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.placeholders.interfaces.IWebRequestListener#OnWebRequestWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnHttpRequestWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/**
	 * Gets the controlled {@link Rule} RML message 
	 * @return {@link Rule} the rule
	 */
	public Rule getRule() {
		return rule;
	}
}