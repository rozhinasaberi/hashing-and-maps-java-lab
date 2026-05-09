/*
 * lab 6 ex 1
 * this file is the separate chaining hash map
 * each bucket can hold its own small map, which helps when collisions happen
 * chaining is used here because different keys can land in same bucket and still be stored
 */
package com.exercise1.rojina.saberi;

import java.util.ArrayList;

public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
  private UnsortedTableMap<K,V>[] table;

  // updated constructors to support load factor

  public ChainHashMap() {
    super();
  }

  public ChainHashMap(double loadFactor) {
    super(loadFactor);
  }

  public ChainHashMap(int cap, double loadFactor) {
    super(cap, loadFactor);
  }

  public ChainHashMap(int cap, int p, double loadFactor) {
    super(cap, p, loadFactor);
  }

  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
  }

  @Override
  protected V bucketGet(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    return bucket.get(k);
  }

  @Override
  protected V bucketPut(int h, K k, V v) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null)
      bucket = table[h] = new UnsortedTableMap<>();
    int oldSize = bucket.size();
    V answer = bucket.put(k,v);
    n += (bucket.size() - oldSize);
    return answer;
  }

  @Override
  protected V bucketRemove(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    int oldSize = bucket.size();
    V answer = bucket.remove(k);
    n -= (oldSize - bucket.size());
    return answer;
  }

  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h=0; h < capacity; h++)
      if (table[h] != null)
        for (Entry<K,V> entry : table[h].entrySet())
          buffer.add(entry);
    return buffer;
  }
}
