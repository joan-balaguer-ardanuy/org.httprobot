package org.httprobot;

import java.util.Map;

public interface DataMapping<K,V> extends Map.Entry<K,V>, Map<K,V>, Iterable<K> {

	@Override
	K getKey();
	K setKey(K key);
	
	@Override
	V getValue();
	@Override
	V setValue(V value);
}
