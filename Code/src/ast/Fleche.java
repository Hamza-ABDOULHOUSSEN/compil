package ast;

public class Fleche implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast value ;
    public Ast ident ;

    public Fleche(Ast value, Ast ident){
        this.value = value;
        this.ident = ident;
    }
    
}

