package ast;

import java.util.ArrayList;

public class DeclVarStruct implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast struct_type;
    public ArrayList<Ast> struct_names ;

    public DeclVarStruct(Ast struct_type, ArrayList<Ast> struct_names){
        this.struct_type = struct_type;
        this.struct_names = struct_names;
    }

}

