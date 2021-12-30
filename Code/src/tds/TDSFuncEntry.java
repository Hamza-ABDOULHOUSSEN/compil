package tds;

import ast.Bloc;
import ast.DefFctIntParam;
import ast.ParamInt;
import ast.DefFctInt;
import ast.DefFctStruct;
import ast.DefFctStructParam;
import ast.ParamStruct;

public class TDSFuncEntry extends TDSEntry {
    public Ast ident;
    public Ast ident1;
    public Ast ident2;

    public String type;

    public TDSBlocEntry bloc;

    public TDSFuncEntry(DefFctIntParam fct, TDS father) {
        this.ident = fct.ident;
        this.type = "INT";
        this.bloc = new TDSBlocEntry((Bloc)fct.bloc);

        bloc.getTable().setFatherTDS(father);
        TdsVisitor blocVisitor = bloc.getVisitor();

        for (ast.Ast param: fct.params) {
            if (param instanceof ParamInt) {
                ParamInt pi = (ParamInt)param;
                pi.accept(blocVisitor);
            }
            else if (param instanceof ParamStruct) {
                ParamStruct ps = (ParamStruct)param;
                ps.accept(blocVisitor);
            }
        }
    }

    public TDSFuncEntry(DefFctStructParam fct, TDS father) {
        this.ident1 = fct.ident1;
        this.ident2 = fct.ident2;
        this.type = "STRUCT";
        this.bloc = new TDSBlocEntry((Bloc)fct.bloc);

        bloc.getTable().setFatherTDS(father);
		TdsVisitor blocVisitor = bloc.getVisitor();

        for (ast.Ast param: fct.params) {
            if (param instanceof ParamInt) {
                ParamInt pi = (ParamInt)param;
                pi.accept(blocVisitor);
            }
            else if (param instanceof ParamStruct) {
                ParamStruct ps = (ParamStruct)param;
                ps.accept(blocVisitor);
            }
        }
    }   

    public TDSFuncEntry(DefFctInt fct, TDS father) {
        this.ident = fct.ident;
        this.type = "INT";
        this.bloc = new TDSBlocEntry((Bloc)fct.bloc);

        bloc.getTable().setFatherTDS(father);
    }

    public TDSFuncEntry(DefFctStruct fct, TDS father) {
        this.ident1 = fct.ident1;
        this.ident2 = fct.ident2;
        this.type = "STRUCT";
        this.bloc = new TDSBlocEntry((Bloc)fct.bloc);

        bloc.getTable().setFatherTDS(father);
    }

    public String toString() {
        return "function " + ident + " : " + type;
        return "function " + ident1 + " : " + type;
        return "function " + ident2 + " : " + type;
    }

}
