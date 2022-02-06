package org.httprobot;

import java.util.Map;

/**
 * The data mapping interface.
 * All implementing instances will map some class of data.
 * It is {@link java.util.Map.Entry}, {@link java.util.Map} and {@link Iterable} of their keys.
 * 
 * @author joan
 *
 * @param <K> is the key.
 * @param <V> is the value.
 */
public interface DataMapping<K,V> extends Map.Entry<K,V>, Map<K,V>, Iterable<K> {

	@Override
	K getKey();
	
	/**
     * Replaces the key corresponding to this entry with the specified
     * key (optional operation).  (Writes through to the map.)  The
     * behavior of this call is undefined if the mapping has already been
     * removed from the map (by the iterator's <tt>remove</tt> operation).
     *
     * @param key new key to be stored in this entry
     * @return old key corresponding to the entry
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *         is not supported by the backing map
     * @throws ClassCastException if the class of the specified key
     *         prevents it from being stored in the backing map
     * @throws NullPointerException if the backing map does not permit
     *         null keys, and the specified key is null
     * @throws IllegalArgumentException if some property of this key
     *         prevents it from being stored in the backing map
     * @throws IllegalStateException implementations may, but are not
     *         required to, throw this exception if the entry has been
     *         removed from the backing map.
     */
	K setKey(K key);
	
	@Override
	V getValue();
	@Override
	V setValue(V value);
}
