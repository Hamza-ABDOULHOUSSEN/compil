package structureTds;

import ast.Ast;
import ast.ParamInt;
import ast.ParamStruct;

import java.util.*;

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
            throw new RuntimeException("Erreur fonction : " + name + " non definie");
        }
        TdsFunction fct = TableFunction.get(name);
        return fct.type;

    }

    public void test_main() {
        TdsFunction fct_main = TableFunction.get("main");
        int nb_var = fct_main.params.size();
        if (nb_var != 0) {
            throw new RuntimeException("Le profil de main ne correspond pas");
        }
    }

    public String struct_non_def(String type) {
        type = "struct " + type ;
        if (!TableStruct.containsKey(type)) {
            throw new RuntimeException("Erreur struct : " + type + " non definie");
        }
        TdsStruct struct = TableStruct.get(type);
        return type ;
    }

    public void struct_deja_def(String type) {
        type = "struct " + type ;
        if (TableStruct.containsKey(type)) {
            throw new RuntimeException("Erreur struct : " + type + " deja definie");
        }
    }

    public void return_type(String typeRetour) {
        //fct = 1er element de la stack
        TdsFunction function =  (TdsFunction) TdsStack.get(0) ;
        String typeDef = ((TdsFunction) TdsStack.get(0)).type ;
        if (!typeDef.equals(typeRetour)) {
            throw new RuntimeException("Erreur sur le type de retour " + typeRetour + " le type dans la definition est " + typeDef) ;
        }
    }

    public void return_bloc(String name, String contient_return) {
        if (! contient_return.equals("return")) {
            throw new RuntimeException("fonction : " + name + " ne renvoie pas d'element dans tous les cas");
        }
    }

    public void type_param(String name, ArrayList<String> declParamTypes) {
        ArrayList<String> defParamTypes = new ArrayList<String>() ;
        TdsFunction function = TableFunction.get(name);
        Collection<String> collectionDefParamTypes = function.params.values();
        for (String elem : collectionDefParamTypes) {
            defParamTypes.add(elem) ;
        }
        for (int i = 0; i<defParamTypes.size(); i++) {
            if (!defParamTypes.get(i).equals(declParamTypes.get(i))) {
                throw new RuntimeException("Erreur sur le " + i + "-eme parametre, type definition = " + defParamTypes.get(i) +" et type declaration = " + declParamTypes.get(i)) ;
            }
        }
    }

    public void nombre_param(String name, int nb) {
        TdsFunction function = TableFunction.get(name);
        int nb_param = function.params.size();

        if (nb_param != nb) {
            throw new RuntimeException("Erreur fonction : " + name + " le nombre de parametres ne correspond pas");
        }

    }

    public String var_non_def(String name) {
        String type;

        for (Tds tds : TdsStack) {
            LinkedHashMap<String, String> liste_param = new LinkedHashMap<String, String>();
            LinkedHashMap<String, String> liste_var = new LinkedHashMap<String, String>();
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

        throw new RuntimeException("Erreur variable : " + name + " non definie");

    }

    public void var_deja_def(String name) {

        //récupération du bloc actuel
        Tds tds = TdsStack.pop();

        LinkedHashMap<String, String> liste_param = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> liste_var = new LinkedHashMap<String, String>();
        if (tds instanceof TdsBloc) {
            liste_var = ((TdsBloc) tds).variables;
        } else if (tds instanceof TdsFunction) {
            liste_param = ((TdsFunction) tds).params;
            liste_var = ((TdsFunction) tds).variables;
        } else {
            liste_param = ((TdsStruct) tds).params;
        }

        if (liste_var.containsKey(name)) {
            throw new RuntimeException("Erreur variable : " + name + " deja definie");
        }

        if (liste_param.containsKey(name)) {
            throw new RuntimeException("Erreur variable : " + name + " deja definie");
        }

        //on remet le bloc ectuel qu'on a retiré après l'avoir modifié
        TdsStack.push(tds);

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

    public void cond(String type) {
        if (type.equals("void")) {
            throw new RuntimeException("Erreur condition : = au lieu de ==");
        }
    }

    public void fonc_deja_def(String name) {
        if (TableFunction.containsKey(name)) {
            throw new RuntimeException("Erreur fonction : "+ name +" deja definie");
        }
    }

    public void divis_zero(String value) {
        if (value.equals("0")) {
            throw new RuntimeException("Erreur division par zero");
        }
    }


}
