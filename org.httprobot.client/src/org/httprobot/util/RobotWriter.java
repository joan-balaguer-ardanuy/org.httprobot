package org.httprobot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;
import org.httprobot.BrowserVersion;
import org.httprobot.Constants;
import org.httprobot.configuration.Driver;

public class RobotWriter {
	
	static Driver selenium;
	
	public RobotWriter() {
		
	}

	public static void main(String[] args) {
		selenium = new Driver();
		selenium.setDriverPath("/home/joan/lib/WebDriver/bin/geckodriver");
		selenium.setDriverProperty(Constants.FIREFOX_DRIVER_PROPERTY);
		selenium.setBrowserVersion(BrowserVersion.FIREFOX);
	
		File file = new File("selenium.xml");
		WriteAppConfig(file);
	}
	
	public static void WriteAppConfig(File file) 
	{
		try {
			OutputStream os = new FileOutputStream(file);
			selenium.marshal(os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
