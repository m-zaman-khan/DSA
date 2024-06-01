package com.company.stacks;

import java.util.Stack;

public class StringReverser {
    public static String reverse(String input) {
        if (input == null)
            throw new IllegalArgumentException();

        var stack = new Stack<Character>();

        for (var character : input.toCharArray()) {
            stack.push(character);
        }

        var reverse = new StringBuilder();
        while (!stack.empty())
            reverse.append(stack.pop());

        return reverse.toString();
    }
}
