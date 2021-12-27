package ast;
import java.util.ArrayList;

public class DefStruct implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident ;
    public ArrayList<Ast> decl_vars ;

    public DefStruct(Ast ident, ArrayList<Ast> decl_vars) {
        this.ident = ident ;
        this.decl_vars = decl_vars ;
    }

}
