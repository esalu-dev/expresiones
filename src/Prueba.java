import java.util.List;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class Prueba {
   public static void main(String[] args) {
      ScriptEngineManager sem = new ScriptEngineManager();
      List<ScriptEngineFactory> factories = sem.getEngineFactories();
      for (ScriptEngineFactory factory : factories)
         System.out.println(factory.getEngineName() + " " + factory.getEngineVersion() + " " + factory.getNames());
      if (factories.isEmpty())
         System.out.println("No Script Engines found");
   }
}
