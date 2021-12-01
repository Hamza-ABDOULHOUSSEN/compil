package ast;

public class ParamStruct implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ident struct_type ;
    public Ident struct_name ;

    public ParamStruct(Ident struct_type, Ident struct_name){
        this.struct_type = struct_type;
        this.struct_name = struct_name ;
    }

}

