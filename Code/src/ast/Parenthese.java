package ast;

public class Parenthese implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast expr ;

    public Parenthese(Ast expr) {
        this.expr = expr ;
    }
}
