package ast;

public class Int implements Ast{

    // Utile pour la dernière partie
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String val;

    public Int(String val){
        this.val = val;
    }

}
