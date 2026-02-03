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
        if (input == null || input.trim().isEmpty())
            throw new RuntimeException("Input is empty");

        List<Token> tokens = Tokenizer.tokenize(input);
        if (tokens.isEmpty())
            throw new RuntimeException("No valid tokens found");

        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (Token T : tokens) {
            if (T.type == Token.Type.NUMBER) {
                nums.push(T.value);
            } else if (T.type == Token.Type.LPAREN) {
                ops.push('(');
            } else if (T.type == Token.Type.RPAREN) {
                boolean foundParen = false;
                while (!ops.isEmpty()) {
                    char op = ops.pop();
                    if (op == '(') {
                        foundParen = true;
                        break;
                    } else {
                        apply(nums, op);
                    }
                }
                if (!foundParen)
                    throw new RuntimeException("Unmatched parentheses");
            } else if (T.type == Token.Type.OPERATOR) {
                char ch = T.op;
                while (!ops.isEmpty() && ops.peek() != '(' &&
                        (priority(ops.peek()) > priority(ch) ||
                                (priority(ops.peek()) == priority(ch) && !rightAssoc(ch))))
                    apply(nums, ops.pop());
                ops.push(ch);
            }
        }

        while (!ops.isEmpty()) {
            char op = ops.pop();
            if (op == '(')
                throw new RuntimeException("Unmatched parentheses");
            apply(nums, op);
        }

        if (nums.size() != 1)
            throw new RuntimeException("Invalid expression");

        return nums.pop();
    }
}
