package ast;

public class DeclVarInt implements Ast {

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident;

    public DeclVarInt(Ast ident){
        this.ident = ident;
    }
    
}

