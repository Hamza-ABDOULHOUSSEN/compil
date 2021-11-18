package ast;

public class Ident implements Ast{

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String name;

    public Ident(String name){
        this.name = name;
    }

}
