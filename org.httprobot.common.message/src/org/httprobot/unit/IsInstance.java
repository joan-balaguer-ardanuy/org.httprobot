package org.httprobot.unit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractUnit;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class IsInstance extends AbstractUnit {

	/**
	 * -1245334166690086291L
	 */
	private static final long serialVersionUID = -1245334166690086291L;
	String className;
	
	@XmlElement
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public IsInstance() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		IsInstance isInstance = IsInstance.class.cast(e.getSource());
		setClassName(isInstance.getClassName());
	}
}
