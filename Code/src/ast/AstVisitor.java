package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(DeclVarInt declvarint);
    public T visit(DeclVarStruct declvarstruct);
    public T visit(Ident ident);
    public T visit(Int entier);
    public T visit(ParamInt paramint);
    public T visit(ParamStruct paramstruct);
    public T visit(If ifinstr);
    public T visit(IfElse ifelseinstr);
    public T visit(While whileinstr);



}
