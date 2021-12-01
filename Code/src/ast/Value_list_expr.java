package ast;

import java.util.ArrayList;

public class Value_list_expr implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public ArrayList<Ast> exprs ;

    public Value_list_expr(Ast ident, ArrayList<Ast> exprs) {
        this.ident = ident ;
        this.exprs = exprs ;
    }
}
