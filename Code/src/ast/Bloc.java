package ast;

import java.util.ArrayList;

public class Bloc implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public ArrayList<Ast> vars ;

    public Bloc(ArrayList<Ast> vars) {
        this.vars = vars ;
    }
}
