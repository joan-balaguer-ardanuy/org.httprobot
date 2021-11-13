package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.openqa.selenium.WebElement;

@XmlRootElement
public final class SeleniumManager
	extends AbstractPlaceholderManager<InputField, WebElement, HtmlUnitControl> {

	/**
	 * 239612135819734039L
	 */
	private static final long serialVersionUID = 239612135819734039L;
	
	public SeleniumManager() {
	}
	public SeleniumManager(HtmlUnit message, ManagerListener parent) {
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