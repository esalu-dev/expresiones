import java.io.IOException;
import java.util.Scanner;

public class Main{
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
      System.out.println("1. Validar expresión infija");
      System.out.println("2. Convertir expresión infija a postfija");
      System.out.println("3. Convertir expresión infija a prefija");
      System.out.println("4. Evaluar expresión postfija");
      System.out.println("5. Evaluar expresión prefija");
      System.out.println("6. Salir");
      System.out.print("Ingresa una opción: ");
      int opcion = Integer.parseInt(sc.nextLine());
      switch(opcion){
        case 1:
          clearScreen();
          System.out.println("Validar expresión infija");
          System.out.print("Ingresa la expresión infija: ");
          String infija = sc.nextLine();
          System.out.println("La expresión es: " + infija);
          System.out.println("¿Es válida? " + ExpressionConverter.validateInfixExpression(infija));
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 2:
          clearScreen();
          System.out.println("Convertir expresión infija a postfija");
          System.out.print("Ingresa la expresión infija: ");
          String infija2 = sc.nextLine();
          System.out.println("La expresión es: " + infija2);
          System.out.println("La expresión postfija es: " + ExpressionConverter.infixToPostfix(infija2));
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 3:
          clearScreen();
          System.out.println("Convertir expresión infija a prefija");
          System.out.print("Ingresa la expresión infija: ");
          String infija3 = sc.nextLine();
          System.out.println("La expresión es: " + infija3);
          System.out.println("La expresión prefija es: " + ExpressionConverter.infixToPrefix(infija3));
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 4:
          clearScreen();
          System.out.println("Evaluar expresión postfija");
          System.out.print("Ingresa la expresión postfija: ");
          String postfija = sc.nextLine();
          System.out.println("La expresión es: " + postfija);
          System.out.println("El resultado es: " + ExpressionConverter.evaluatePostfixExpression(postfija));
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        case 5:
          clearScreen();
          System.out.println("Evaluar expresión prefija");
          System.out.print("Ingresa la expresión prefija: ");
          String prefija = sc.nextLine();
          System.out.println("La expresión es: " + prefija);
          System.out.println("El resultado es: " + ExpressionConverter.evaluatePrefixExpression(prefija));
          System.out.println("Enter para continuar..."); sc.nextLine();
          break;
        default:
          salir = true;
          break;
      }
    }

    sc.close();
  }
}