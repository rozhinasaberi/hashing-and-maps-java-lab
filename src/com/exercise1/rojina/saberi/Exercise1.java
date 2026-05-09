/*
 * lab 6 ex 1
 * this is the main file i run for the hashing experiment
 * it inserts lots of random keys and times inserts and searches
 * load factor is the main concept here because it affects resizing and collisions
 */
package com.exercise1.rojina.saberi;

import java.util.Random;

public class Exercise1 {
  public static void main(String[] args) {

    // name: Rojina Saberi
    // student number: 30153334

    // testing chainhashmap with different max load factors

    int numberOfKeys = 20000;
    int searchCount = 5000;
    int keyRange = 1000000;

    double[] loadFactors = {0.5, 0.7, 0.9};

    Random rand = new Random(254);
    int[] generatedKeys = new int[numberOfKeys];
    int[] searchIndexes = new int[searchCount];

    for (int i = 0; i < numberOfKeys; i++) {
      generatedKeys[i] = rand.nextInt(keyRange);
    }

    for (int i = 0; i < searchCount; i++) {
      searchIndexes[i] = rand.nextInt(numberOfKeys);
    }

    System.out.println("exercise 1 test");
    System.out.println();

    for (double loadFactor : loadFactors) {

      // creating a new chainhashmap with selected load factor
      ChainHashMap<Integer, Integer> map = new ChainHashMap<>(17, loadFactor);

      int[] insertedKeys = new int[numberOfKeys];

      // timing insertions
      long insertStart = System.nanoTime();

      for (int i = 0; i < numberOfKeys; i++) {
        int key = generatedKeys[i];
        insertedKeys[i] = key;
        map.put(key, i);
      }

      long insertEnd = System.nanoTime();

      // timing searches using keys that were inserted
      long searchStart = System.nanoTime();

      for (int i = 0; i < searchCount; i++) {
        int index = searchIndexes[i];
        map.get(insertedKeys[index]);
      }

      long searchEnd = System.nanoTime();

      double insertTimeMs = (insertEnd - insertStart) / 1_000_000.0;
      double searchTimeMs = (searchEnd - searchStart) / 1_000_000.0;

      System.out.println("load factor: " + loadFactor);
      System.out.println("map size: " + map.size());
      System.out.println("insert time: " + insertTimeMs + " ms");
      System.out.println("search time: " + searchTimeMs + " ms");
      System.out.println();
    }

    System.out.println("higher load factors may save space but can increase collisions");
    System.out.println("lower load factors may resize sooner and can improve access time");
  }
}
