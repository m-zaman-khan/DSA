package com.company.stacks;

import java.util.List;
import java.util.Stack;

public class ExpressionValidator {
    public static boolean validate(String input) {
        var stack = new Stack<Character>();
        var opens = List.of('[', '{', '<', '(');
        var close = List.of(']', '}', '>', ')');
        for (var character : input.toCharArray()) {
            if (opens.contains(character))
                stack.push(character);
            else if (close.contains(character) &&
                    (stack.empty() || close.indexOf(character) != opens.indexOf(stack.pop())))
                return false;
        }
        return stack.empty();
    }
}

