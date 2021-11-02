package org.httprobot;

import javax.xml.bind.annotation.XmlElement;

import org.httprobot.event.MessageEventArgs;
import org.httprobot.placeholder.string.Contains;
import org.httprobot.placeholder.string.EndsWith;
import org.httprobot.placeholder.string.Equals;
import org.httprobot.placeholder.string.Trim;
import org.httprobot.placeholder.string.Replace;
import org.httprobot.placeholder.string.StartsWith;
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
	Concat concat;
	Substring substring;
	TryParse tryParse;
	StartsWith startsWith;
	EndsWith endsWith;

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
	public Concat getConcat() {
		return concat;
	}
	public void setConcat(Concat concat) {
		this.concat = concat;
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
	
	@XmlElement
	public StartsWith getStartsWith() {
		return startsWith;
	}
	public void setStartsWith(StartsWith startsWith) {
		this.startsWith = startsWith;
	}
	
	@XmlElement
	public EndsWith getEndsWith() {
		return endsWith;
	}
	public void setEndsWith(EndsWith endsWith) {
		this.endsWith = endsWith;
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
		setConcat(placeholder.getConcat());
		setSubstring(placeholder.getSubstring());
		setTryParse(placeholder.getTryParse());
	}
}