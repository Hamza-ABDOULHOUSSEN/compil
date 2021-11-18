package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(Decl_fct_A decl_fct_A);
    public T visit(Decl_fct_B decl_fct_B);
    public T visit(DeclVarInt var);
    public T visit(Ident var);
    
}
