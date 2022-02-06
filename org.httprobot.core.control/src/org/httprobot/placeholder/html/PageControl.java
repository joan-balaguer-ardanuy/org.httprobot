package org.httprobot.placeholder.html;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.ControlListener;
import org.httprobot.Command;
import org.httprobot.Data;
import org.httprobot.event.CommandEventArgs;
import org.httprobot.event.ControlEventArgs;
import org.httprobot.unit.ElementControl;

@XmlRootElement
public final class PageControl 
	extends AbstractHtmlControl<Page> {

	/**
	 * -6505681141524637686L
	 */
	private static final long serialVersionUID = -6505681141524637686L;
	
	AnchorControl anchorControl;
	ElementControl elementControl;
	PageControl pageControl;
	
	@XmlElement
	public AnchorControl getAnchorControl() {
		return anchorControl;
	}
	public void setAnchorControl(AnchorControl anchorControl) {
		this.anchorControl = anchorControl;
	}
	@XmlElement
	public ElementControl getElementControl() {
		return elementControl;
	}
	public void setElementControl(ElementControl elementControl) {
		this.elementControl = elementControl;
	}
	@XmlElement
	public PageControl getPageControl() {
		return pageControl;
	}
	public void setPageControl(PageControl pageControl) {
		this.pageControl = pageControl;
	}
	
	public PageControl() {
		super();
		setMessage(new Page());
	}
	public PageControl(Page message, ControlListener parent) {
		super(message, parent);
	}
	
	@Override
	public void OnControlInitialized(ControlEventArgs e) {
		super.OnControlInitialized(e);
		if(e.getSource().equals(this)) {
			Page page = Page.class.cast(e.getMessage());
			
			if(page.getAnchor() != null) {
				new AnchorControl(page.getAnchor(), this);
			}
			if(page.getElement() != null) {
				new ElementControl(page.getElement(), this);
			}
			if(page.getPage() != null) {
				new PageControl(page.getPage(), this);
			}
		}
		else if(e.getSource() instanceof AnchorControl) {
			anchorControl = AnchorControl.class.cast(e.getSource());
			addChildControl(anchorControl);
		} else if(e.getSource() instanceof ElementControl) {
			elementControl = ElementControl.class.cast(e.getSource());
			addChildControl(elementControl);
		} else if (e.getSource() instanceof PageControl) {
			pageControl = PageControl.class.cast(e.getSource());
			addChildControl(pageControl);
		}
	}
	@Override
	public void OnControlLoaded(ControlEventArgs e) {
		super.OnControlLoaded(e);
		if(e.getSource().equals(this)) {
			
			Page page = Page.class.cast(e.getMessage());
			
			if(hasChildControls()) {
				
				while(hasNext()) {
					
					ControlListener control = next();
					
					if(control instanceof AnchorControl ?
							page.getAnchor() != null ?
									anchorControl.equals(control)
									: false : false) {
						anchorControl.loadControl();
					}
					else if(control instanceof ElementControl ?
							page.getElement() != null ?
									elementControl.equals(control)
									: false : false) {
						elementControl.loadControl();
					}
					else if(control instanceof PageControl ?
							page.getPage() != null ?
									pageControl.equals(control)
									: false : false) {
						pageControl.loadControl();
					}
				}
				// Set control ready to be iterated again.
				reset();
				// Send event to parent
				CommandListenerEvent(new CommandEventArgs(this, Command.PAGE_CONTROL_LOADED));
			}
		}
		else if(e.getSource() instanceof AnchorControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.ANCHOR, e.getMessage());
			}
		}
		else if(e.getSource() instanceof ElementControl) {
			if(getChildControls().contains(e.getSource())) {
				put(Data.ELEMENT, e.getMessage());
			}
		}
		else if(e.getSource() instanceof PageControl) {
			if (getChildControls().contains(e.getSource())) {
				put(Data.PAGE, e.getMessage());
			}
		}
	}
}