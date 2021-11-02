package org.httprobot;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.httprobot.config.Configuration;
import org.httprobot.net.MessageService;

@WebService(endpointInterface = "org.httprobot.net.MessageService")
public class RobotSource implements MessageService {

	public RobotSource() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) 
	{
		RobotSource robotSource = new RobotSource();
		
		Endpoint.publish("http://localhost:8888/ws/server", robotSource);
		System.out.println("Service is published!\n");
	}

	@Override
	public Configuration getConfiguration() {
		return null;
	}
}
