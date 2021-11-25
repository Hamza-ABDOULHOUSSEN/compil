package ast;

import java.util.ArrayList;

public class DeclVarInt implements Ast {

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public ArrayList<Ast> ident ;

    public DeclVarInt(ArrayList<Ast> ident){
        this.ident = ident;
    }
    
}

