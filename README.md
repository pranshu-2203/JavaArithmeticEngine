# JavaArithmeticEngine

![Java](https://img.shields.io/badge/Java-17+-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)
![Tests](https://img.shields.io/badge/Tests-Passing-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)


A console-based Java arithmetic engine that evaluates mathematical expressions while respecting operator precedence and parentheses.The project is designed with a clean tokenizer–evaluator separation to make the core logic easy to understand, extend, and debug.

## Features
- Supports `+`, `-`, `*`, `/`, and `^` operators
- Parentheses handling
- Decimal numbers
- Operator precedence
- Clean tokenizer and evaluator separation
- Console-based input/output

---

## Project Structure
JavaArithmeticEngine/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── Calculator/
│   │               ├── Main.java              
│   │               ├── Evaluator/
│   │               │   └── evaluator.java     
│   │               └── Tokenizer/
│   │                   ├── Token.java         
│   │                   └── Tokenizer.java     
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── calculator/
│                   └── CalculatorTest.java    # Unit tests
│
├── README.md
├── LICENSE
└── .gitignore


---

## How to Run
1. Compile the source files
2. Run `Main.java`
3. Enter an arithmetic expression in the console

Example:
Enter expression: 2 + 3 * 4 / 6
Result: 3.0


---

## Examples
Input: 2 + 3 * 4 / 6
Output: 3.0

Input: --5
Output: 5.0

Input: (2 + 3) * 4
Output: 20.0

Input: 5 / 0
Output: ArithmeticException: Division by zero



---

## Testing  
The project includes automated test cases covering:
- Operator precedence
- Parentheses evaluation
- Decimal calculations
- Division by zero scenarios
- Invalid input handling

All tests pass locally.

---

## Limitations  
- Console-based 
- Input must be a valid arithmetic expression
- Error messages are minimal
- No support for variables or functions 
- Intended for learning purposes, not production use

---

## Learning Outcomes  
- Tokenization of expressions
- Expression evaluation logic
- Writing unit tests
- Debugging edge cases
- Structuring a Java project cleanly

---

## License
This project is licensed under the MIT License.
