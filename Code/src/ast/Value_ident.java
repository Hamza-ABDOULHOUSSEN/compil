package ast;

public class Value_ident implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;

    public Value_ident(Ast ident) {
        this.ident = ident ;
    }
}
