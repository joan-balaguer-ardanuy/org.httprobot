package org.httprobot.data.document;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.httprobot.DataMapping;

public class InputLibrary<K>
	implements DataMapping<K, InputDocument> {
	
	Map<K,InputDocument> inputDocuments;
	K currentKey;
	InputDocument currentValue;
	
	public Map<K, InputDocument> getInputDocuments() {
		return inputDocuments;
	}

	public void setInputDocuments(Map<K, InputDocument> inputDocuments) {
		this.inputDocuments = inputDocuments;
	}

	public InputLibrary() {
		inputDocuments = new LinkedHashMap<K,InputDocument>();
	}

	@Override
	public int size() {
		return inputDocuments.size();
	}

	@Override
	public boolean isEmpty() {
		return inputDocuments.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return inputDocuments.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return inputDocuments.containsValue(value);
	}

	@Override
	public InputDocument get(Object key) {
		return inputDocuments.get(key);
	}

	@Override
	public InputDocument put(K key, InputDocument value) {
		setKey(key);
		setValue(value);
		return inputDocuments.put(key, value);
	}

	@Override
	public InputDocument remove(Object key) {
		return inputDocuments.remove(key);
	}
	@Override
	public void putAll(Map<? extends K, ? extends InputDocument> m) {
		inputDocuments.putAll(m);
	}

	@Override
	public void clear() {
		inputDocuments.clear();
	}

	@Override
	public Set<K> keySet() {
		return inputDocuments.keySet();
	}

	@Override
	public Collection<InputDocument> values() {
		return inputDocuments.values();
	}

	@Override
	public Set<Entry<K, InputDocument>> entrySet() {
		return inputDocuments.entrySet();
	}

	@Override
	public Iterator<K> iterator() {
		return inputDocuments.keySet().iterator();
	}

	@Override
	public K getKey() {
		return currentKey;
	}

	@Override
	public K setKey(K key) {
		K oldKey = currentKey;
		currentKey = key;
		return oldKey;
	}

	@Override
	public InputDocument getValue() {
		return currentValue;
	}

	@Override
	public InputDocument setValue(InputDocument value) {
		InputDocument oldValue = currentValue;
		currentValue = value;
		return oldValue;
	}
}