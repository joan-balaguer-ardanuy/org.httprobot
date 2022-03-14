package org.httprobot;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;

public abstract class Entry<K,V,T extends Control> 
	extends AbstractParent<T> 
		implements DataMapping<K,V> {

	/**
	 * 1596083240511199085L
	 */
	private static final long serialVersionUID = 1596083240511199085L;

	Set<K> inputData;
	Map<K,V> outputData;
	
	K currentInput;
	V currentOutput;
	
	@XmlTransient
	@Override
	public K getKey() {
		return currentInput;
	}
	@Override
	public K setKey(K key) {
		K oldInput = currentInput;
		currentInput = key;
		return oldInput;
	}
	@XmlTransient
	@Override
	public V getValue() {
		return currentOutput;
	}
	@Override
	public V setValue(V value) {
		V oldOutput = currentOutput;
		currentOutput = value;
		return oldOutput;
	}
	
	public Entry() {
		super();
	}
	public Entry(XML message, Class<T> type, Parent parent) {
		super(message, type, parent);
		inputData = new LinkedHashSet<K>();
		outputData = new LinkedHashMap<K,V>();
	}
	
	@Override
	public int size() {
		return outputData.size();
	}
	@Override
	public boolean isEmpty() {
		return outputData.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return inputData.contains(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return outputData.containsValue(value);
	}
	@Override
	public V get(Object key) {
		return outputData.get(key);
	}
	@Override
	public V put(K key, V value) {
		V oldValue = outputData.put(key, value);
		ManagerEvent(new ManagerEventArgs(this, ManagerEventType.FINISHED));
		return oldValue;
	}
	@Override
	public V remove(Object key) {
		return outputData.remove(key);
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(K input : m.keySet()) {
			put(input, m.get(input));
		}
	}
	@Override
	public void clear() {
		inputData.clear();
		outputData.clear();
	}
	@Override
	public Set<K> keySet() {
		return inputData;
	}
	@Override
	public Collection<V> values() {
		return outputData.values();
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		return outputData.entrySet();
	}
	@Override
	public Iterator<K> iterator() {
		return inputData.iterator();
	}
}