package ast;

import java.util.ArrayList;

public class Decl_fct_int implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public Ast bloc ;

    public Decl_fct_int(Ast ident, Ast bloc) {
        this.ident = ident ;
        this.bloc = bloc ;
    }
}
