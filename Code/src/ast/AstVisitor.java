package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(DeclVarInt declvarint);
    public T visit(Ident ident);
    public T visit(Int entier);


}
