package main.java.com.Calculator.Tokenizer;

import java.util.*;

public class Tokenizer {

    static boolean isOperator(char ch) {
        return ch == '+' || ch== '-' || ch == '*' || ch == '/' || ch == '^';
    }

    static boolean isUnaryMinus(String s, int i) {
        if (s.charAt(i) != '-')
            return false;
        int j = i - 1;
        while (j >= 0 && Character.isWhitespace(s.charAt(j)))
            j--;
        if (j < 0)
            return true;
        char p = s.charAt(j);
        return p == '(' || isOperator(p);
    }

    public static List<Token> tokenize(String s) {
        List<Token> list = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (c == '(') {
                list.add(new Token(Token.Type.LPAREN, '('));
                i++;
                continue;
            }
            if (c == ')') {
                list.add(new Token(Token.Type.RPAREN, ')'));
                i++;
                continue;
            }

            if (Character.isDigit(c) || c == '.' || isUnaryMinus(s, i)) {
                StringBuilder sb = new StringBuilder();
                if (isUnaryMinus(s, i)) {
                    sb.append('-');
                    i++;
                }
                boolean hasDigits = false;
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    sb.append(s.charAt(i));
                    i++;
                    hasDigits = true;
                }
                if (!hasDigits)
                    throw new RuntimeException("Invalid unary minus without number");
                list.add(new Token(Double.parseDouble(sb.toString())));
                continue;
            }

            if (isOperator(c)) {
                list.add(new Token(Token.Type.OPERATOR, c));
                i++;
                continue;
            }

            throw new RuntimeException("Invalid character: " + c);
        }

        return list;
    }
}
