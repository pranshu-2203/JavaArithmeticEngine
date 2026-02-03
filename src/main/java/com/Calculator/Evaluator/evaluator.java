package main.java.com.Calculator.Evaluator;

import java.util.*;
import main.java.com.Calculator.Tokenizer.Token;
import main.java.com.Calculator.Tokenizer.Tokenizer;

public class evaluator {

    static int priority(char OPERATOR) {
        if (OPERATOR == '+' || OPERATOR == '-')
            return 1;
        if (OPERATOR == '*' || OPERATOR == '/')
            return 2;
        if (OPERATOR == '^')
            return 3;
        return -1;
    }

    static boolean rightAssoc(char OPERATOR) {
        return OPERATOR == '^';
    }

    static void apply(Stack<Double> s, char OPERATOR) {
        double b = s.pop();
        double a = s.pop();
        switch (OPERATOR) {
            case '+':
                s.push(a + b);
                break;
            case '-':
                s.push(a - b);
                break;
            case '*':
                s.push(a * b);
                break;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Division by zero");
                s.push(a / b);
                break;
            case '^':
                s.push(Math.pow(a, b));
                break;
        }
    }

    public static double eval(String input) {
        List<Token> tokens = Tokenizer.tokenize(input);
        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (Token t : tokens) {
            switch (t.type) {
                case NUMBER:
                    nums.push(t.value);
                    break;

                case LPAREN:
                    ops.push('(');
                    break;

                case RPAREN:
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        apply(nums, ops.pop());
                    }
                    if (ops.isEmpty())
                        throw new RuntimeException("Mismatched parentheses");
                    ops.pop();
                    break;

                case OPERATOR:
                    char currentOp = t.op;
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        char topOp = ops.peek();
                        // Apply top operator if it has higher precedence OR same precedence and
                        // left-associative
                        if (priority(topOp) > priority(currentOp) ||
                                (priority(topOp) == priority(currentOp) && !rightAssoc(currentOp))) {
                            apply(nums, ops.pop());
                        } else {
                            break;
                        }
                    }
                    ops.push(currentOp);
                    break;
            }
        }

        while (!ops.isEmpty()) {
            if (ops.peek() == '(' || ops.peek() == ')')
                throw new RuntimeException("Mismatched parentheses");
            apply(nums, ops.pop());
        }

        if (nums.size() != 1)
            throw new RuntimeException("Invalid expression");
        return nums.pop();
    }

}
