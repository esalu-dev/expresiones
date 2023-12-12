# tu_script.py

import sys

def calcular_expresion(expresion):
    try:
        # Evalúa la expresión matemática
        resultado = eval(expresion)
        return str(resultado)
    except Exception as e:
        return f"Error al evaluar la expresión: {str(e)}"

if __name__ == "__main__":
    # Lee la expresión desde stdin
    expresion = sys.stdin.readline().strip()

    # Calcula el resultado de la expresión
    resultado = calcular_expresion(expresion)

    # Imprime el resultado en stdout
    print(resultado)
    sys.stdout.flush()  # Asegura que la salida se envíe inmediatamente
