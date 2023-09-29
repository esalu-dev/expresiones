import java.util.Scanner;
import java.util.Stack;

public class Prefix {

   public static double evaluatePrefixExpression(String expression) {
      Stack<Double> stack = new Stack<>();
      int index = expression.length() - 1;

        while (index >= 0) {
            char currentChar = expression.charAt(index);

            if (Character.isLetter(currentChar)) {
                // Si es una letra, solicitar el valor de la variable y apilarlo
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el valor para '" + currentChar + "': ");
                double value = scanner.nextDouble();
                stack.push(value);
                index--;
            } else if (isOperator(currentChar)) {
                // Si es un operador, realizar la operación y apilar el resultado
                double operand1 = stack.pop();
                double operand2 = stack.pop();
                double result = performOperation(currentChar, operand1, operand2);
                stack.push(result);
                index--;
            } else if (currentChar == ' ') {
                // Ignorar espacios en blanco
                index--;
            } else {
                throw new IllegalArgumentException("Carácter no válido: " + currentChar);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expresión inválida");
        }

        return stack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("División por cero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operator);
        }
    }
}
