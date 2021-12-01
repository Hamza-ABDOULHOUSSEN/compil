package ast;

public class Moinsunaire implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ast ;

    public Moinsunaire(Ast ast){
        this.ast = ast ;
    }
}
