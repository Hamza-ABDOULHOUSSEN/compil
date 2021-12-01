package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(DeclVarInt var);
    public T visit(Decl_fct_int decl_fct_int);
    public T visit(Decl_fct_int_param decl_fct_int_param);
    public T visit(Decl_fct_struct decl_fct_struct);
    public T visit(Decl_fct_struct_param decl_fct_struct_param);
    public T visit(Decl_fct_struct_param_vide decl_fct_struct_param_vide);
    public T visit(Value_sizeof value_sizeof);
    public T visit(Value_expr value_expr);
    public T visit(Value_list_expr value_list_expr);
    public T visit(Value_list_expr_vide value_list_expr_vide);
    public T visit(Sup comp);
    public T visit(Egal egal);
    public T visit(Diff diff);
    public T visit(Mult mult);
    public T visit(Not ast);
    public T visit(Moinsunaire moinsunaire);
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
    T visit(Plus plus);
    T visit(Moins moins);
    T visit(Fleche fleche);

}
