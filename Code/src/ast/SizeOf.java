package ast;

public class SizeOf implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;

    public SizeOf(Ast ident) {
        this.ident = ident ;
    }
}
