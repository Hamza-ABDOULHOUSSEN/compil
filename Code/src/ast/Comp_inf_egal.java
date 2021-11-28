package ast;

import java.util.ArrayList;

public class Comp_inf_egal implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public ArrayList<Ast> plus ;

    public Comp_inf_egal(ArrayList<Ast> plus) {
        this.plus = plus ;
    }
}