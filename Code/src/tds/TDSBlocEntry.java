package tds;

import ast.Bloc;

public class TDSBlocEntry extends TDSEntry {
    private TdsVisitor visitor;

    public TDSBlocEntry(Bloc bloc) {
        this.visitor = new TdsVisitor();
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
