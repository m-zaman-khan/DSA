package com.company.stacks;

public class Test {
    public static void main(String[] args) {
        System.out.println(StringReverser.reverse("abcd"));
        System.out.println(ExpressionValidator.validate("(([1] + <3>))[a]"));

        System.out.println();
        var stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.pop();
        stack.push(30);
        System.out.println(stack.peek());
        stack.push(40);

        System.out.println();

        System.out.println(stack);

        var twoStack = new TwoStackArray();
        System.out.println(twoStack);
        twoStack.push1(10);
        System.out.println(twoStack);
        twoStack.push2(20);
        System.out.println(twoStack);
        twoStack.push1(30);
        System.out.println(twoStack);
        twoStack.pop1();
        System.out.println(twoStack);
        twoStack.push1(50);
        System.out.println(twoStack);
        twoStack.push2(60);
        System.out.println(twoStack);
        twoStack.push2(70);
        System.out.println(twoStack);
    }
}
