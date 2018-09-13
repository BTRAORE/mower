/**
 * 
 */
package com.mom.webapp.utils;

/**
 * @author Brehima
 *
 */
public class ArraysUtil {
	public static boolean isNotEmpty(Object [] array){
		return (array != null && array.length != 0);
	}
	public static boolean isNullOrEmpty(Object [] array) {
		return !isNotEmpty(array);
	}
}
