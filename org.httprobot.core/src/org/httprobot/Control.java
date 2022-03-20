package org.httprobot;

public interface Control 
	extends Listener<Control>, DataMapping<Data,Object> {
	void initialize();
	void load();
}