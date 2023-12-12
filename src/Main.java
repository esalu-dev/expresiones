import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{
  static String expresion;
  static String expresionPrefija;
  static String expresionPostfija;

  public static void clearScreen(){
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        // Si estás en Windows, utiliza "cls" para limpiar la consola
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
          // En otros sistemas, utiliza "clear" para limpiar la consola
          Runtime.getRuntime().exec("clear");
      }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean salir = false;
    while(!salir){
      clearScreen();
      System.out.println("====================================");
      System.out.println("Sistema de expresiones (PILAS)");
      System.out.println("Selecciona una opción: ");
      System.out.println("1. Ingresar expresión infija");
      System.out.println("2. Convertir expresión infija a postfija");
      System.out.println("3. Convertir expresión infija a prefija");
      System.out.println("4. Evaluar expresiones");
      System.out.println("5. Salir");
      System.out.print("Ingresa una opción: ");
      int opcion = Integer.parseInt(sc.nextLine());
      switch(opcion){
        case 1:
          clearScreen();
          System.out.println("Ingresar la expresión para ser validada: ");
          System.out.print("Ingresa la expresión infija: ");
          expresion = sc.nextLine();
          System.out.println("La expresión es: " + expresion);
          System.out.println("¿Es válida? " + ExpressionConverter.validateInfixExpression(expresion));
          System.out.println("Expresión guardada en memoria");
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 2:
          clearScreen();
          if(expresion == null){
            System.out.println("No hay expresión guardada en memoria. Por favor ingresa la expresión infija primero.");
            System.out.println("Enter para continuar..."); sc.nextLine();
            break;
          }
          System.out.println("Convertir expresión infija a postfija");
          System.out.println("La expresión es: " + expresion);
          expresionPostfija = ExpressionConverter.infixToPostfix(expresion);
          System.out.println("La expresión postfija es: " + expresionPostfija);
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 3:
          clearScreen();
          if(expresion == null){
            System.out.println("No hay expresión guardada en memoria. Por favor ingresa la expresión infija primero.");
            System.out.println("Enter para continuar..."); sc.nextLine();
            break;
          }
          System.out.println("Convertir expresión infija a prefija");
          System.out.println("La expresión es: " + expresion);
          expresionPrefija = ExpressionConverter.infixToPrefix(expresion);
          System.out.println("La expresión prefija es: " + expresionPrefija);
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 4:
          List<Double> values = new ArrayList<>();
          List<Character> operadores = new ArrayList<>();

          clearScreen();
          System.out.println("Evaluar expresiones infija, prefija y postfija");
          
          if(expresion == null){
            System.out.println("No hay expresión guardada en memoria. Por favor ingresa la expresión infija primero.");
            System.out.println("Enter para continuar..."); sc.nextLine();
            break;
          }
          if(expresionPrefija == null){
            System.out.println("No hay expresión prefija guardada en memoria. Por favor convierte la expresión infija a prefija primero.");
            System.out.println("Enter para continuar..."); sc.nextLine();
            break;
          }
          if(expresionPostfija == null){
            System.out.println("No hay expresión postfija guardada en memoria. Por favor convierte la expresión infija a postfija primero.");
            System.out.println("Enter para continuar..."); sc.nextLine();
            break;
          }
          
          System.out.println("La expresión postfija es: " + expresionPostfija);
          System.out.println("El resultado es: " + ExpressionConverter.evaluatePostfixExpression(expresionPostfija, values, operadores));
          System.out.println("La expresión prefia es: "+ expresionPrefija);
          System.out.println("El resultado es: " + ExpressionConverter.evaluatePrefixExpression(expresionPrefija, values, operadores));
          System.out.println("La expresión infija es: " + expresion);
          System.out.println("El resultado es: " + ExpressionConverter.evaluateInfixExpression(expresion, values, operadores));

          System.out.println("\nEnter para continuar..."); sc.nextLine();
          break;
        default:
          System.out.println("Saliendo...");
          salir = true;
          break;
      }
    }

    sc.close();
  }
}