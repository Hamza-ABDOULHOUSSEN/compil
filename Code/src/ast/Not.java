package ast;

public class Not implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ast ;

    public Not(Ast ast){
        this.ast = ast ;
    }
}
