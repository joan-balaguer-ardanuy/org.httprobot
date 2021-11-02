package org.httprobot.placeholder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Enums.Command;
import org.httprobot.Enums.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.placeholder.html.PageControl;

@XmlRootElement
public final class HtmlUnitControl 
	extends AbstractPlaceholderControl<HtmlUnit> {

	/**
	 * -5062725452790453335L
	 */
	private static final long serialVersionUID = -5062725452790453335L;
	
	PageControl pageControl;
	
	@XmlElement
	public PageControl getPageControl() {
		return pageControl;
	}
	public void setPageControl(PageControl pageControl) {
		this.pageControl = pageControl;
	}
	
	public HtmlUnitControl() {
		super();
	}
	public HtmlUnitControl(HtmlUnit message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		if(e.getSource().equals(this)) {
			HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getMessage());
			
			if(htmlUnit.getPage() != null) {
				new PageControl(htmlUnit.getPage(), this);
			}
		} 
		else if(e.getSource() instanceof PageControl) {
			pageControl = PageControl.class.cast(e.getSource());
			addChildControl(pageControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		if (e.getSource().equals(this)) {

			HtmlUnit htmlUnit = HtmlUnit.class.cast(e.getMessage());

			if (hasChildControls()) {

				while (hasNext()) {
					ControlListener control = next();
					
					if(control instanceof PageControl ?
							htmlUnit.getPage() != null ?
									pageControl.equals(control)
									: false : false) {
						pageControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandLineEvent(new CommandEventArgs(this, Command.HTML_UNIT_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof PageControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.PAGE, e.getMessage());
			}	
		}
	}
}