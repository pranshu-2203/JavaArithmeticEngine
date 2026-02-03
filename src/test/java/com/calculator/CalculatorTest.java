package test.java.com.calculator;

import org.junit.jupiter.api.Test;

import main.java.com.Calculator.Evaluator.evaluator;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    // ---- Valid operations ----
    @Test
    public void testAddition() {
        assertEquals(5, evaluator.eval("2+3"));
    }

    @Test
    public void testSubtraction() {
        assertEquals(2, evaluator.eval("5-3"));
    }

    @Test
    public void testMultiplication() {
        assertEquals(15, evaluator.eval("5*3"));
    }

    @Test
    public void testDivision() {
        assertEquals(2, evaluator.eval("6/3"));
    }

    @Test
    public void testExponent() {
        assertEquals(8, evaluator.eval("2^3"));
    }

    @Test
    public void testParentheses() {
        assertEquals(14, evaluator.eval("2*(3+4)"));
    }

    @Test
    public void testUnaryMinus() {
        assertEquals(-2, evaluator.eval("-5+3"));
    }

    @Test
    public void testUnaryMinusInParentheses() {
        assertEquals(-14, evaluator.eval("2*(-3-4)"));
    }

    @Test
    public void testDecimalNumbers() {
        assertEquals(7.5, evaluator.eval("3.5+4.0"));
    }

    @Test
    public void testComplexExpression() {
        assertEquals(3, evaluator.eval("2 + 3 * 4 / 6"));
    }

    @Test
    public void testExponentWithParentheses() {
        assertEquals(64, evaluator.eval("(2^3)^2"));
    }

    // ---- Exception / error handling ----
    @Test
    public void testInvalidCharacter() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            evaluator.eval("2 + a");
        });
        assertTrue(exception.getMessage().contains("Invalid character"));
    }

    @Test
    public void testEmptyInput() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            evaluator.eval("");
        });
        // tokenizer will throw index error
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testUnmatchedParentheses() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            evaluator.eval("(2+3");
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            evaluator.eval("5/0");
        });
    }

    @Test
    public void testMultipleUnaryMinus() {
        assertEquals(5, evaluator.eval("--5"));
        assertEquals(-5, evaluator.eval("---5"));
    }

    @Test
    public void testOperatorPrecedence() {
        assertEquals(11, evaluator.eval("2+3*3"));
        assertEquals(15, evaluator.eval("(2+3)*3"));
    }

}
