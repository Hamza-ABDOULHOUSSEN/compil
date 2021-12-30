package tds;

import ast.Bloc;
import ast.If;
import ast.IfElse;

public class TDSIfEntry extends TDSEntry {
    public TDSBlocEntry thenBloc = null;
    public TDSBlocEntry elseBloc = null;

    public TDSFuncEntry(If IfThen , TDS father) {
        if (IfThen.instruction instanceof Bloc) {
            thenBloc = new TDSBlocEntry((Bloc)IfThen.instruction);
            thenBloc.getTable().setFatherTDS(father);
        }
    }

    public TDSIfEntry(IfElse ifElse , TDS father) {
        if (ifElse.instruction1 instanceof Bloc) {
            thenBloc = new TDSBlocEntry((Bloc)ifElse.instruction1);
            thenBloc.getTable().setFatherTDS(father);
        }
        if (ifElse.instruction2 instanceof Bloc) {
            elseBloc = new TDSBlocEntry((Bloc)ifElse.instruction2);
            elseBloc.getTable().setFatherTDS(father);
        }
    }

    public String toString() {
        String ret = "If";
        if (thenBloc != null) ret += "with a then bloc";
        if (elseBloc != null) ret += "with an else bloc";
        return ret;
    }
}

