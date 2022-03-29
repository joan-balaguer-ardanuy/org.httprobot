package org.httprobot.configuration;

import java.net.URL;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import org.httprobot.XML;
import org.httprobot.configuration.adapter.ServiceQNameAdapter;
import org.httprobot.configuration.adapter.ServiceUrlAdapter;

/**
 * Service connection XML configuration message class.
 * Inherits {@link AbstractConfiguration} and encapsulates a
 * {@link QName} and {@link URL} properties. It contains all
 * necessary data to connect to the WebService server wich will
 * provide the {@link Source} configuration XML message.
 * @author joan
 *
 */
@XmlRootElement
public final class ServiceConnection extends XML {

	/**
	 * 355219426287742894L
	 */
	private static final long serialVersionUID = 355219426287742894L;

	/**
	 * The QName property.
	 */
	private QName qName;
	/**
	 * The URL propery
	 */
	private URL url;
	
	/**
	 * Returns the {@link QName} property.
	 * See {@link ServiceQNameAdapter} to view how this class
	 * is marshalled and unmarshalled by JAXB framework.
	 * @return the {@link QName} property.
	 */
	@XmlJavaTypeAdapter(value = ServiceQNameAdapter.class)
	public QName getQName() {
		return qName;
	}
	/**
	 * Sets the {@link QName} property.
	 * @param qName {@link QName} property to be set.
	 */
	public void setQName(QName qName) {
		this.qName = qName;
	}
	/**
	 * Returns the {@link URL} property-
	 * See {@link ServiceUrlAdapter} to view how this class
	 * is marshalled and unmarshalled by JAXB framework.
	 * @return
	 */
	@XmlJavaTypeAdapter(value = ServiceUrlAdapter.class)
	public URL getURL() {
		return url;
	}
	/**
	 * Sets the {@link URL} property.
	 * @param qName {@link URL} property to be set.
	 */
	public void setURL(URL url) {
		this.url = url;
	}

	/**
	 * {@link ServiceConnection} default class constructor.
	 */
	public ServiceConnection() {
		super();
	}
}
