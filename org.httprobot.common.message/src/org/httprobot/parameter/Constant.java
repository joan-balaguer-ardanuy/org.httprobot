package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.XML;

/**
 * Constant XML message class. This element maps from key to value 
 * and from value to key. It's {@link java.util.Map.Entry}.
 * @author joan
 *
 */
@XmlRootElement
public final class Constant extends XML
	implements java.util.Map.Entry<String,String> {

	/**
	 * 3549203523654599730L
	 */
	private static final long serialVersionUID = 3549203523654599730L;

	/**
	 * The key to be mapped.
	 */
	String key;
	/**
	 * The value to be mapped.
	 */
	String value;
	
	@XmlElement
	public String getKey() {
		return key;
	}
	/**
	 * Replaces the key corresponding to this entry with the specified key (optional 
	 * operation). (Writes through to the map.) The behavior of this call is undefined 
	 * if the mapping has already been removed from the map (by the iterator's remove 
	 * operation).
	 * @param key {@link String} new key to be stored in this entry
	 * @return the old mapping {@link String} key.
	 */
	public String setKey(String key) {
		String oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	@XmlElement
	public String getValue() {
		return value;
	}
	public String setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	/**
	 * {@link Constant} default class constructor.
	 * */
	public Constant() {
		super();
	}
}