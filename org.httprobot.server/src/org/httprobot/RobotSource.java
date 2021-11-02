package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.httprobot.config.Configuration;
import org.httprobot.net.MessageService;

@WebService(endpointInterface = "org.httprobot.net.MessageService")
public class RobotSource implements MessageService {

	Configuration configuration;
	
	public RobotSource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	void LoadConfigFile(String path) 
	{
		configuration = new Configuration();
		configuration.setPath(path);
		
		File file = new File(path);		
		InputStream is;
		
		try 
		{
			is = new FileInputStream(file);
			configuration.unmarshal(is);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		RobotSource robotSource = new RobotSource();
		
		Endpoint.publish("http://localhost:8888/ws/server", robotSource);
		System.out.println("Service is published!\n");
	}
}
