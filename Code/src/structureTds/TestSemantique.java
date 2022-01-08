package structureTds;

import ast.Ast;

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

    public String fonc_non_def(String name) {
        if (!TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction : " + name + " => non definie");
        }
        TdsFunction fct = TableFunction.get(name);
        return fct.type;

    }

    public String struct_non_def(String type) {
        type = "struct " + type ;
        if (!TableStruct.containsKey(type)) {
            throw new RuntimeException("Erreur struct : " + type + " => non definie");
        }
        TdsStruct struct = TableStruct.get(type);
        return type ;
    }

    public void struct_deja_def(String type) {
        type = "struct " + type ;
        if (TableStruct.containsKey(type)) {
            throw new RuntimeException("Erreur struct : " + type + " => déjà definie");
        }
    }

    public void return_type(String name) {
        //if (TableStruct.get(name) == //Type sur le retour)
    }

    public void return_bloc(String name) {

    }

    public void type_param(String name, ArrayList<Ast> newParam) {
        ArrayList<String> defParam = new ArrayList<String>() ;
        TdsFunction function = TableFunction.get(name);
        for (String key : function.params.keySet()) {
            defParam.add(function.params.get(key)) ;
        }
        for (int i = 0; i<defParam.size(); i++) {
            //if (!defParam.get(i).equals(newParam.get(i).)) {
            //    throw new RuntimeException("Erreur sur le paramètre " + i + " type definition = " + defParam.get(i) +" et type declaration = " + newParam) ;
            //}
        }
    }

    public void nombre_param(String name, int nb) {
        TdsFunction function = TableFunction.get(name);
        int nb_param = function.params.size();

        if (nb_param != nb) {
            throw new RuntimeException("Erreur fonction : " + name + " => nombre de paramètres ne correspond pas");
        }

    }

    public String var_non_def(String name) {
        String type;

        for (Tds tds : TdsStack) {
            Hashtable<String, String> liste_param = new Hashtable<String, String>();
            Hashtable<String, String> liste_var = new Hashtable<String, String>();
            if (tds instanceof TdsBloc) {
                liste_var = ((TdsBloc) tds).variables;
            } else if (tds instanceof TdsFunction) {
                liste_param = ((TdsFunction) tds).params;
                liste_var = ((TdsFunction) tds).variables;
            } else {
                liste_param = ((TdsStruct) tds).params;
            }

            if (liste_var.containsKey(name)) {
                type = liste_var.get(name);
                return type;
            }

            if (liste_param.containsKey(name)) {
                type = liste_param.get(name);
                return type;
            }

        }

        throw new RuntimeException("Erreur variable : " + name + " => non definie");

    }

    public void var_deja_def(String name) {
        int bool = 0;
        String type;

        for (Tds tds : TdsStack) {
            Hashtable<String, String> liste_param = new Hashtable<String, String>();
            Hashtable<String, String> liste_var = new Hashtable<String, String>();
            if (tds instanceof TdsBloc) {
                liste_var = ((TdsBloc) tds).variables;
            } else if (tds instanceof TdsFunction) {
                liste_param = ((TdsFunction) tds).params;
                liste_var = ((TdsFunction) tds).variables;
            } else {
                liste_var = ((TdsStruct) tds).params;
            }

            if (liste_var.containsKey(name)) {
                throw new RuntimeException("Erreur variable : " + name + " => déjà definie");
            }

            if (liste_param.containsKey(name)) {
                throw new RuntimeException("Erreur variable : " + name + " => déjà definie");
            }

        }

    }

    public String test_fleche_def(String type1, String name) {
        TdsStruct structure = TableStruct.get(type1);
        if (!structure.params.containsKey(name)) {
            throw new RuntimeException("Erreur " + type1 + " : ne contient pas d'attribut " + name);
        }
        else {
            return structure.params.get(name);
        }
    }

    public void test_type(String operation, String type_insert, String type_request) {
        if (type_insert.equals("void *")) {
            if (type_request.equals("int")) {
                throw new RuntimeException("Erreur malloc avec un entier");
            }
        }
        else if(!type_insert.equals(type_request)) {
            throw new RuntimeException("Erreur type : " + operation + " avec "+ type_insert + " au lieu de "+ type_request );
        }
    }

    public void fonc_deja_def(String name) {
        if (TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction : "+ name +" => déjà definie");
        }
    }

    public void divis_zero(int value) {
        if (value == 0) {
            throw new RuntimeException("Erreur division par zéro");
        }
    }


}
