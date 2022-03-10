package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Listener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.operator.Html;
import org.httprobot.placeholder.html.AbstractHtmlParent;
import org.w3c.dom.Document;

@XmlRootElement
public final class HtmlParent
	extends AbstractHtmlParent<InputField, Document, HtmlControl> {

	/**
	 * 239612135819734039L
	 */
	private static final long serialVersionUID = 239612135819734039L;
	
	public HtmlParent() {
	}
	public HtmlParent(Html message, Listener parent) {
	}
	@Override
	public void OnCommandEvent(CommandEventArgs e) {
		super.OnEventReceived(e);
	}
	@Override
	public void OnParentEvent(ManagerEventArgs e) {
		// TODO Auto-generated method stub
		
	}
}