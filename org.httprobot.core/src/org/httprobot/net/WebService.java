package org.httprobot.net;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.httprobot.configuration.Source;

public class WebService
	extends Service 
		implements MessageService {
	
	@Override
	public Source getSource() {
		Source message = this.getPort(MessageService.class).getSource();
		return message;
	}
	
	public WebService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}
}