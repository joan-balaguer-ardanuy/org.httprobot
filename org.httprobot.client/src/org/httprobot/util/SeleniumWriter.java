package org.httprobot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.JAXBException;
import org.httprobot.BrowserVersion;
import org.httprobot.configuration.Selenium;

public class SeleniumWriter {
	
	static Selenium selenium;
	
	public SeleniumWriter() {
		
	}

	public static void main(String[] args) {
		selenium = new Selenium();
		selenium.setDriverPath("/home/joan/lib/WebDriver/bin/geckodriver");
		selenium.setDriverProperty("webdriver.gecko.driver");
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
