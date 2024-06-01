package com.company.hashtables;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CollisionHashTable {
    private static class Entry {
        public final int key;

        public String value;

        private Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private final Entry[] entries;
    private int count;

    public CollisionHashTable(int capacity) {
        entries = new Entry[capacity];
        count = 0;
    }

    public void put(int key, String value) {
        int index = getEmptyOrIndex(key);
        if (entries[index] == null)
            entries[index] = new Entry(key, value);
        else
            entries[index].value = value;
        count++;
    }

    public String get(int key) {
        return entries[getIndex(key)].value;
    }

    public void remove(int key) {
        entries[getIndex(key)] = null;
        count--;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == entries.length;
    }

    private int getIndex(int key) {
        for (int i = hash(key), j = 0; j < entries.length; i = indexIncrement(i), j++) {
            if (entries[i] == null) throw new NoSuchElementException();
            if (entries[i].key == key)
                return i;
        }
        throw new NoSuchElementException();
    }

    private int getEmptyOrIndex(int key) {
        for (int i = hash(key), j = 0; j < entries.length; i = indexIncrement(i), j++)
            if (entries[i] == null || entries[i].key == key)
                return i;
        throw new ArrayIndexOutOfBoundsException();
    }

    private int indexIncrement(int key) {
        return hash(key + 1);
    }

    private int hash(int key) {
        return key % entries.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(entries);
    }
}
