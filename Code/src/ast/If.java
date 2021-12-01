package ast;

public class If implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr ;
    public Ast instruction ;

    public If(Ast expr, Ast instruction){
        this.expr = expr;
        this.instruction = instruction ;
    }

}

