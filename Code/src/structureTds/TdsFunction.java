package structureTds;

import java.util.LinkedHashMap;

public class TdsFunction implements Tds {

    public String nom;
    public String type;
    public LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
    public LinkedHashMap<String, String> variables = new LinkedHashMap<String, String>();

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
