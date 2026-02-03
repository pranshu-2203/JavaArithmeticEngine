package main.java.com.Calculator.Tokenizer;

public class Token {

    public enum Type {
        NUMBER, OPERATOR, LPAREN, RPAREN
    }

    public Type type;
    public double value;
    public char op;

    public Token(double value) {
        this.type = Type.NUMBER;
        this.value = value;
    }

    public Token(Type type, char op) {
        this.type = type;
        this.op = op;
    }
}
