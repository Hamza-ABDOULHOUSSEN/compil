package structureTds;

import java.util.Hashtable;

public class TdsStruct implements Tds {

    public String nom;
    public Hashtable<String, String> params = new Hashtable<String, String>();

    public TdsStruct(String nom) {
        this.nom = nom;
    }

    @Override
    public void addParam(String nom, String type) {

    }

    @Override
    public void addVariable(String nom, String type) {
        this.params.put(nom, type);
    }

}
