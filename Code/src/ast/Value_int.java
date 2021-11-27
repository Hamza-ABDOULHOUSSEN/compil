package ast;

import com.sun.jdi.Value;

public class Value_int implements Ast {
    public <T> T accept(AstVisitor<T> visitor){ return visitor.visit(this); }

    public Ast INT ;

    public Value_int(Ast INT) {
        this.INT = INT ;
    }
}
