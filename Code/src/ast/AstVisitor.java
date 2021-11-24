package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(Decl_fct_int decl_fct_int);
    public T visit(Decl_fct_struct decl_fct_B);
    public T visit(DeclVarInt var);
    public T visit(Ident var);
    
}
