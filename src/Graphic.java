import java.util.ArrayList;
import java.util.List;

public class Graphic {
   static String expresion = "(a+b*c) - (d*e+f)*g";
   public static void main(String[] args) {
      List<Double> values = new ArrayList<>();
          List<Character> operadores = new ArrayList<>();
      System.out.println("Conversión de expresiones infijas a postfijas y prefijas");
      System.out.println("Expresión infija: " + expresion);
      String expresionPostfija = ExpressionConverter2.infixToPostfix(expresion);
      System.out.println("Expresión postfija: " + expresionPostfija);
      System.out.println("Expresión prefija: " + ExpressionConverter2.infixToPrefix(expresion));
      System.out.println("\n\nEvaluación de expresiones infijas, postfijas y prefijas");
      System.out.println(ExpressionConverter2.evaluatePostfixExpression(expresionPostfija, values, operadores));
   }
}
