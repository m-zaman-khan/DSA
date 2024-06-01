package com.company.Algorithms;

public class Searcher {
    public static int linearSearch(int[] dataArray, int data) {
        for (int i = 0; i < dataArray.length; i++)
            if (dataArray[i] == data)
                return i;
        return -1;
    }

    public static int binarySearchIterative(int[] dataArray, int data) {
        int left = 0;
        int right = dataArray.length - 1;
        while (left <= right) {
            int middle = (right + left) / 2;
            if (dataArray[middle] == data)
                return middle;
            if (data < dataArray[middle])
                right = middle - 1;
            else
                left = middle + 1;
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] dataArray, int data) {
        return binarySearchRecursive(dataArray, data, 0, dataArray.length - 1);
    }

    private static int binarySearchRecursive(int[] dataArray, int data, int startIndex, int lastIndex) {
        if (startIndex > lastIndex)
            return -1;
        int middle = (startIndex + lastIndex) / 2;
        if (dataArray[middle] == data)
            return middle;
        return data < dataArray[middle] ?
                binarySearchRecursive(dataArray, data, startIndex, middle - 1) :
                binarySearchRecursive(dataArray, data, middle + 1, lastIndex);
    }

    public static int ternarySearchRecursive(int[] dataArray, int data) {
        return ternarySearchRecursive(dataArray, data, 0, dataArray.length - 1);
    }

    private static int ternarySearchRecursive(int[] dataArray, int data, int startIndex, int lastIndex) {
        if (startIndex > lastIndex)
            return -1;
        int middleLeft = startIndex + (lastIndex - startIndex) / 3;
        int middleRight = startIndex + ((lastIndex - startIndex) * 2) / 3;

        if (dataArray[middleLeft] == data)
            return middleLeft;
        if (dataArray[middleRight] == data)
            return middleRight;
        if (data < dataArray[middleLeft])
            return ternarySearchRecursive(dataArray, data, startIndex, middleLeft - 1);
        if (data > dataArray[middleRight])
            return ternarySearchRecursive(dataArray, data, middleRight + 1, lastIndex);
        return ternarySearchRecursive(dataArray, data, middleLeft + 1, middleRight - 1);
    }

    public static int jumpSearch(int[] dataArray, int data) {
        int indexSize = (int) Math.sqrt(dataArray.length);
        int next = indexSize;
        while (next < dataArray.length && dataArray[next - 1] < data)
            next += indexSize;
        for (int i = next - indexSize; i < next && i < dataArray.length; i++)
            if (dataArray[i] == data)
                return i;
        return -1;
    }

    public static int exponentialSearch(int[] dataArray, int data) {
        int bound = 1;
        while (bound < dataArray.length && dataArray[bound] <= data)
            bound *= 2;
        return binarySearchRecursive(dataArray, data, bound / 2, Math.min(bound, dataArray.length - 1));
    }
}
