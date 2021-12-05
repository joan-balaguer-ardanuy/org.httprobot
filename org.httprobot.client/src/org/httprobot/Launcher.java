package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.httprobot.configuration.Application;

public class Launcher {

	Application appConfig;
	Precursor precursor;
	
	public Launcher(String path) {
		appConfig = LoadAppConfigFile(path);
		
		//Initialize precursor
		precursor = new Precursor(appConfig);
		precursor.start();
	}
	
	private Application LoadAppConfigFile(String path) 
	{
		Application appConfig = new Application();
		
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
