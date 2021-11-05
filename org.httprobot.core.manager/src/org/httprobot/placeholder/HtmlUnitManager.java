package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Manager;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

@XmlRootElement
public final class HtmlUnitManager
	extends AbstractPlaceholderManager<InputField, HtmlPage, HtmlUnitControl> {

	/**
	 * 239612135819734039L
	 */
	private static final long serialVersionUID = 239612135819734039L;
	
	public HtmlUnitManager() {
	}
	public HtmlUnitManager(HtmlUnit message, Manager<?> parent) {
	}
	@Override
	public void OnCommandReceived(CommandEventArgs e) {
		super.OnCommandReceived(e);
	}
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		// TODO Auto-generated method stub
		
	}
}
