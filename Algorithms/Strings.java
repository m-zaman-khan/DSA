package com.company.Algorithms;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Strings {
    public static int countVowels(String string) {
        if (string == null)
            return 0;
        String vowels = "aeiou";
        int count = 0;
        for (char ch : string.toCharArray())
            if (vowels.indexOf(Character.toLowerCase(ch)) != -1)
                ++count;
        return count;
    }

    public static String reverse(String string) {
        if (string == null)
            return "";
        StringBuilder builder = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--)
            builder.append(string.charAt(i));
        return builder.toString();
    }

    public static String reverseSentence(String string) {
        if (string == null)
            return "";
        String[] words = string.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--)
            builder.append(words[i]).append(" ");
        return builder.toString().trim();
    }

    public static boolean hasRotation(String main, String string) {
        return main != null && string != null &&
                main.length() == string.length() &&
                (main + main).contains(string);
    }

    public static String removeDuplicate(String string) {
        if (string == null)
            return "";
        StringBuilder builder = new StringBuilder();
        for (char ch : string.toCharArray())
            if (builder.toString().indexOf(ch) == -1)
                builder.append(ch);
        return builder.toString();
    }

    public static char mostRepeatedCharacter(String string) {
        if (string == null)
            return 0;

        Map<Character, Integer> frequencies = getFrequencies(string);

        int max = 0;
        char maxChar = 0;
        for (Character ch : frequencies.keySet()) {
            if (frequencies.get(ch) > max) {
                maxChar = ch;
                max = frequencies.get(ch);
            }
        }
        return maxChar;
    }

    public static String capitalizeWords(String string) {
        if (string == null)
            return "";
        String[] words = string.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String word : words)
            if (!word.isBlank())
                builder
                        .append(Character.toTitleCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
        return builder.toString().trim();
    }

    public static boolean areAnagram(String string1, String string2) {
        if (string1 == null || string2 == null)
            return false;

        Map<Character, Integer> frequencies1 = getFrequencies(string1.toLowerCase());
        Map<Character, Integer> frequencies2 = getFrequencies(string2.toLowerCase());

        if (frequencies1.size() != frequencies2.size())
            return false;

        for (Character ch : frequencies1.keySet())
            if (!frequencies1.get(ch).equals(frequencies2.get(ch)))
                return false;
        return true;
    }

    public static boolean isPalindrome(String string) {
        if (string == null)
            return false;
        String lowerString = string.toLowerCase();
        for (int i = 0; i < lowerString.length() / 2; ++i)
            if (lowerString.charAt(i) != lowerString.charAt(lowerString.length() - i - 1))
                return false;
        return true;
    }

    @NotNull
    private static Map<Character, Integer> getFrequencies(String string) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (char ch : string.toCharArray()) {
            if (frequencies.containsKey(ch))
                frequencies.replace(ch, frequencies.get(ch) + 1);
            else
                frequencies.put(ch, 1);
        }
        return frequencies;
    }
}
