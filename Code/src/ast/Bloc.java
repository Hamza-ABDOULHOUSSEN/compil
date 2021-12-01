package ast;

import java.util.ArrayList;

public class Bloc implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public ArrayList<Ast> vars ;

    public Decl_fct_struct_param(ArrayList<Ast> vars) {
        this.vars = vars ;
    }
}
