package ast;

public interface AstVisitor<T> {

    public T visit(Fichier fichier);
    public T visit(Decl_typ decl_typ);
    public T visit(DeclVarInt var);
    public T visit(Ident var);
    public T visit(Decl_fct_int decl_fct_int);
    public T visit(Decl_fct_int_param decl_fct_int_param);
    public T visit(Decl_fct_struct decl_fct_struct);
    public T visit(Decl_fct_struct_param decl_fct_struct_param);
    public T visit(Decl_fct_struct_param_vide decl_fct_struct_param_vide);
    public T visit(Value_int value_int);
    public T visit(Value_ident value_ident);
    public T visit(Value_sizeof value_sizeof);
    public T visit(Value_expr value_expr);
    public T visit(Value_list_expr value_list_expr);
    public T visit(Value_list_expr_vide value_list_expr_vide);
    public T visit(Comp_inf comp_inf);
    public T visit(Comp_inf_egal comp_inf_egal);
    public T visit(Comp_sup comp_sup);
    public T visit(Comp_sup_egal comp_sup_egal);
    public T visit(Egal egal);
    public T visit(Diff diff);
    public T visit(Mult mult);
    public T visit(Unaire unaire);






}
