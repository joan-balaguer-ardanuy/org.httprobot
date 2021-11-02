/**
 * 
 */
package org.xmlrobot;

import java.util.Random;

/**
 * The gender types.
 * @author joan
 */
public enum Parity {

	/*
	 * Female
	 * */
	XX(1),
	/*
	 * Male
	 * */
	XY(-1),
	/*
	 * Hermafrodite
	 * */
	YY(0);
	
	/**
	 * Bound for random number generator.
	 */
	private static int BOUND = 1000000;
	
	/**
	 * The parity random generator.
	 */
	private static Random random = new Random();
	
	/**
	 * The value associated to current instance.
	 */
	private final int value;
	
	/**
	 * Returns the value associated to current instance.
	 * @return the value associated to current instance
	 */
	public final int getValue() {
		return this.value;
	}
	
	/**
	 * Parity default class constructor.
	 * @param value the value
	 */
	private Parity(int value) {
		this.value = value;
	}

	/**
	 * Returns the opposite parity corresponding to current instance.
	 * @return the opposite parity corresponding to current instance
	 */
	public Parity get() {
		switch (this) {
		case XX:
			return XY;
		case XY:
			return XX;
		default:
			return YY;
		}
	}
	/**
	 * Returns a random parity.
	 * @return a random parity
	 */
	public static Parity random() {
		if (random.nextInt(BOUND) == 0) {
			return YY;
		}
		else return random.nextBoolean() ? XY : XX;
	}
	/**
	 * Translates boolean to gender
	 * @param bool the boolean to translate
	 * @return the translated gender
	 */
	public static Parity translate(boolean bool) {
		return bool ? XX : XY;
	}
	/**
	 * Translates integer to gender
	 * @param code {@link XML} the string to translate
	 * @return the translated gender
	 */
	public static Parity translate(int value) {
		Parity result = null;
		Parity[] values = Parity.values();
		
		for(Parity gender : values) {
			if(gender.value == value) {
				result = gender;
			}
		}
		return result;
	}
}