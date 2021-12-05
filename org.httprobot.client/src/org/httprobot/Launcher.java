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
		robot = LoadRobotFile(path);
		
		//Initialize precursor
		precursor = new Precursor(robot);
		precursor.start();
	}
	
	private Robot LoadRobotFile(String path) 
	{
		Robot robot = new Robot();
		
		File file = new File(path);	
		InputStream is;
		
		try 
		{
			is = new FileInputStream(file);
			robot.unmarshal(is);
			return robot;
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
		new Launcher("./robot.xml");
	}
}
