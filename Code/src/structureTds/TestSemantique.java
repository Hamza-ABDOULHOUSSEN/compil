package structureTds;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TestSemantique {

    public Hashtable<String, TdsFunction> TableFunction = new Hashtable<String, TdsFunction>();
    public Hashtable<String, TdsStruct> TableStruct = new Hashtable<String, TdsStruct>();

    public Stack<Tds> TdsStack = new Stack<Tds>();

    public void fonctionNonDef(String name) {
        if (! TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction non definis");
        }
    }
}
