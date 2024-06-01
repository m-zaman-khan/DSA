package com.company.tries;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class SearchWord {
    private static final Trie trie = new Trie();

    public static void main(String[] args) {
        var path = Paths.get("big.txt");
        String data;
        try {
            data = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var words = data.split(" ");

        for (String word : words)
            if ((trie.isValidWord(word)))
                trie.insert(word);

        System.out.println(trie.countWords());
        System.out.println(trie.longestCommonPrefix());
        System.out.println(trie.autoCompletion("Geor"));
        System.out.println(trie.autoCompletion("DISFRANCHISEMENT"));
        System.out.println(trie.autoCompletion("mart"));
        System.out.println(trie.autoCompletion("spla"));
        System.out.println(trie.autoCompletion("pick"));
        System.out.println(trie.autoCompletion("doo"));
        System.out.println(trie.autoCompletion("ham"));
        System.out.println(trie.autoCompletion("war"));
        System.out.println(trie.autoCompletion("dil"));
        System.out.println(trie.autoCompletion("asp"));
    }


}
