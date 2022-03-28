package org.httprobot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;
import org.httprobot.BrowserVersion;
import org.httprobot.Constants;
import org.httprobot.unit.Driver;

public class RobotWriter {
	
	static Driver driver;
	
	public RobotWriter() {
		
	}

	public static void main(String[] args) {
		driver = new Driver();
		driver.setDriverPath("/home/joan/lib/WebDriver/bin/geckodriver");
		driver.setDriverProperty(Constants.FIREFOX_DRIVER_PROPERTY);
		driver.setBrowserVersion(BrowserVersion.FIREFOX);
	
		File file = new File("driver.xml");
		WriteAppConfig(file);
	}
	
	public static void WriteAppConfig(File file) 
	{
		try {
			OutputStream os = new FileOutputStream(file);
			driver.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
