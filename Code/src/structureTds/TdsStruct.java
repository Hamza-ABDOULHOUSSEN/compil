package structureTds;

import java.util.LinkedHashMap;

public class TdsStruct implements Tds {

    public String nom;
    public LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();

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
