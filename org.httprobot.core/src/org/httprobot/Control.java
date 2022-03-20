package org.httprobot;

public interface Control 
	extends Listener<Control>, DataMapping<Data,Object> {
	
	XML getMessage();
	
	void initialize();
	void load();
}