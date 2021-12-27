package ast;

public interface AstVisitor<T> {

    T visit(Fichier fichier);
    T visit(DefStruct def_struct);
    T visit(DeclVarInt var);
    T visit(DefFctInt def_fct_int);
    T visit(DefFctIntParam def_fct_int_param);
    T visit(DefFctStruct def_fct_struct);
    T visit(DefFctStructParam def_fct_struct_param);
    T visit(SizeOf sizeof);
    T visit(Parenthese parenthese);
    T visit(FctParam fct_param);
    T visit(Fct fct);
    T visit(Sup comp);
    T visit(Egal egal);
    T visit(Mult mult);
    T visit(Not ast);
    T visit(Moinsunaire moinsunaire);
    T visit(Affect affect);
    T visit(Bloc bloc);
    T visit(Div div);
    T visit(Inegal inegal);
    T visit(Inf inf);
    T visit(InfEgal infEgal);
    T visit(SupEgal supEgal);
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
    T visit(Ou ou);
    T visit(Plus plus);
    T visit(Moins moins);
    T visit(Fleche fleche);

}
