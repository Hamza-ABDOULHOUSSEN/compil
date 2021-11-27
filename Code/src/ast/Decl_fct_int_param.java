package ast;

import java.util.ArrayList;

public class Decl_fct_int_param implements Ast{
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public ArrayList<Ast> params ;
    public Ast bloc ;

    public Decl_fct_int_param(Ast ident, ArrayList<Ast> params, Ast bloc) {
        this.ident = ident ;
        this.params = params ;
        this.bloc = bloc ;
    }
}
