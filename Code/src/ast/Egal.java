package ast;

import java.util.ArrayList;

public class Egal implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public ArrayList<Ast> ou ;

    public Egal(ArrayList<Ast> ou) {
        this.ou = ou ;
    }
}