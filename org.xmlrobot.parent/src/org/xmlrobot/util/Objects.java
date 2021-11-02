/**
 * 
 */
package org.xmlrobot.util;

import java.util.Arrays;

/**
 * @author joan
 *
 */
public class Objects {

    public static Object[] resize(Object[] src, int size) {
		if (size < 0) {
			throw new Abort();
		}
		return Arrays.copyOf(src, size);
    }
    public static Object[] append(Object[] src, Object[] str) {
    	int end = src.length;
        src = resize(src, src.length + str.length);
        System.arraycopy(str, 0, src, end, str.length);
        return src;
    }
}
