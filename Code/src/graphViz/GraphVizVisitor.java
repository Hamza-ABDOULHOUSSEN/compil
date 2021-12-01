package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;

import ast.*;

public class GraphVizVisitor implements AstVisitor<String> {

    private int state;
    private String nodeBuffer;
    private String linkBuffer;
    private final String endBuffer;

    public GraphVizVisitor(){
        this.state = 0;
        this.nodeBuffer = "digraph \"ast\"{\n\n\tnodesep=1;\n\tranksep=1;\n\n";
        this.linkBuffer = "\n";
        this.endBuffer = "}\n";
    }

    public void dumpGraph(String filepath) throws IOException{

        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.nodeBuffer + this.linkBuffer + this.endBuffer;
        byte[] strToBytes = buffer.getBytes();

        output.write(strToBytes);

        output.close();

    }


    private String nextState(){
        int returnedState = this.state;
        this.state++;
        return "N"+ returnedState;
    }

    private void addTransition(String from,String dest){
        this.linkBuffer += String.format("\t%s -> %s; \n", from,dest);

    }

    private void addNode(String node,String label){
        this.nodeBuffer += String.format("\t%s [label=\"%s\", shape=\"box\"];\n", node,label);

    }

    @Override
    public String visit(Fichier fichier) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Fichier");

        for (Ast ast:fichier.declarations) {
            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);
        }

        return nodeIdentifier;

    }

    @Override
    public String visit(Ident ident) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, ident.name);

        return nodeIdentifier;

    }

    @Override
    public String visit(Decl_fct_int decl_fct_int) {
        return null;
    }

    @Override
    public String visit(Decl_fct_int_param decl_fct_int_param) {
        return null;
    }

    @Override
    public String visit(Decl_fct_struct decl_fct_struct) {
        return null;
    }

    @Override
    public String visit(Decl_fct_struct_param decl_fct_struct_param) {
        return null;
    }

    @Override
    public String visit(Decl_fct_struct_param_vide decl_fct_struct_param_vide) {
        return null;
    }

    @Override
    public String visit(Value_sizeof value_sizeof) {
        return null;
    }

    @Override
    public String visit(Value_expr value_expr) {
        return null;
    }

    @Override
    public String visit(Value_list_expr value_list_expr) {
        return null;
    }

    @Override
    public String visit(Value_list_expr_vide value_list_expr_vide) {
        return null;
    }

    @Override
    public String visit(Sup comp) {
        return null;
    }

    @Override
    public String visit(Egal egal) {
        return null;
    }

    @Override
    public String visit(Diff diff) {
        return null;
    }

    @Override
    public String visit(Mult mult) {
        return null;
    }

    @Override
    public String visit(Not ast) {
        return null;
    }

    @Override
    public String visit(Moinsunaire moinsunaire) {
        return null;
    }

    @Override
    public String visit(DeclVarInt declvarint) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_Var_Int");

        for (Ast ast:declvarint.ident){
            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(DeclVarStruct declvarstruct) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_Var_Struct");

        String astState = declvarstruct.struct_type.accept(this);
        this.addTransition(nodeIdentifier, astState);

        for (Ast ast:declvarstruct.struct_names){
            astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(Decl_typ decltype) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_typ");

        String idfState = decltype.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:decltype.decl_vars){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        return nodeIdentifier;
    }

    @Override
    public String visit(Int entier) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, entier.val);

        return nodeIdentifier;
    }

    @Override
    public String visit(Affect affect) {
        return null;
    }

    @Override
    public String visit(Bloc bloc) {
        return null;
    }

    @Override
    public String visit(Div div) {
        return null;
    }

    @Override
    public String visit(Inegal inegal) {
        return null;
    }

    @Override
    public String visit(Inf inf) {
        return null;
    }

    @Override
    public String visit(InfEgal infEgal) {
        return null;
    }

    @Override
    public String visit(SupEgal supEgal) {
        return null;
    }

    @Override
    public String visit(ParamInt paramint) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Param_int");

        String idfState = paramint.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(ParamStruct paramstruct) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Param_struct");

        String idfState = paramstruct.struct_type.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = paramstruct.struct_name.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(If ifinstr) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "If");

        String idfState = ifinstr.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        Ast instr = ifinstr.instruction;
        if (instr != null) {
            idfState = instr.accept(this);
            this.addTransition(nodeIdentifier, idfState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(IfElse ifelseinstr) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "IfElse");

        String idfState = ifelseinstr.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        Ast instr = ifelseinstr.instruction1;
        if (instr != null) {
            idfState = instr.accept(this);
            this.addTransition(nodeIdentifier, idfState);
        }
        instr = ifelseinstr.instruction2;
        if (instr != null) {
            idfState = instr.accept(this);
            this.addTransition(nodeIdentifier, idfState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(While whileinstr) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "While");

        String idfState = whileinstr.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        Ast instr = whileinstr.instruction;
        if (instr != null) {
            idfState = instr.accept(this);
            this.addTransition(nodeIdentifier, idfState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(Return ret) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Return");

        String idfState = ret.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Et et) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "&&");

        String idfState = et.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = et.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Plus plus) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "+");

        String idfState = plus.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = plus.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Moins moins) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "-");

        String idfState = moins.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = moins.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Fleche fleche) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "->");

        String idfState = fleche.value.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = fleche.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

}
