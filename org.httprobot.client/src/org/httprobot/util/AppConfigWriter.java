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
import org.httprobot.configuration.Application;
import org.httprobot.configuration.ServiceConnection;

public class AppConfigWriter {
	
	static Application app_config;
	
	public AppConfigWriter() {
		
	}

	public static void main(String[] args) {
		try {
			app_config = new Application();
			
			app_config.setPath("AppConfig.xml");
			
			URL url = new URL("http://localhost:8888/ws/server?wsdl");
			QName qname = new QName("http://org.httprobot/", "RobotSourceService");
			
			ServiceConnection serviceConnection = new ServiceConnection();
			serviceConnection.setUrl(url);
			serviceConnection.setQName(qname);
			
			app_config.setServiceConnection(serviceConnection);
			app_config.setDriverPath("/home/joan/lib/WebDriver/bin/geckodriver");
			app_config.setDriverProperty("webdriver.gecko.driver");
			app_config.setBrowserVersion(BrowserVersion.FIREFOX);
		
			File file = new File("AppConfig.xml");
			WriteAppConfig(file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteAppConfig(File file) 
	{
		try {
			OutputStream os = new FileOutputStream(file);
			app_config.marshal(os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
