package org.httprobot.data.field;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.httprobot.DataMapping;

public class FieldLibrary<K>
	implements DataMapping<K,InputField> {

	/**
	 * The input fields.
	 */
	Map<K, InputField> inputFields;
	K currentKey;
	InputField currentValue;

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
	public InputField getValue() {
		return currentValue;
	}
	@Override
	public InputField setValue(InputField value) {
		InputField oldValue = currentValue;
		currentValue = value;
		return oldValue;
	}
	
	public FieldLibrary() {
		inputFields = new LinkedHashMap<K, InputField>();
	}

	@Override
	public int size() {
		return inputFields.size();
	}
	@Override
	public boolean isEmpty() {
		return inputFields.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return inputFields.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return inputFields.containsValue(value);
	}
	public InputField getByUUID(UUID uuid)
	{
		for(K key : this.inputFields.keySet())
		{
			InputField inputField = this.inputFields.get(key);
			
			if(inputField.getFieldRef().getUuid().equals(uuid))
			{
				return inputField.deepInputCopy();
			}
		}
		return null;
	}
	@Override
	public InputField get(Object key) {
		return inputFields.get(key);
	}
	@Override
	public InputField put(K key, InputField value) {
		setKey(key);
		setValue(value);
		return inputFields.put(key, value);
	}
	@Override
	public InputField remove(Object key) {
		return inputFields.remove(key);
	}
	@Override
	public void putAll(Map<? extends K, ? extends InputField> m) {
		inputFields.putAll(m);
	}
	@Override
	public void clear() {
		inputFields.clear();
	}
	@Override
	public Set<K> keySet() {
		return inputFields.keySet();
	}
	@Override
	public Collection<InputField> values() {
		return inputFields.values();
	}
	@Override
	public Set<Entry<K, InputField>> entrySet() {
		return inputFields.entrySet();
	}
	@Override
	public Iterator<K> iterator() {
		return inputFields.keySet().iterator();
	}
}