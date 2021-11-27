package ast;

public class IfElse implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr ;
    public Ast instruction1 ;
    public Ast instruction2 ;

    public IfElse(Ast expr, Ast instruction1, Ast instruction2){
        this.expr = expr;
        this.instruction1 = instruction1 ;
        this.instruction2 = instruction2 ;
    }

}

