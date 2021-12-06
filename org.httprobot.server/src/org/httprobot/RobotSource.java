package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.httprobot.configuration.Source;
import org.httprobot.net.MessageService;

@WebService(endpointInterface = "org.httprobot.net.MessageService")
public class RobotSource implements MessageService {

	Source source;
	
	public RobotSource() {
	}

	@Override
	public Source getSource() {
		return source;
	}
	
	void LoadConfigFile(String path) {
		source = new Source();

		File file = new File(path);
		InputStream is;

		try {
			is = new FileInputStream(file);
			source.unmarshal(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
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
