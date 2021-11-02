/**
 * 
 */
package org.xmlrobot.util;

/**
 * @author joan
 *
 */
public class Ints {

	public static boolean contains(int[] src, int[] dst) {
    	for(int i = 0; i < src.length; i++) {
    		if(src[i] != dst[i]) {
    			return false;
    		}
    	}
    	return true;
	}
	public static boolean equals(int[] o1, int[] o2) {
        if (o1 == o2) {
            return true;
        }
        else {
            int n = o1.length;
            if (n == o2.length) {
                int i = 0;
                while (n-- != 0) {
                    if (o1[i] != o2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
}
