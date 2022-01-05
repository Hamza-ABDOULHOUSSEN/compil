package tds;
import ast.Ast ;

import ast.ParamInt;
import ast.ParamStruct;
import ast.*;

public class TDSParamEntry extends TDSEntry {
    public Ast ident;
    public Ident struct_name;
    public String type;

    public TDSParamEntry(ParamInt var) {
        this.ident = var.ident;
        this.type = "INT";
    }

    public TDSParamEntry(ParamStruct var) {
        this.struct_name = var.struct_name;
        this.type = "STRUCT";
    }

    public String toString() {
        String result = "" ;
        if (this.type == "INT"  ){
            Ident newident = (Ident) this.ident  ;
            result = newident.name + " : " + type;
        }
        else if (this.type == "STRUCT" ){
            result = struct_name.name + " : " + type;
        }
        return result ;
    }
}