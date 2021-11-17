package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);

}
