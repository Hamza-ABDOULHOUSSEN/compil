package ast;

import java.util.ArrayList;

public class DeclFctInt implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public Ast bloc ;

    public DeclFctInt(Ast ident, Ast bloc) {
        this.ident = ident ;
        this.bloc = bloc ;
    }
}
