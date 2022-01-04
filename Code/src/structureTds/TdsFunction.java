package structureTds;

import java.util.Hashtable;

public class TdsFunction implements Tds {

    public String nom;
    public String type;
    public Hashtable<String, String> params = new Hashtable<String, String>();
    public Hashtable<String, String> variables = new Hashtable<String, String>();

    public TdsFunction(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    @Override
    public void addParam(String nom, String type) {
        this.params.put(nom, type);
    }

    @Override
    public void addVariable(String nom, String type) {
        this.variables.put(nom, type);
    }
}
