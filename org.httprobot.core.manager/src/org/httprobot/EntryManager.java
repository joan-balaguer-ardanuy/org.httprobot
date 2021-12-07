package org.httprobot;

import org.httprobot.event.ManagerEventArgs;

public abstract class EntryManager<K extends XML,V,T extends Control<? extends XML>>
	extends Manager<T> 
		implements java.util.Map.Entry<K,V> {

	/**
	 * 5788898149095657921L
	 */
	private static final long serialVersionUID = 5788898149095657921L;

	private K key;
	private V value;
	
	@Override
	public K getKey() {
		return key;
	}
	public K setKey(K key) {
		K oldKey = this.key;
		this.key = key;
		return oldKey;
	}
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