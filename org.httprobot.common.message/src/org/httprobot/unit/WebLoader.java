package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.httprobot.AbstractUnit;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.unit.adapter.BrowserVersionAdapter;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class WebLoader extends AbstractUnit {

	/**
	 * 7700952400328745265L
	 */
	private static final long serialVersionUID = 7700952400328745265L;
	
	BrowserVersion browserVersion;
	Integer time;
	Paginator paginator;
	
	@XmlJavaTypeAdapter(value = BrowserVersionAdapter.class)
	public BrowserVersion getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(BrowserVersion browserVersion) {
		this.browserVersion = browserVersion;
	}
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
		setBrowserVersion(webLoader.getBrowserVersion());
		setTime(webLoader.getTime());
		setPaginator(webLoader.getPaginator());
	}
}
