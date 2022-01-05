package tds;
import ast.Ast ;

import ast.ParamInt;
import ast.ParamStruct;

public class TDSParamEntry extends TDSEntry {
    public Ast ident;
    public String type;

    public TDSParamEntry(ParamInt var) {
        this.ident = var.ident;
        this.type = "INT";
    }

    public TDSParamEntry(ParamStruct var) {
        this.ident = var.ident;
        this.type = "STRUCT";
    }

    public String toString() {
        return ident + " : " + type;
    }
}