/*
 * lab 6 ex 1
 * this file is the base hash map logic
 * the compression and resizing ideas are handled here so child classes can focus on buckets
 * abstract class is used because chain hash and probe hash share a lot but not everything
 */
package com.exercise1.rojina.saberi;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
  protected int n = 0;
  protected int capacity;
  private int prime;
  private long scale, shift;

  // added this so user can control max load factor instead of fixed 0.5
  private double maxLoadFactor;

  // new constructor with load factor
  public AbstractHashMap(int cap, int p, double loadFactor) {
    prime = p;
    capacity = cap;
    maxLoadFactor = loadFactor;
    Random rand = new Random();
    scale = rand.nextInt(prime-1) + 1;
    shift = rand.nextInt(prime);
    createTable();
  }

  // constructor with default prime
  public AbstractHashMap(int cap, double loadFactor) {
    this(cap, 109345121, loadFactor);
  }

  // constructor with default capacity
  public AbstractHashMap(double loadFactor) {
    this(17, 109345121, loadFactor);
  }

  // default constructor keeps original behavior (0.5 load factor)
  public AbstractHashMap() {
    this(17, 109345121, 0.5);
  }

  @Override
  public int size() { return n; }

  @Override
  public V get(K key) { return bucketGet(hashValue(key), key); }

  @Override
  public V remove(K key) { return bucketRemove(hashValue(key), key); }

  @Override
  public V put(K key, V value) {
    V answer = bucketPut(hashValue(key), key, value);

    // changed this from fixed 0.5 to user defined load factor
    if ((double) n / capacity > maxLoadFactor)
      resize(2 * capacity - 1);

    return answer;
  }

  private int hashValue(K key) {
    return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
  }

  private void resize(int newCap) {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
    for (Entry<K,V> e : entrySet())
      buffer.add(e);
    capacity = newCap;
    createTable();
    n = 0;
    for (Entry<K,V> e : buffer)
      put(e.getKey(), e.getValue());
  }

  protected abstract void createTable();
  protected abstract V bucketGet(int h, K k);
  protected abstract V bucketPut(int h, K k, V v);
  protected abstract V bucketRemove(int h, K k);
}
