package ast;

public class Fichier implements Ast {

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast declarations;

    public Fichier(Ast declarations) {
        this.declarations = declarations;
    }

}
