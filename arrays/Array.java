package com.company.arrays;

public class Array {
    private int[] items;
    private int count;

    public Array(int length) {
        items = new int[length];
        count = 0;
    }

    // O(n)
    public void insert(int item) {
        if (count == items.length) {
            var newArray = new int[count * 2];
            System.arraycopy(items, 0, newArray, 0, count);
            items = newArray;
        }
        items[count++] = item;
    }

    // O(n)
    public int indexOf(int item) {
        for (int i = 0; i < items.length; i++)
            if (items[i] == item)
                return i;
        return -1;
    }

    // O(1)
    public int valueAt(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();
        return items[index];
    }

    // O(n)
    public void removeAt(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();
        if (count - (index + 1) >= 0)
            System.arraycopy(items, index + 1, items, index, count - (index + 1));
        count--;
    }

    // O(n)
    public int max() {
        int maxValue = items[0];
        for (int i = 1; i < count; i++) {
            if (items[i] > maxValue)
                maxValue = items[i];
        }
        return maxValue;
    }

    // O(n * m)
    public Array intersect(int[] other) {
        var newArray = new Array(0);
        for (var item : items)
            for (var itemOther : other)
                if (item == itemOther)
                    newArray.insert(item);
        return newArray;
    }

    // O(n)
    public void reverse() {
        for (int i = 0, j = count - 1; i <= count / 2; i++, j--)
            if (items[i] != items[j])
                swap(i, j);
    }

    // O(n)
    public void insertAt(int index, int item) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();
        insert(item);
        for (int i = count - 1; i > index; i--)
            swap(i, i - 1);
    }

    // O(1)
    private void swap(int indexA, int indexB) {
        var temp = items[indexA];
        items[indexA] = items[indexB];
        items[indexB] = temp;
    }

    // O(1)
    public int length() {
        return items.length;
    }

    // O(n)
    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }
}
