package ast;

public class Unaire implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast fleche ;

    public Unaire(Ast fleche){
        this.fleche = fleche ;
    }
}
