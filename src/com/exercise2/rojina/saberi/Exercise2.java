/*
 * lab 6 ex 2
 * this is the small containskey test
 * the point here is to show null value and missing key are not the same thing
 * i used unsortedtablemap because this exercise only needed a simple map check
 */
package com.exercise2.rojina.saberi;

import com.exercise1.rojina.saberi.UnsortedTableMap;

public class Exercise2 {
  public static void main(String[] args) {

	    // name: Rojina Saberi
	    // student number: 30153334

    UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();

    map.put(10, "A");
    map.put(20, null);

    System.out.println(map.containsKey(10)); // true
    System.out.println(map.containsKey(20)); // true
    System.out.println(map.containsKey(30)); // false
  }
}
