package org.httprobot.unit;

import java.util.LinkedHashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.httprobot.Unit;
import org.httprobot.event.MessageEventArgs;
import org.httprobot.parameter.BannedWord;
import org.httprobot.parameter.Constant;

@XmlRootElement
public final class Action extends Unit {

	/**
	 * 798890955203009246L
	 */
	private static final long serialVersionUID = 798890955203009246L;

	Boolean strictMode;
	String httpAddress;
	String method;
	WebLoader webLoader;
	LinkedHashSet<Constant> constant;
	LinkedHashSet<BannedWord> bannedWord;
	
	@XmlAttribute
	public Boolean getStrictMode() {
		return strictMode;
	}
	public void setStrictMode(Boolean strictMode) {
		this.strictMode = strictMode;
	}
	@XmlElement
	public String getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}
	@XmlElement
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@XmlElement
	public WebLoader getWebLoader() {
		return webLoader;
	}
	public void setWebLoader(WebLoader webLoader) {
		this.webLoader = webLoader;
	}
	@XmlElement
	public LinkedHashSet<Constant> getConstant() {
		return constant;
	}
	public void setConstant(LinkedHashSet<Constant> constant) {
		this.constant = constant;
	}
	@XmlElement
	public LinkedHashSet<BannedWord> getBannedWord() {
		return bannedWord;
	}
	public void setBannedWord(LinkedHashSet<BannedWord> bannedWord) {
		this.bannedWord = bannedWord;
	}
	
	public Action() {
		super();
		constant = new LinkedHashSet<Constant>();
		bannedWord = new LinkedHashSet<BannedWord>();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		Action action = Action.class.cast(e.getSource());
		setStrictMode(action.getStrictMode());
		setHttpAddress(action.getHttpAddress());
		setMethod(action.getMethod());
		setWebLoader(action.getWebLoader());
		setConstant(action.getConstant());
		setBannedWord(action.getBannedWord());
	}
}
