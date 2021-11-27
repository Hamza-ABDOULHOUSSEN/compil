package ast;

public class Value_sizeof implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;

    public Value_sizeof(Ast ident) {
        this.ident = ident ;
    }
}
