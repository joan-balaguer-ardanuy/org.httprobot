package org.httprobot.config;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class Log extends AbstractConfig {

	/**
	 * -4278626463665433544L
	 */
	private static final long serialVersionUID = -4278626463665433544L;

	LinkedHashSet<Session> session;
	
	@XmlElement
	public LinkedHashSet<Session> getSession() {
		return session;
	}
	public void setSession(LinkedHashSet<Session> session) {
		this.session = session;
	}

	public Log() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Log log = Log.class.cast(e.getSource());
		setSession(log.getSession());
	}
}
