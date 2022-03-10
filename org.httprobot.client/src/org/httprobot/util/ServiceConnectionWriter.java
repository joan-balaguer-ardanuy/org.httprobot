package org.httprobot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.httprobot.configuration.ServiceConnection;

public class ServiceConnectionWriter {

	static ServiceConnection serviceConnection;
	
	public ServiceConnectionWriter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		serviceConnection = new ServiceConnection();
		try {
			URL url = new URL("http://localhost:8888/ws/server?wsdl");
			QName qname = new QName("http://org.httprobot/", "RobotSourceService");
			
			serviceConnection = new ServiceConnection();
			serviceConnection.setURL(url);
			serviceConnection.setQName(qname);
			
			File file = new File("serviceConnection.xml");
			WriteServiceConnection(file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public static void WriteServiceConnection(File file) 
	{
		try {
			OutputStream os = new FileOutputStream(file);
			serviceConnection.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
