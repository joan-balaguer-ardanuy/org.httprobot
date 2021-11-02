package org.httprobot.unit;

import org.httprobot.Enums.Data;
import org.httprobot.Manager;
import org.httprobot.MappingManager;
import org.httprobot.event.ManagerEventArgs;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PaginatorManager 
	extends MappingManager<HtmlPage, HtmlAnchor, PaginatorControl> {

	/**
	 * 6281489078874071651L
	 */
	private static final long serialVersionUID = 6281489078874071651L;

	public PaginatorManager() {
	
	}
	public PaginatorManager(Paginator message, Manager<?> parent) {
		super(message, PaginatorControl.class, parent);
	}

	@Override
	public HtmlAnchor put(HtmlPage key, HtmlAnchor value) {
		keySet().add(key);
		setKey(key);
		setValue(value);
		
		for(HtmlAnchor anchor : key.getAnchors()) {
			if (anchor.getFirstChild() != null) {
				if (anchor.getFirstChild().getTextContent().contains(getControl().get(Data.PAGINATOR_ANCHOR).toString())) {
					value = anchor;
					setValue(value);
				}
			}
		}
		return super.put(key, value);
	}
	
	@Override
	public void OnManagerEvent(ManagerEventArgs e) {
		
	}
}