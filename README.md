# ExpressionConverter

ExpressionConverter es una utilidad Java que proporciona funciones para validar expresiones infixas y convertirlas a notación postfix (postfija). Puede ser útil en aplicaciones que trabajan con expresiones matemáticas o lógicas.

## Características

- Validación de expresiones infixas para garantizar que estén correctamente formadas.
- Conversión de expresiones infixas a notación postfix.
- Tratamiento de operadores, operandos y paréntesis en expresiones matemáticas y lógicas.
  x Conversión de expresiones infixas a notación prefija. (WIP)

## Requisitos

- Java JDK 8 o superior.

## Uso

Puedes usar la clase `ExpressionConverter` de la siguiente manera:

```java
// Validar una expresión infix
String expression = "a + b * (c - d)";
if (ExpressionConverter.validateInfixExpression(expression)) {
    System.out.println("La expresión es válida.");
} else {
    System.out.println("La expresión no es válida.");
}

// Convertir una expresión infix a postfix
String infixExpression = "a + b * c";
String postfixExpression = ExpressionConverter.infixToPostfix(infixExpression);
System.out.println("Expresión postfix: " + postfixExpression);
```
