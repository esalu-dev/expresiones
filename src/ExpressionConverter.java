public class ExpressionConverter {
    final static char CLOSING_PARENTHESIS = ')';
    final static char OPENING_PARENTHESIS = '(';

    // Función para verificar si un carácter es un operador
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    // Función para verificar si un carácter es un operando (letra o número)
    public static boolean isOperand(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    public static String showError(String expression, int contador){
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
                    throw new Error("Parenthesis cannot precede or succeed an operator." + "\n" + showError(expression, contador));
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

    public static void push(String[] stack, String element){
        if(top == stack.length){
            throw new Error("Stack overflow");
        }
        stack[top] = element;
        top++;
    }

    public static String pop(String[] stack){
        if(top == 0){
            throw new Error("Stack underflow");
        }
        top--;
        return stack[top];
    }
    
    static int top = 0;
    public static String infixToPostfix(String expression){
    String[] operatorStack = new String[expression.length()];
    String result = "";
    int balanceOperandAndOperator = 0;
    int numberParenthesis = 0;
    int contador = 0;
    int length = expression.length()-1;
    for (char c : expression.toCharArray()) {
        if (isOperand(c)) {
            balanceOperandAndOperator++;
            result += c;
        } 
        if (isOperator(c)) {
            balanceOperandAndOperator--;

            int prioridad = getPriority(c);
            while(top > 0 && getPriority(operatorStack[top-1].charAt(0)) >= prioridad){
                result += pop(operatorStack);
            }
            push(operatorStack, Character.toString(c));

            if(contador == 0){
                throw new Error("Expression cannot start with an operator." + "\n" + showError(expression, contador));
            }
            if(contador == length){
                throw new Error("Expression cannot end with an operator." + "\n" + showError(expression, contador));
            }
        } 
        if(balanceOperandAndOperator > 1){
            throw new Error("Unexpected operand. Expected Operator." + "\n" + showError(expression, contador));
        }
        if(balanceOperandAndOperator < 0){
            throw new Error("Unexpected operator. Expected Operand in posis." + "\n" + showError(expression, contador));
        }
        if (c == OPENING_PARENTHESIS) {
            numberParenthesis++;
        } 
        if (c == CLOSING_PARENTHESIS) {
            numberParenthesis--;
        }
        contador++;
    }
    while (top > 0) {
        result += pop(operatorStack);
        top--;
    }
    if(numberParenthesis > 0 ){
        throw new Error("Unexpected '('. There is a missing ')'");
    }
    
    if(numberParenthesis < 0){
        throw new Error("Unexpected ')'. There is a missing '('");
    }
    
    
    return result;
}


    
}
