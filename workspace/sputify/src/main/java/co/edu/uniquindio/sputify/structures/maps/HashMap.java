package co.edu.uniquindio.sputify.structures.maps;

import co.edu.uniquindio.sputify.structures.lists.LinkedList;
import co.edu.uniquindio.sputify.structures.lists.SimpleList;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        this.buckets = new SimpleList[initialCapacity];
        this.size = 0;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new SimpleList<Entry<K, V>>();
        }
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        buckets[index].addHead(new Entry<K, V>(key, value));
        size++;
        if ((double) size / buckets.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        if (buckets[index] != null) {
            for (Entry<K, V> entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    V removedValue = entry.value;
                    buckets[index].remove(entry);
                    size--;
                    return removedValue;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void resize() {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new SimpleList[oldBuckets.length * 2];
        size = 0;
        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    private static class Entry<K, V> implements Comparable<Entry<K, V>>{
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            //TODO
            return 0;
        }
    }
}
