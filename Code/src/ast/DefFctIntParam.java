package ast;

import java.util.ArrayList;

public class DefFctIntParam implements Ast{
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast ident ;
    public ArrayList<Ast> params ;
    public Ast bloc ;

    public DefFctIntParam(Ast ident, ArrayList<Ast> params, Ast bloc) {
        this.ident = ident ;
        this.params = params ;
        this.bloc = bloc ;
    }
}
