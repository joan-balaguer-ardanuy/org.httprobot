package org.httprobot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.httprobot.configuration.ServiceConnection;
import org.httprobot.unit.Driver;

/**
 * The robot launcher application.
 * The launcher encapsulates the {@link Driver} configuration XML message
 * and the {@link Root} application that is the root XML message.
 * @author joan
 *
 */
public class Launcher {

	/**
	 * The {@link ServiceConnection} data.
	 */
	ServiceConnection serviceConnection;
	/**
	 * The {@link Root} XML message
	 */
	Root precursor;
	
	/**
	 * {@link Launcher} default class constructor.
	 * @param robotPath the path of {@link Driver} XML message file.
	 */
	public Launcher(String serviceConnectionPath) {
		// Load service connection data
		serviceConnection = loadServiceConnectionFile(serviceConnectionPath);
		// Initialize precursor
		precursor = new Root(serviceConnection);
		// Start precursor
		precursor.start();
	}
	
	/**
	 * Loads the {@link ServiceConnection} configuration XML message.
	 * @param path the path where file is located
	 * @return the {@link ServiceConnection} configuration XML message
	 */
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
		new Launcher("./serviceConnection.xml");
	}
}
