package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Parent;
import org.httprobot.data.field.InputField;
import org.httprobot.placeholder.Html;
import org.httprobot.placeholder.html.AbstractHtmlParent;
import org.w3c.dom.Document;

@XmlRootElement
public final class HtmlParent
	extends AbstractHtmlParent<InputField, Document> {

	/**
	 * 239612135819734039L
	 */
	private static final long serialVersionUID = 239612135819734039L;
	
	public HtmlParent() {
	}
	public HtmlParent(Html message, Parent parent) {
	}
}