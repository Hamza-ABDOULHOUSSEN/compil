package ast;

public interface AstVisitor<T> {

     T visit(Fichier fichier);
     T visit(Decl_typ decl_typ);
     T visit(DeclVarInt declvarint);
     T visit(DeclVarStruct declvarstruct);
     T visit(Ident ident);
     T visit(Int entier);
     T visit(ParamInt paramint);
     T visit(ParamStruct paramstruct);
     T visit(If ifinstr);
     T visit(IfElse ifelseinstr);
     T visit(While whileinstr);
     T visit(Return ret);
     T visit(Et et);

}
