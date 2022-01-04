package tds;

import ast.Bloc;
import ast.If;
import ast.IfElse;

public class TDSIfEntry extends TDSEntry {
    public TDSBlocEntry thenBloc = null;
    public TDSBlocEntry elseBloc = null;

    public TDSIfEntry(If IfThen , TDS father) {
        if (IfThen.instruction instanceof Bloc) {
            thenBloc = new TDSBlocEntry(father, (Bloc)IfThen.instruction);
        }
    }

    public TDSIfEntry(IfElse ifElse , TDS father) {
        if (ifElse.instruction1 instanceof Bloc) {
            thenBloc = new TDSBlocEntry(father, (Bloc)ifElse.instruction1);
        }
        if (ifElse.instruction2 instanceof Bloc) {
            elseBloc = new TDSBlocEntry(father, (Bloc)ifElse.instruction2);
        }
    }

    public String toString() {
        String ret = "If";
        if (thenBloc != null) ret += "with a then bloc";
        if (elseBloc != null) ret += "with an else bloc";
        return ret;
    }
}

