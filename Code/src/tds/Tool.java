package tds;

import ast.*;

public class Tool {
    private TDS tds;

    public Tool (TDS tds) {
        this.tds = tds;
    }


    //  int -> 0
    //  struct -> 1
    //  other -> -1
    public int typeOf (int i) {
        return 0;
    }

    public int typeOf (Ast ast) {
        if (ast instanceof Bloc || ast instanceof DefStruct || ast instanceof Fichier || ast instanceof If || ast instanceof IfElse || ast instanceof Return || ast instanceof DefFctIntParam || ast instanceof ParamInt || ast instanceof DeclVarInt || ast instanceof DefFctStructParam || ast instanceof ParamStruct || ast instanceof DeclVarStruct || ast instanceof While) {
            return -1;
        }
        if (ast instanceof Affect) {
            return typeOf(((Affect)ast).left);
        }
        //if (ast instanceof ExprOper) {
        //   return 0;
        //}
        if (ast instanceof Parenthese) {
            Parenthese p = (Parenthese)ast;
            return typeOf(p.expr);
        }
        if (ast instanceof FctParam) {
            FctParam fp = (FctParam)ast;
            Ident newident = (Ident) fp.ident  ;
            TDSEntry tdsEntry = tds.getRefEntry(newident.name);
            if (tdsEntry == null) {
                throw new IllegalArgumentException();
            }
            if (!(tdsEntry instanceof TDSFuncEntry)) {
                throw new IllegalArgumentException();
            }
            TDSFuncEntry tdsFuncEntry = (TDSFuncEntry) tdsEntry;
            if (tdsFuncEntry.type.equals("INT")) return 0;
            else return 1;
        }
        if (ast instanceof Fct) {
            Fct f = (Fct)ast;
            Ident newident = (Ident) f.ident  ;
            TDSEntry tdsEntry = tds.getRefEntry(newident.name);
            if (tdsEntry == null) {
                throw new IllegalArgumentException();
            }
            if (!(tdsEntry instanceof TDSFuncEntry)) {
                throw new IllegalArgumentException();
            }
            TDSFuncEntry tdsFuncEntry = (TDSFuncEntry) tdsEntry;
            if (tdsFuncEntry.type.equals("INT")) return 0;
            else return 1;
        }

        if (ast instanceof Int || ast instanceof SizeOf) {
            return 0;

    }
        return 0;

}}