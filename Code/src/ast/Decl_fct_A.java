package ast;
import java.util.ArrayList;

public class Decl_fct_A implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident ;
    public Ast param1 ; 
    public ArrayList<Ast> param2 ;
    public Ast bloc ;

    public Decl_fct_A(Ast ident, Ast param1, ArrayList<Ast> param2, Ast bloc) {
        this.ident = ident ;
        this.param1 = param1 ;
        this.param2 = param2 ;
        this.bloc = bloc ;
    }

}
