package tds;

import ast.Bloc;

public class TDSBlocEntry extends TDSEntry {
    private TdsVisitor visitor;

    public TDSBlocEntry(TDS father, Bloc bloc) {
        this.visitor = new TdsVisitor();
        this.visitor.getTable().setFatherTDS(father);
        for (ast.Ast vars: bloc.vars) {
            if (vars == null) continue;
            vars.accept(this.visitor);
        }
    }

    public TDS getTable() {
        return this.visitor.getTable();
    }

    public TdsVisitor getVisitor() {
        return this.visitor;
    }

    public String toString() {
        return this.visitor.getTable().toString();
    }
}
