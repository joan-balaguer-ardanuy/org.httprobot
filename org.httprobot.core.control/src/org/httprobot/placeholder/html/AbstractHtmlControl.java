package org.httprobot.placeholder.html;

import org.httprobot.ControlListener;
import org.httprobot.AbstractHtml;
import org.httprobot.Enums.Data;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.string.AbstractStringControl;

public class AbstractHtmlControl<TMessage extends AbstractHtml>
	extends AbstractStringControl<TMessage> {

	/**
	 * 7409054080699637039L
	 */
	private static final long serialVersionUID = 7409054080699637039L;

	public AbstractHtmlControl() {
		super();
	}
	public AbstractHtmlControl(TMessage message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			AbstractHtml html = AbstractHtml.class.cast(e.getMessage());
			if(html.getId() != null) {
				put(Data.ID, html.getId());
			}
			if(html.getStyle() != null) {
				put(Data.STYLE, html.getStyle());
			}
			if(html.getClassName() != null) {
				put(Data.CLASS_NAME, html.getClassName());
			}
			if(html.getTitle() != null) {
				put(Data.TITLE, html.getTitle());
			}
		}
	}
}