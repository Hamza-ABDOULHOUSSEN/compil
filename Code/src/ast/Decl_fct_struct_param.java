package ast;

import java.util.ArrayList;

public class Decl_fct_struct_param implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident1 ;
    public Ast ident2 ;
    public ArrayList<Ast> params ;
    public Ast bloc ;

    public Decl_fct_struct_param(Ast ident1, Ast ident2, ArrayList<Ast> params, Ast bloc) {
        this.ident1 = ident1 ;
        this.ident2 = ident2 ;
        this.params = params ;
        this.bloc = bloc ;
    }
}
