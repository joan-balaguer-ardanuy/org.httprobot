package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.httprobot.configuration.Robot;

public class Launcher {

	Robot robot;
	Precursor precursor;
	
	public Launcher(String path) {
		robot = LoadAppConfigFile(path);
		
		//Initialize precursor
		precursor = new Precursor(robot);
		precursor.start();
	}
	
	private Robot LoadAppConfigFile(String path) 
	{
		Robot appConfig = new Robot();
		
		File file = new File(path);	
		InputStream is;
		
		try 
		{
			is = new FileInputStream(file);
			appConfig.unmarshal(is);
			return appConfig;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		
		return null;		
	}
	public static void main(String[] args) {
		new Launcher("./AppConfig.xml");
	}
}
