package ast;

import java.util.ArrayList;

public class Decl_fct_struct_param_vide implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident1 ;
    public Ast ident2 ;
    public Ast bloc ;

    public Decl_fct_struct_param_vide(Ast ident1, Ast ident2, Ast bloc) {
        this.ident1 = ident1 ;
        this.ident2 = ident2 ;
        this.bloc = bloc ;
    }
}
