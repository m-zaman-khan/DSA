package com.company.hashtables;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        var result = twoSum(new int[]{1, 1, 2, 4, 7, 11, 15}, 11);
//        System.out.println(result[0] + " " + result[1]);
        var table = new CollisionHashTable(8);
        table.put(1, "A");
        table.put(2, "B");
        table.put(3, "C");
        table.put(4, "D");
        table.put(5, "E");
        table.put(6, "F");
        table.put(7, "G");
        table.put(2, "H");
        System.out.println(table.get(2));
        table.put(4, "I");
        table.put(7, "J");
        table.put(13, "K");
        table.remove(4);
        table.put(14, "L");
        System.out.println(table);
    }

    public static char firstNonRepeatedCharacter(String input) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (var character : input.toCharArray())
            if (Character.isAlphabetic(character))
                frequency.put(character, frequency.getOrDefault(character, 0) + 1);
        for (var character : input.toCharArray())
            if (Character.isAlphabetic(character) && frequency.get(character) == 1)
                return character;
        return Character.MIN_VALUE;
    }

    public static char firstRepeatedCharacter(String input) {
        Set<Character> set = new HashSet<>();
        for (var character : input.toCharArray()) {
            if (set.contains(character)) return character;
            set.add(character);
        }
        return Character.MIN_VALUE;
    }

    public static char mostFrequent(String input) {
        Map<Character, Integer> frequency = new HashMap<>();
        int max = 0;
        char maxChar = 0;
        for (var character : input.toCharArray())
            if (Character.isLetterOrDigit(character)) {
                int newCount = frequency.getOrDefault(character, 0) + 1;
                if (newCount > max) {
                    max = newCount;
                    maxChar = character;
                }
                frequency.put(character, newCount);
            }
        return maxChar;
    }

    public static int countPairsWithDiff(int[] numbers, int diff) {
        Set<Integer> set = new HashSet<>();

        for (var number : numbers)
            set.add(number);
        int count = 0;
        for (var number : set)
            if (set.contains(number + diff))
                count++;
        return count;
    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            int complement = target - number;
            if (map.containsKey(complement))
                return new int[] {map.get(complement), i};
            map.put(number, i);
        }
        return null;
    }
}
