package com.company.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private static class Node {
        private final char value;
        private boolean isEndOfWord;
        private final HashMap<Character, Node> children = new HashMap<>();

        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public void removeChild(char ch) {
            children.remove(ch);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        @Override
        public String toString() {
            return "value=" + value;
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
    }

    private final Node root = new Node('\0');

    public void insert(String word) {
        if (word == null)
            throw new IllegalStateException();
        var current = root;
        for (char ch : word.toCharArray()) {
            ch = Character.toUpperCase(ch);
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;
        var current = root;
        for (char ch : word.toCharArray()) {
            ch = Character.toUpperCase(ch);
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;
        return containsRecursive(root, word, 0);
    }

    private boolean containsRecursive(Node node, String word, int index) {
        if (index == word.length())
            return node.isEndOfWord;
        var ch = Character.toUpperCase(word.charAt(index));
        if (!node.hasChild(ch))
            return false;
        var child = node.getChild(ch);
        return containsRecursive(child, word, index + 1);
    }

    public void remove(String word) {
        if (word == null)
            return;
        remove(root, word, 0);
    }

    private void remove(Node node, String word, int index) {
        if (index == word.length()) {
            node.isEndOfWord = false;
            return;
        }
        var ch = Character.toUpperCase(word.charAt(index));
        if (!node.hasChild(ch))
            return;
        var child = node.getChild(ch);
        remove(child, word, index + 1);
        if (!child.hasChildren() && !child.isEndOfWord)
            node.removeChild(ch);
    }

    public List<String> autoCompletion(String input) {
        List<String> wordList = new ArrayList<>();
        var lastNode = lastNodeOf(input);
        if (lastNode == null)
            return wordList;
        autoCompletion(lastNode, toUpperCase(input), wordList);
        return wordList;
    }

    private void autoCompletion(Node node, String word, List<String> wordList) {
        if (node.isEndOfWord)
            wordList.add(word);
        for (Node child : node.getChildren())
            autoCompletion(child, word + child.value, wordList);
    }

    private Node lastNodeOf(String input) {
        if (input == null)
            return null;
        var current = root;
        for (char ch : input.toCharArray()) {
            ch = Character.toUpperCase(ch);
            if (!current.hasChild(ch))
                return null;
            current = current.getChild(ch);
        }
        return current;
    }

    private String toUpperCase(String input) {
        StringBuilder builder = new StringBuilder();
        for (char ch : input.toCharArray())
            builder.append(Character.toUpperCase(ch));
        return builder.toString();
    }

    public int countWords() {
        return countWords(root);
    }

    private int countWords(Node node) {
        int count = 0;
        if (node.isEndOfWord)
            ++count;
        for (Node child : node.getChildren())
            count += countWords(child);
        return count;
    }

    public String longestCommonPrefix() {
        return longestCommonPrefix(root, "");
    }

    private String longestCommonPrefix(Node node, String word) {
        var longestPrefix = "";
        for (Node child : node.getChildren()) {
            var prefix = longestCommonPrefix(child, word + child.value);
            if (prefix.length() > longestPrefix.length())
                longestPrefix = prefix;
        }
        if (longestPrefix.isEmpty() && node.hasChildren() && node.isEndOfWord)
            return word;
        return longestPrefix;
    }

    public boolean isValidWord(String word) {
        for (char ch : word.toCharArray()) {
            if (!Character.isLetter(ch))
                return false;
        }
        return true;
    }
}
