package com.company.tries;

public class Test {
    public static void main(String[] args) {
        var trie = new Trie();
        trie.insert("boy");
        trie.insert("book");
        trie.insert("border");
        trie.insert("cat");
        trie.insert("dog");
        trie.insert("doctor");
        trie.insert("fine");
        trie.insert("fined");
        trie.insert("finest");
        trie.insert("figure");
        trie.insert("pick");
        trie.insert("pickle");
        trie.insert("picture");

        System.out.println(trie.containsRecursive("pick"));
        System.out.println(trie.containsRecursive("picked"));
        System.out.println(trie.countWords());
        System.out.println(trie.longestCommonPrefix());


//        trie.remove("boy");
//        trie.remove("fined");
//        trie.remove("dog");
//        trie.remove("pickle");

//        System.out.println(trie.contains("boy"));
//        System.out.println(trie.contains("fined"));
//        System.out.println(trie.contains("fine"));
//        System.out.println(trie.contains("dog"));
//        System.out.println(trie.contains("doctor"));
//        System.out.println(trie.contains("fineded"));
//        System.out.println(trie.contains("pickle"));
//        System.out.println(trie.contains("fine"));
//
//        System.out.println(trie.contains("fine"));
//        System.out.println(trie.contains("fined"));
//        System.out.println(trie.contains("fin"));
//        System.out.println(trie.contains("full"));
//        System.out.println(trie.contains("pick"));
//        System.out.println(trie.contains("book"));
//        System.out.println(trie.contains("signature"));
//        System.out.println(trie.contains(null));
        System.out.println();
        System.out.println(trie.autoCompletion(""));
        System.out.println(trie.autoCompletion("f"));
        System.out.println(trie.autoCompletion("fi"));
        System.out.println(trie.autoCompletion("fin"));
        System.out.println(trie.autoCompletion("fine"));
        System.out.println(trie.autoCompletion("fined"));
        System.out.println(trie.autoCompletion("finestify"));
    }
}
