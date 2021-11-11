package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import org.httprobot.AbstractUnit;
import org.httprobot.event.MessageEventArgs;

public class WebLoader extends AbstractUnit {

	/**
	 * 7700952400328745265L
	 */
	private static final long serialVersionUID = 7700952400328745265L;
	
	Integer time;
	Paginator paginator;
	
	@XmlElement
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer periodTime) {
		this.time = periodTime;
	}
	@XmlElement
	public Paginator getPaginator() {
		return paginator;
	}
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
	
	public WebLoader() {
		
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		WebLoader webLoader = WebLoader.class.cast(e.getSource());
		setTime(webLoader.getTime());
		setPaginator(webLoader.getPaginator());
	}
}
