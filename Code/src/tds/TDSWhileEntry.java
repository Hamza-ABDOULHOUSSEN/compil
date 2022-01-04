package tds;

import ast.Bloc;
import ast.While;

public class TDSWhileEntry extends TDSEntry {
    public TDSBlocEntry bloc = null;

    public TDSWhileEntry(While whileStruct, TDS father) {
        if (whileStruct.instruction instanceof Bloc) {
            bloc = new TDSBlocEntry(father, (Bloc)whileStruct.instruction);
        }
    }

    public String toString() {
        String ret = "While";
        if (bloc != null) ret += " with a bloc";
        return ret;
    }
}