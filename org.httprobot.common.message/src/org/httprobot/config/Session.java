package org.httprobot.config;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Session extends AbstractConfig {

	/**
	 * -7934455651557768961L
	 */
	private static final long serialVersionUID = -7934455651557768961L;

	Date datetime;
	
	@XmlElement
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Session() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Session session = Session.class.cast(e.getSource());
		setDatetime(session.getDatetime());
	}
}
