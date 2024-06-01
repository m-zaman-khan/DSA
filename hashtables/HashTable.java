package com.company.hashtables;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTable {
    private static class Entry {
        public final int key;

        public String value;

        private Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry>[] table;

    public HashTable(int capacity) {
        table = new LinkedList[capacity];
    }

    public void put(int key, String value) {
        var entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
            return;
        }
        getOrCreateBucket(key).addLast(new Entry(key, value));
    }

    public String get(int key) {
        var entry = getEntry(key);
        return (entry == null) ? null : entry.value;
    }

    public void remove(int key) {
        var entry = getEntry(key);
        if (entry == null) throw new NoSuchElementException();
        getBucket(key).remove(entry);
    }

    private int hash(int key) {
        return key % table.length;
    }

    private LinkedList<Entry> getBucket(int key) {
        return table[hash(key)];
    }

    private LinkedList<Entry> getOrCreateBucket(int key) {
        int index = hash(key);
        if (table[index] == null)
            table[index] = new LinkedList<>();
        return table[index];
    }

    private Entry getEntry(int key) {
        var bucket = getBucket(key);
        if (bucket == null)
            return null;
        for (var entry : bucket)
            if (entry.key == key)
                return entry;
        return null;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("{");
        for (var list : table) {
            if (list == null) continue;
            for (var entry : list)
                builder.append(entry.key).append("=").append(entry.value).append(", ");
        }
        builder.append("\b}");
        return builder.toString();
    }
}
