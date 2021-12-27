package ast;

import java.util.ArrayList;

public class Fct implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ident ident ;

    public Fct(Ident ident) {
        this.ident = ident ;
    }
}
