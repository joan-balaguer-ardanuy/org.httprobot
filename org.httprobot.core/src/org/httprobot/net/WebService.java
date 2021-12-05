package org.httprobot.net;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.httprobot.configuration.Robot;

public class WebService
	extends Service 
		implements MessageService {
	
	@Override
	public Robot getConfiguration() {
		Robot message = this.getPort(MessageService.class).getConfiguration();
		return message;
	}
	
	public WebService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}
}