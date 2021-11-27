package ast;

import java.util.ArrayList;

public class Value_list_expr_vide implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ident ident ;
    public ArrayList<Ast> exprs ;

    public Value_list_expr_vide(Ident ident, ArrayList<Ast> exprs) {
        this.ident = ident ;
        this.exprs = exprs ;
    }
}
