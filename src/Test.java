import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) {
        try {
            // Configura el proceso de ejecución para el script de Python
            ProcessBuilder processBuilder = new ProcessBuilder("python", "./src/tu_script.py");
            processBuilder.redirectErrorStream(true);

            // Inicia el proceso
            Process process = processBuilder.start();

            // Obtén el OutputStream para escribir en la entrada estándar del proceso (stdin)
            OutputStream outputStream = process.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            // Escribe datos en el stdin del proceso (puedes enviar comandos al script de Python)
            printWriter.println("2.0 + 3.0");
            printWriter.flush();

            // Cierra la entrada estándar para indicar que no se enviarán más datos
            outputStream.close();

            // Lee la salida estándar del proceso (stdout)
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Python Output: " + line);
            }

            // Espera a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("Python script terminado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
