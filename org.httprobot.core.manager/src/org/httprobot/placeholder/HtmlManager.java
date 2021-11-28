package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ManagerListener;
import org.httprobot.data.field.InputField;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ManagerEventArgs;
import org.httprobot.net.WebDocument;
import org.httprobot.placeholder.html.AbstractHtmlManager;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;

@XmlRootElement
public final class HtmlManager
	extends AbstractHtmlManager<InputField, Document, HtmlUnitControl> {

	/**
	 * 239612135819734039L
	 */
	private static final long serialVersionUID = 239612135819734039L;
	
	public HtmlManager() {
	}
	public HtmlManager(HtmlUnit message, ManagerListener parent) {
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