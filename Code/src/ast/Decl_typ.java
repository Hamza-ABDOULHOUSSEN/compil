package ast;
import java.util.ArrayList;

public class Decl_typ implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident ;
    public ArrayList<Ast> decl_vars ;

    public Decl_typ(Ast ident, ArrayList<Ast> decl_vars) {
        this.ident = ident ;
        this.decl_vars = decl_vars ;
    }

}
