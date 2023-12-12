import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExpressionConverter2 {
   private static final char OPENING_PARENTHESIS = '(';
   private static final char CLOSING_PARENTHESIS = ')';

   private static boolean isOperator(char c) {
      return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
  }

  // Función para verificar si un carácter es un operando (letra o número)
  private static boolean isOperand(char c) {
      return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  private static String showError(String expression, int contador){
   return expression + "\n" + " ".repeat(contador) + "^";
}

private static int getPriority(char operator) {
   switch (operator) {
       case '+':
       case '-':
           return 1;
       case '*':
       case '/':
           return 2;
       case '^':
           return 3;
   }
   return 0;
}

   public static boolean validateInfixExpression(String expression){
      boolean result = false;
      int balanceOperandAndOperator = 0;
      int numberParenthesis = 0;
      int contador = 0;
      boolean lastIsOperator = false;
      int length = expression.length()-1;
      for (char c : expression.toCharArray()) {
          if (c == OPENING_PARENTHESIS) {
              numberParenthesis++;
              lastIsOperator = false;
          } 
          if (c == CLOSING_PARENTHESIS) {
              numberParenthesis--;
              if(!lastIsOperator){
                  throw new Error("Parenthesis cannot precede or succeed an operator." + "\n" + showError(expression, contador));
              }
          }
          if (isOperand(c)) {
              balanceOperandAndOperator++;
              lastIsOperator = true;
          } 
          if (isOperator(c)) {
              balanceOperandAndOperator--;
              if(contador == 0){
                  throw new Error("Expression cannot start with an operator." + "\n" + showError(expression, contador));
              }
              if(contador == length){
                  throw new Error("Expression cannot end with an operator." + "\n" + showError(expression, contador));
              }
              if(!lastIsOperator){
                  throw new Error("Unexpected Operator." + "\n" + showError(expression, contador));
              }
              lastIsOperator = false;
          } 
          if(balanceOperandAndOperator > 1){
              throw new Error("Unexpected operand. Expected Operator." + "\n" + showError(expression, contador));
          }
          if(balanceOperandAndOperator < 0){
              throw new Error("Unexpected operator. Expected Operand in posis." + "\n" + showError(expression, contador));
          }
         
          contador++;
      }
      if(numberParenthesis > 0 ){
          throw new Error("Unexpected '('. There is a missing ')'");
      }
      
      if(numberParenthesis < 0){
          throw new Error("Unexpected ')'. There is a missing '('");
      }
      
      if(balanceOperandAndOperator == 1){
          result = true;
      }
      return result;
  }

  private static void push(String[] stack, String element){
      if(top == stack.length){
          throw new Error("Stack overflow");
      }
      stack[top] = element;
      top++;
  }

  private static String pop(String[] stack){
      if(top == 0){
          throw new Error("Stack underflow");
      }
      top--;
      return stack[top];
  }
  
  private static int top = 0;

  public static String infixToPostfix(String expression) {
   if (!validateInfixExpression(expression)) {
       throw new Error("Invalid expression");
   }

   String[] stack = new String[expression.length()];
   String postfix = "";

   for (char c : expression.toCharArray()) {
       // Imprimir estado de la pila y la expresión antes de procesar el caracter actual
       System.out.println("\nPila: " + Arrays.toString(Arrays.copyOfRange(stack, 0, top)));
       try{
            Thread.sleep(500);
   
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
       System.out.println("Expresión actual: " + postfix);

       if (isOperand(c)) {
           postfix += c;
       }

       if (isOperator(c)) {
           while (top > 0 && getPriority(stack[top - 1].charAt(0)) >= getPriority(c)) {
               postfix += pop(stack);
           }
           push(stack, String.valueOf(c));
       }

       if (c == OPENING_PARENTHESIS) {
           push(stack, String.valueOf(c));
       }

       if (c == CLOSING_PARENTHESIS) {
           while (top > 0 && stack[top - 1].charAt(0) != OPENING_PARENTHESIS) {
               postfix += pop(stack);
           }
           pop(stack);
       }
         try{
               Thread.sleep(500);
      
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
   }

   // Imprimir estado final de la pila y la expresión después de procesar todos los caracteres
   System.out.println("Pila final: " + Arrays.toString(Arrays.copyOfRange(stack, 0, top)));
   System.out.println("Expresión final: " + postfix);

   while (top > 0) {
       postfix += pop(stack);
   }

   return postfix;
}



   

public static String infixToPrefix(String expression){
   if(!validateInfixExpression(expression)){
       throw new Error("Invalid expression");
   }
   StringBuilder reversedInfix = new StringBuilder(expression).reverse();
   for (int i = 0; i < reversedInfix.length(); i++) {
       if (reversedInfix.charAt(i) == '(') {
           reversedInfix.setCharAt(i, ')');
       } else if (reversedInfix.charAt(i) == ')') {
           reversedInfix.setCharAt(i, '(');
       }
   }
   System.out.println("Expresión infija invertida: " + reversedInfix.toString());
   String postfix = infixToPostfix(reversedInfix.toString());
   System.out.println("Expresión postfija invertida: " + postfix);
   return new StringBuilder(postfix).reverse().toString();
}
private static int top2 = 0;

private static double askForVariableValue(char variable) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor para la variable " + variable + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor no válido. Ingrese un número: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

public static double evaluatePostfixExpression(String expression, List<Double> values, List<Character> operands) {
        double[] operandStack = new double[expression.length()];

        for (char c : expression.toCharArray()) {
            // Imprimir estado de la pila y la expresión antes de procesar el caracter actual
            System.out.println("Pila: " + Arrays.toString(Arrays.copyOfRange(operandStack, 0, top2 + 1)));

            if (Character.isLetter(c)) {
                if (operands.contains(c)) {
                    int flag = operands.indexOf(c);
                    push(operandStack, values.get(flag));
                } else {
                    operands.add(c);
                    double value = askForVariableValue(c);
                    values.add(value);
                    push(operandStack, value);
                }
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder operand = new StringBuilder();
                while (Character.isDigit(c) || c == '.') {
                    operand.append(c);
                    if (++top2 == expression.length()) {
                        break;
                    }
                    c = expression.charAt(top2);
                }
                push(operandStack, Double.parseDouble(operand.toString()));
                top2--;
            } else if (isOperator(c)) {
                double operand2 = pop(operandStack);
                double operand1 = pop(operandStack);
                double result = performOperation(c, operand1, operand2);
                push(operandStack, result);
            } else if (c == ' ') {
                // Ignorar espacios en blanco
            } else {
                throw new IllegalArgumentException("Carácter no válido: " + c);
            }
        }

        // Imprimir estado final de la pila después de procesar todos los caracteres
        System.out.println("Pila final: " + Arrays.toString(Arrays.copyOfRange(operandStack, 0, top2)));

        if (top2-1 == 0) {
            return pop(operandStack);
        } else {
            throw new IllegalArgumentException("Expresión inválida");
        }
    }

    private static void push(double[] stack, double element) {
      if (top2 == stack.length) {
          throw new StackOverflowError("Stack overflow");
      }
      stack[top2] = element;
      top2++;
  }
  private static double pop(double[] stack) {
      if (top2 == 0) {
          throw new StackOverflowError("Stack underflow");
      }
      top2--;
      return stack[top2];
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
         case '^':
             return Math.pow(operand1, operand2);
       default:
           throw new IllegalArgumentException("Operador no válido: " + operator);
   }
}

}
