package structureTds;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TestSemantique {

    public Hashtable<String, TdsFunction> TableFunction = new Hashtable<String, TdsFunction>();
    public Hashtable<String, TdsStruct> TableStruct = new Hashtable<String, TdsStruct>();

    public Stack<Tds> TdsStack = new Stack<Tds>();

    public TestSemantique() {
        TdsFunction malloc = new TdsFunction("malloc", "void *");
        malloc.addParam("n", "int");
        TdsFunction print = new TdsFunction("print", "void");
        print.addParam("n", "int");

        TableFunction.put("print", print);
        TableFunction.put("malloc", malloc);
    }

    public void fonc_non_def(String name) {
        if (! TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction : "+ name +" => non definie");
        }
    }

    public void struct_non_def(String name) {
        if (! TableStruct.containsKey(name)) {
            throw new RuntimeException("Erreur struct : "+ name +" => non definie");
        }
    }

    public void struct_deja_def(String name) {
        if (TableStruct.containsKey(name)) {
            throw new RuntimeException("Erreur struct : "+ name +" => déjà definie");
        }
    }

    public void nombre_param(String name, int nb) {
        TdsFunction function = TableFunction.get(name);
        int nb_param = function.params.size();

        if (nb_param != nb) {
            throw new RuntimeException("Erreur fonction : "+ name +" => nombre de paramètres ne correspond pas");
        }

    }
}
