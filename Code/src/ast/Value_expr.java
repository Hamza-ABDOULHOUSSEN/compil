package ast;

public class Value_expr implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast expr ;

    public Value_expr(Ast expr) {
        this.expr = expr ;
    }
}
