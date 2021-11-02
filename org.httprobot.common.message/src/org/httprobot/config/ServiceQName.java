package org.httprobot.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.AbstractConfig;
import org.httprobot.event.MessageEventArgs;

@XmlRootElement
public final class ServiceQName extends AbstractConfig {

	/**
	 * 5243372154742756420Ls
	 */
	private static final long serialVersionUID = 5243372154742756420L;

	String namespaceURI;
	String localPart;
	String prefix;
	
	@XmlAttribute
	public String getNamespaceURI() {
		return namespaceURI;
	}
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	@XmlAttribute
	public String getLocalPart() {
		return localPart;
	}
	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	@XmlAttribute
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public ServiceQName() {
		super();
	}
	
	@Override
	public void OnMessageUnmarshalled(MessageEventArgs e) {
		super.OnMessageUnmarshalled(e);
		
		ServiceQName serviceQName = ServiceQName.class.cast(e.getSource());
		setNamespaceURI(serviceQName.getNamespaceURI());
		setLocalPart(serviceQName.getLocalPart());
		setPrefix(serviceQName.getPrefix());
	}
}
