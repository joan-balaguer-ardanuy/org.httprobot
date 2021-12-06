package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.httprobot.configuration.Selenium;
import org.httprobot.configuration.ServiceConnection;

/**
 * The robot launcher application.
 * The launcher encapsulates the {@link Selenium} configuration XML message
 * and the {@link Precursor} application that is the root XML message.
 * @author joan
 *
 */
public class Launcher {

	/**
	 * The {@link ServiceConnection} data.
	 */
	ServiceConnection serviceConnection;
	/**
	 * The {@link Selenium} configuration XML message
	 */
	Selenium selenium;
	/**
	 * The {@link Precursor} XML message
	 */
	Precursor precursor;
	
	/**
	 * {@link Launcher} default class constructor.
	 * @param robotPath the path of {@link Selenium} XML message file.
	 */
	public Launcher(String robotPath, String serviceConnectionPath) {
		// Load robot configuration
		selenium = loadSeleniumFile(robotPath);
		// Load service connection data
		serviceConnection = loadServiceConnectionFile(serviceConnectionPath);
		// Initialize precursor
		precursor = new Precursor(selenium, serviceConnection);
		// Start precursor
		precursor.start();
	}
	
	/**
	 * Loads the {@link Selenium} configuration XML message.
	 * @param path the path where file is located
	 * @return the {@link Selenium} configuration XML message
	 */
	private Selenium loadSeleniumFile(String path) {
		Selenium selenium = new Selenium();
		
		File file = new File(path);
		InputStream is;

		try {
			is = new FileInputStream(file);
			selenium.unmarshal(is);
			return selenium;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
	private ServiceConnection loadServiceConnectionFile(String path) {
		ServiceConnection serviceConnection = new ServiceConnection();

		File file = new File(path);
		InputStream is;

		try {
			is = new FileInputStream(file);
			serviceConnection.unmarshal(is);
			return serviceConnection;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * Launches org.httprobot
	 * @param args {@code null}
	 */
	public static void main(String[] args) {
		new Launcher("./selenium.xml", "./serviceConnection.xml");
	}
}
