package Code.src.ast;
import java.util.ArrayList;

public class Decl_fct_B implements Ast  {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ident1 ;
    public Ast ident2 ;
    public Ast param1 ; 
    public ArrayList<Ast> param2 ;
    public Ast bloc ;

    public Decl_fct_B(Ast ident1, Ast ident2, Ast param1, ArrayList<Ast> param2, Ast bloc) {
        this.ident1 = ident1 ;
        this.ident2 = ident2 ;
        this.param1 = param1 ;
        this.param2 = param2 ;
        this.bloc = bloc ;
    }

}
