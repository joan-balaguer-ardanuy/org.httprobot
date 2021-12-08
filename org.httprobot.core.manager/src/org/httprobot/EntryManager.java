package org.httprobot;

import org.httprobot.event.ManagerEventArgs;

public abstract class EntryManager<K,V,T extends Control<?>>
	extends Manager<T>
		implements java.util.Map.Entry<K,V> {

	/**
	 * 5788898149095657921L
	 */
	private static final long serialVersionUID = 5788898149095657921L;

	private V value;
	
	@Override
	public abstract K getKey();
	@Override
	public V getValue() {
		return value;
	}
	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}

	public EntryManager() {

	}
	public EntryManager(XML message, Class<T> type, ManagerListener parent) {
		super(message, type, parent);
	}
}