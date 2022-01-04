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
        Parenthese
        if (ast instanceof Parenthese) {
            Parenthese p = (Parenthese)ast;
            return typeOf(p.expr);
        }
    }

}