package org.httprobot.parameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractParameter;

@XmlRootElement
public final class Constant extends AbstractParameter {

	/**
	 * 3549203523654599730L
	 */
	private static final long serialVersionUID = 3549203523654599730L;

	@Override
	@XmlElement
	public String getKey() {
		return super.getKey();
	}
	@Override
	public String setKey(String key) {
		if(key.startsWith("[@") && key.endsWith("]")) {
			return super.setKey(key);
		} else {
			throw new Error("org.httprobot.paramater.Constant: bad key format.");
		}
	}

	public Constant() {
		super();
	}

}
