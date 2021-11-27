package ast;

import java.util.ArrayList;

public class Fichier implements Ast {

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<Ast> declarations;

    public Fichier(ArrayList<Ast> declarations) {
        this.declarations = declarations;
    }

}
