/**
 * 
 */
package org.xmlrobot.util;

/**
 * @author joan
 *
 */
public class Primers {

	public static void main(String[] args) {
		int nombre = 2023;
		
		bucle : while (true) {
			for(int i = 2; i < nombre; i++) {
				if(i != nombre && i != 1) {
					if(nombre % i == 0)
					{
						System.out.println("no és primer: " + i);
						break bucle;
					}	
				}
			}
			System.out.println("és primer");
			break bucle;
		}
	}
}
