package org.httprobot;

public class Application {
	
	static Launcher launcher;
	
	public static void main(String[] args) {
		launcher = new Launcher("./AppConfig.xml");
	}
}