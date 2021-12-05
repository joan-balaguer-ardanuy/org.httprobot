package org.httprobot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.httprobot.BrowserVersion;
import org.httprobot.configuration.Robot;
import org.httprobot.configuration.ServiceConnection;

public class RobotWriter {
	
	static Robot robot;
	
	public RobotWriter() {
		
	}

	public static void main(String[] args) {
		try {
			robot = new Robot();
			
			URL url = new URL("http://localhost:8888/ws/server?wsdl");
			QName qname = new QName("http://org.httprobot/", "RobotSourceService");
			
			ServiceConnection serviceConnection = new ServiceConnection();
			serviceConnection.setURL(url);
			serviceConnection.setQName(qname);
			
			robot.setServiceConnection(serviceConnection);
			robot.setDriverPath("/home/joan/lib/WebDriver/bin/geckodriver");
			robot.setDriverProperty("webdriver.gecko.driver");
			robot.setBrowserVersion(BrowserVersion.FIREFOX);
		
			File file = new File("robot.xml");
			WriteAppConfig(file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteAppConfig(File file) 
	{
		try {
			OutputStream os = new FileOutputStream(file);
			robot.marshal(os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
