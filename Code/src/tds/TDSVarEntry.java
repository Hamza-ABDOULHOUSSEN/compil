package tds;

import ast.Ident;

public class TDSVarEntry extends TDSNamedEntry{
    public String name;
    public String type;
    public TDSStructDecl structDecl;

    public TDSVarEntry(Ident var) {
        this.name = var.name;
        this.type = "INT";
    }

    public TDSVarEntry(TDSStructDecl structDecl, Ident var) {
        this.name = var.name;
        this.type = "STRUCT";
        this.structDecl = structDecl;
    }

    public String toString() {
        return name + " : " + type;
    }

    @Override
	public String getName() {
		return this.name;
	}
}