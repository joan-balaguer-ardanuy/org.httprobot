package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Message;

/**
 * Constant XML message class. This element maps one {@link String} key
 * with one string value. This {@link Constant} message will be present in
 * all the parent XML message being processed.
 * @author user
 *
 */
@XmlRootElement
public final class Constant extends Message
	implements java.util.Map.Entry<String,String> {

	/**
	 * 3549203523654599730L
	 */
	private static final long serialVersionUID = 3549203523654599730L;

	String key;
	String value;
	
	/**
	 * The {@link String} key to be mapped.
	 */
	@XmlElement
	public String getKey() {
		return key;
	}
	/**
	 * Sets the {@link String} key to be mapped.
	 * @param key {@link String} the key to be mapped
	 * @return the old mapping {@link String} key.
	 */
	public String setKey(String key) {
		String oldKey = this.key;
		this.key = key;
		return oldKey;
	}
	/**
	 * The {@link String} value to be mapped.
	 */
	@XmlElement
	public String getValue() {
		return value;
	}
	/**
	 * Sets the {@link String} value to be mapped.
	 * @param value {@link String} the value.
	 * @return the old mapping {@link String} value
	 */
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