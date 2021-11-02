package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.Concat;
import org.httprobot.placeholder.string.Substring;
import org.httprobot.placeholder.string.TryParse;

public abstract class AbstractPlaceholder extends XML {

	/**
	 * 3345969046526629498L
	 */
	private static final long serialVersionUID = 3345969046526629498L;

	Contains contains;
	Equals equals;
	Trim trim;
	Replace replace;
	Concat split;
	Substring substring;
	TryParse tryParse;

	@XmlElement
	public Contains getContains() {
		return contains;
	}
	public void setContains(Contains contains) {
		this.contains = contains;
	}

	@XmlElement
	public Equals getEquals() {
		return equals;
	}
	public void setEquals(Equals equals) {
		this.equals = equals;
	}

	@XmlElement
	public Trim getTrim() {
		return trim;
	}
	public void setTrim(Trim remove) {
		this.trim = remove;
	}

	@XmlElement
	public Replace getReplace() {
		return replace;
	}
	public void setReplace(Replace replace) {
		this.replace = replace;
	}

	@XmlElement
	public Concat getSplit() {
		return split;
	}
	public void setSplit(Concat split) {
		this.split = split;
	}

	@XmlElement
	public Substring getSubstring() {
		return substring;
	}
	public void setSubstring(Substring substring) {
		this.substring = substring;
	}

	@XmlElement
	public TryParse getTryParse() {
		return tryParse;
	}
	public void setTryParse(TryParse tryParse) {
		this.tryParse = tryParse;
	}

	public AbstractPlaceholder() {
		super();
	}
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		AbstractPlaceholder placeholder = AbstractPlaceholder.class.cast(e.getSource());
		
		setContains(placeholder.getContains());
		setEquals(placeholder.getEquals());
		setTrim(placeholder.getTrim());
		setReplace(placeholder.getReplace());
		setSplit(placeholder.getSplit());
		setSubstring(placeholder.getSubstring());
		setTryParse(placeholder.getTryParse());
	}
}