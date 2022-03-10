package org.httprobot;

import java.util.Iterator;
import java.util.List;

public interface Listener<T> 
	extends Iterator<T>, EventListener {

	// Properties
	T getParent();
	void setParent(T parent);
	List<T> getChildren();
	void setChildren(List<T> children);
	
	boolean hasChildren();
	void addChild(T child);
	void reset();
}