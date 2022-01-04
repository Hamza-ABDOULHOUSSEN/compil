package structureTds;

import java.util.Hashtable;

public class TdsBloc implements Tds {
    public Tds pere;
    public int NumImbr;
    public Hashtable<String, String> variables = new Hashtable<String, String>();

    public TdsBloc(Tds pere, int NumImbr) {
        this.pere = pere;
        this.NumImbr = NumImbr;
    }

    @Override
    public void addParam(String nom, String type) {

    }

    @Override
    public void addVariable(String nom, String type) {
        this.variables.put(nom, type);
    }
}
