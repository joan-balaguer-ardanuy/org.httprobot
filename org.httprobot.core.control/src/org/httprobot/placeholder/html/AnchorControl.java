package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;

@XmlRootElement
public final class AnchorControl 
	extends AbstractHtmlControl<Anchor> {

	/**
	 * 2714219779554114907L
	 */
	private static final long serialVersionUID = 2714219779554114907L;
	
	public AnchorControl() {
		super();
	}
	public AnchorControl(Anchor message, ControlListener parent) {
		super(message, parent);
	}
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Anchor anchor = Anchor.class.cast(e.getMessage());
			if(anchor.getHref() == null) {
				throw new Error("AnchorControl.OnControlInitialized: Href XML element message is missing.");
			}
			if(anchor.getTextContent() == null) {
				throw new Error("AnchorControl.OnControlInitialized: TextContent XML element message is missing.");
			}
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			Anchor anchor = Anchor.class.cast(e.getMessage());
			if(anchor.getHref() != null) {
				put(Data.HREF, anchor.getHref());
			}
			if(anchor.getHrefLang() != null) {
				put(Data.HREF_LANG, anchor.getHrefLang());
			}
			if(anchor.getName() != null) {
				put(Data.NAME, anchor.getName());
			}
			if(anchor.getTextContent() != null) {
				put(Data.TEXT_CONTENT, anchor.getTextContent());
			}
			if(anchor.getCharset() != null) {
				put(Data.CHARSET, anchor.getCharset());
			}
			if(anchor.getType() != null) {
				put(Data.TYPE, anchor.getType());
			}
			if(anchor.getTarget() != null) {
				put(Data.TARGET, anchor.getTarget());
			}
			// Send event to parent
			CommandLineEvent(new CommandEventArgs(this, Command.ANCHOR_CONTROL_LOADED));
		}
	}
}