package org.httprobot.config.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

import org.httprobot.config.ServiceQName;

public class ServiceQNameAdapter extends XmlAdapter<ServiceQName, QName> {

	public ServiceQNameAdapter() {
	}
	
	@Override
	public QName unmarshal(ServiceQName v) throws Exception {
		
		if(v.getPrefix() != null && v.getNamespaceURI() != null && v.getLocalPart() != null)
		{
			return new QName(v.getNamespaceURI(), v.getLocalPart(), v.getPrefix());	
		}
		else if(v.getNamespaceURI() != null && v.getLocalPart() != null)
		{
			return new QName(v.getNamespaceURI(), v.getLocalPart());
		}
		else
		{
			throw new Exception();
		}	
	}

	@Override
	public ServiceQName marshal(QName v) throws Exception {
		ServiceQName serviceQName = new ServiceQName();
		serviceQName.setNamespaceURI(v.getNamespaceURI());
		serviceQName.setLocalPart(v.getLocalPart());
		serviceQName.setPrefix(v.getPrefix());
		return serviceQName;
	}
}
