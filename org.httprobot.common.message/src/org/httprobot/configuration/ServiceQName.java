package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.Message;

/**
 * Service QName configuration XML message class.
 * It inherits {@link AbstractConfiguration} class and contains 
 * all the necessary data to instance a
 * {@link javax.xml.namespace.QName}. It encapsulates a 
 * namespaceURI, a localPart and a prefix. Look at
 * {@link org.httprobot.configuration.adapter.ServiceQNameAdapter} 
 * to see how this object is marshalled and unmarshalled 
 * by JAXB framework.
 * @author joan
 *
 */
@XmlRootElement
public final class ServiceQName extends Message {

	/**
	 * 5243372154742756420Ls
	 */
	private static final long serialVersionUID = 5243372154742756420L;

	/**
	 * The namespace URI.
	 */
	String namespaceURI;
	/**
	 * The local part.
	 */
	String localPart;
	/**
	 * The prefix.
	 */
	String prefix;
	
	/**
	 * Returns the namespace URI XML attribute.
	 * @return the namespace URI XML attribute.
	 */
	@XmlAttribute
	public String getNamespaceURI() {
		return namespaceURI;
	}
	/** 
	 * Sets the namespace URI XML attribute.
	 * @param namespaceURI the namespace URI XML attribute.
	 */
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	/**
	 * Returns the local part XML attribute.
	 * @return the local part XML attribute.
	 */
	@XmlAttribute
	public String getLocalPart() {
		return localPart;
	}
	/**
	 * Sets the local part XML attribute.
	 * @param localPart the local part XML attribute.
	 */
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	/**
	 * Returns the prefix XML attribute.
	 * @return the prefix XML attribute.
	 */
	@XmlAttribute
	public String getPrefix() {
		return prefix;
	}
	/**
	 * Sets the prefix XML attribute.
	 * @param prefix the prefix XML attribute.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * {@link ServiceQName} default class constructor.
	 */
	public ServiceQName() {
		super();
	}
}
