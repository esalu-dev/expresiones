import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce una expresión matemática para ser validada: ");
        String expression = sc.nextLine();

       boolean validacion = ExpressionConverter.validateInfixExpression(expression);
       if(validacion) System.out.println("La expresión es válida");
         else System.out.println("La expresión no es válida");

         String postfixExpression = ExpressionConverter.infixToPostfix(expression);
         System.out.println("Expresión posfija: " + postfixExpression);

         String prefixExpression = ExpressionConverter.infixToPrefix(expression);
          System.out.println("Expresión prefija: " + prefixExpression);
        
        sc.close();
    }

}
