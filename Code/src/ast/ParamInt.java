package ast;

import java.util.ArrayList;

public class ParamInt implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident ;

    public ParamInt(Ident ident){
        this.ident = ident;
    }

}

