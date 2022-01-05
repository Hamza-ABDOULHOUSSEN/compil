package tds;

import java.util.ArrayList;

import ast.Ast;
import ast.Bloc;
import ast.DefFctIntParam;
import ast.ParamInt;
import ast.DefFctInt;
import ast.DefFctStruct;
import ast.DefFctStructParam;
import ast.ParamStruct;
import ast.Ident;

public class TDSFuncEntry extends TDSNamedEntry {
    public Ast ident;
    public ArrayList<Ast> params;
    public Ast ident1;
    public Ast ident2;

    public String type;

    public TDSBlocEntry bloc;

    public TDSFuncEntry(DefFctIntParam fct, TDS father) {
        this.ident = fct.ident;
        this.type = "INT";
        this.bloc = new TDSBlocEntry(father, (Bloc)fct.bloc);
        if (fct.params != null) {
            this.params = new ArrayList<Ast>(fct.params);
        }

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
        this.bloc = new TDSBlocEntry(father, (Bloc)fct.bloc);
        if (fct.params != null) {
            this.params = new ArrayList<Ast>(fct.params);
        }

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
        this.bloc = new TDSBlocEntry(father, (Bloc)fct.bloc);

    }

    public TDSFuncEntry(DefFctStruct fct, TDS father) {
        this.ident1 = fct.ident1;
        this.ident2 = fct.ident2;
        this.type = "STRUCT";
        this.bloc = new TDSBlocEntry(father, (Bloc)fct.bloc);
    }

    public String toString() {
        String result = "" ;
        if (this.type == "INT"  ){
            Ident newident = (Ident) this.ident  ;
            result = "function " + newident.name + " : " + type;
        }
        else if (this.type == "STRUCT" ){
            Ident newident1 = (Ident) this.ident1 ;
            Ident newident2 = (Ident) this.ident2  ;
            result = "function " + newident1.name + " : " + type + "\n" + " function " + newident2.name + " : " + type;
        }
        return result ;
       
        
    }

    @Override
	public String getName() {
        String result = "" ;
        if (this.type == "INT"  ){
            Ident newident = (Ident) this.ident  ;
            result = newident.name;
        }
        else if (this.type == "STRUCT" ){
            Ident newident1 = (Ident) this.ident1 ;
            Ident newident2 = (Ident) this.ident2  ;
            result = newident1.name + "\n" + newident2.name ;
        }
        return result ;
	}
}
