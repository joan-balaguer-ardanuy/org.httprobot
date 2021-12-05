package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.httprobot.config.AppConfig;

public class Launcher {

	AppConfig appConfig;
	Precursor precursor;
	
	public Launcher(String path) {
		appConfig = LoadAppConfigFile(path);
		
		//Initialize precursor
		precursor = new Precursor(appConfig);
		precursor.start();
	}
	
	private AppConfig LoadAppConfigFile(String path) 
	{
		AppConfig appConfig = new AppConfig();
		
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
}
