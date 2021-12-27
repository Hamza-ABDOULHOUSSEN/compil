package ast;

import java.util.ArrayList;

public class DefFctInt implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public Ast bloc ;

    public DefFctInt(Ast ident, Ast bloc) {
        this.ident = ident ;
        this.bloc = bloc ;
    }
}
