package org.httprobot.configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfiguration;
import org.httprobot.event.MessageEventArgs;

/**
 * Service URL configuration XML message class.
 * It inherits {@link AbstractConfiguration} class and contains 
 * all the necessary data to instance a
 * {@link java.net.URL}. It encapsulates an
 * URL, a protocol, a host, a port and a file path. Look at
 * {@link org.httprobot.configuration.adapter.ServiceUrlAdapter}
 * to see how this object is marshalled and unmarshalled
 * by JAXB framework.
 * @author joan
 *
 */
@XmlRootElement
public final class ServiceUrl
	extends AbstractConfiguration {

	/**
	 * 8383249175803013880L
	 */
	private static final long serialVersionUID = 8383249175803013880L;
	
	/**
	 * The spec
	 */
	String url;
	/**
     * The protocol to use (ftp, http, nntp, ... etc.).
     */
	String protocol;
	/**
     * The host name to connect to.
     */
	String host;
	/**
     * The protocol port to connect to.
     */
	Integer port;
    /**
     * The specified file name on that host. {@code file} is
     * defined as {@code path[?query]}
     */
	String file;
	
	/**
	 * Returns the spec.
	 * @return the spec.
	 */
	@XmlAttribute
	public String getUrl() {
		return url;
	}
	/**
	 * Sets the spec
	 * @param url the spec
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Returns the protocol to use (ftp, http, nntp, ... etc.).
	 * @return
	 */
	@XmlAttribute
	public String getProtocol() {
		return protocol;
	}
	/**
	 * Sets the protocol to use (ftp, http, nntp, ... etc.).
	 * @param protocol the protocol to use (ftp, http, nntp, ... etc.).
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * Returns the host name to connect to.
	 * @return the host name to connect to.
	 */
	@XmlAttribute
	public String getHost() {
		return host;
	}
	/**
	 * Sets the host name to connect to.
	 * @param host the host name to connect to.
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * Returns the protocol port to connect to.
	 * @return the protocol port to connect to.
	 */
	@XmlAttribute
	public Integer getPort() {
		return port;
	}
	/**
	 * Sets the protocol port to connect to.
	 * @param port the protocol port to connect to.
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	
	/**
	 * Returns the specified file name on that host. {@code file} is
     * defined as {@code path[?query]}
	 * @return the specified file name on that host. {@code file} is
     * defined as {@code path[?query]}
	 */
	@XmlAttribute
	public String getFile() {
		return file;
	}
	/**
	 * Sets the specified file name on that host. {@code file} is
     * defined as {@code path[?query]}
	 * @param file the specified file name on that host. {@code file} is
     * defined as {@code path[?query]}
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * {@link ServiceUrl} default class constructor.
	 */
	public ServiceUrl() {
		super();
	}

	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		// Cast source
		ServiceUrl serviceUrl = ServiceUrl.class.cast(e.getSource());
		// Set XML attributes
		setUrl(serviceUrl.getUrl());
		setProtocol(serviceUrl.getProtocol());
		setHost(serviceUrl.getHost());
		setPort(serviceUrl.getPort());
		setFile(serviceUrl.getFile());
	}
}
