package com.company.Algorithms;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Sorter {
    public static void bubbleSort(int[] dataArray) {
        boolean isSorted;
        for (int i = 0; i < dataArray.length; i++) {
            isSorted = true;
            for (int j = 1; j < dataArray.length - i; j++)
                if (dataArray[j] < dataArray[j - 1]) {
                    swap(dataArray, j, j - 1);
                    isSorted = false;
                }
            if (isSorted)
                return;
        }
    }

    public static void selectionSort(int[] dataArray) {
        for (int i = 0; i < dataArray.length; i++)
            swap(dataArray, getMinimumIndex(dataArray, i), i);
    }

    public static void insertionSort(int[] dataArray) {
        for (int i = 1; i < dataArray.length; i++) {
            int current = dataArray[i];
            int j = i - 1;
            while (j >= 0 && current < dataArray[j]) {
                dataArray[j + 1] = dataArray[j];
                --j;
            }
            dataArray[j + 1] = current;
        }
    }

    public static void mergeSort(int[] dataArray) {
        mergeSort(dataArray, 0, dataArray.length);
    }

    private static void mergeSort(int[] dataArray, int startIndex, int endIndex) {
        int length = endIndex - startIndex;
        if (length < 2)
            return;
        int midIndex = startIndex + length / 2;
        mergeSort(dataArray, startIndex, midIndex);
        mergeSort(dataArray, midIndex, startIndex + length);

        merge(dataArray, startIndex, midIndex, length);
    }

    public static void quickSort(int[] dataArray) {
        quickSort(dataArray, 0, dataArray.length);
    }

    private static void quickSort(int[] dataArray, int startIndex, int endIndex) {
        if (endIndex - startIndex < 2)
            return;
        int boundryIndex = partitioning(dataArray, startIndex, endIndex);
        quickSort(dataArray, startIndex, boundryIndex);
        quickSort(dataArray, boundryIndex + 1, endIndex);
    }

    public static void countingSort(int[] dataArray) {
        if (dataArray.length < 2)
            return;
        int[] count = getCount(dataArray, getMax(dataArray));
        for (int i = 0, k = 0; i < count.length && k < dataArray.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                dataArray[k++] = i;
            }
        }
    }

    public static void bucketSort(int[] dataArray) {
        if (dataArray.length < 2)
            return;
        int j = 0;
        for (List<Integer> bucket : createBuckets(dataArray)) {
            int[] array = new int[bucket.size()];
            int k = 0;
            for (Integer integer : bucket)
                array[k++] = integer;
            insertionSort(array);
            for (int value : array)
                dataArray[j++] = value;
        }
    }

    @NotNull
    private static List<List<Integer>> createBuckets(int[] dataArray) {
        int max = getMax(dataArray);
        int indexSize = (int) Math.sqrt(max) + 1;
        List<List<Integer>> buckets = new ArrayList<>(indexSize);
        for (int i = 0; i < indexSize; ++i)
            buckets.add(new ArrayList<>());

        for (int data : dataArray)
            buckets.get(data / indexSize).add(data);
        return buckets;
    }

    @NotNull
    private static int[] getCount(int[] dataArray, int max) {
        int[] count = new int[max +1];
        for (int j : dataArray)
            ++count[j];
        return count;
    }

    private static int getMax(int[] dataArray) {
        int max = dataArray[0];
        for (int i = 1; i < dataArray.length; i++)
            if (dataArray[i] > max)
                max = dataArray[i];
        return max;
    }

    private static int partitioning(int[] dataArray, int startIndex, int endIndex) {
        int boundryIndex = startIndex - 1;
        for (int i = startIndex; i < endIndex; i++)
            if (dataArray[i] <= dataArray[endIndex - 1])
                swap(dataArray, i, ++boundryIndex);
        return boundryIndex;
    }

    private static void merge(int[] dataArray, int startIndex, int midIndex, int length) {
        int leftIndex = startIndex;
        int rightIndex = midIndex;
        int[] resultingArray = new int[length];

        for (int i = 0; i < resultingArray.length; i++) {
            if (leftIndex < midIndex && (rightIndex == startIndex + length || dataArray[leftIndex] < dataArray[rightIndex]))
                resultingArray[i] = dataArray[leftIndex++];
            else
                resultingArray[i] = dataArray[rightIndex++];
        }

        System.arraycopy(resultingArray, 0, dataArray, startIndex, resultingArray.length);
    }

    private static int getMinimumIndex(int[] dataArray, int startIndex) {
        int minimumIndex = startIndex;
        for (int j = startIndex + 1; j < dataArray.length; j++)
            if (dataArray[j] < dataArray[minimumIndex])
                minimumIndex = j;
        return minimumIndex;
    }

    private static void swap(int[] dataArray, int a, int b) {
        var temp = dataArray[a];
        dataArray[a] = dataArray[b];
        dataArray[b] = temp;
    }
}
