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

    public void fonctionNonDef(String name) {
        if (! TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction non definis");
        }
    }
}
