package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(DeclVarInt var);
    public T visit(Ident var);

}
