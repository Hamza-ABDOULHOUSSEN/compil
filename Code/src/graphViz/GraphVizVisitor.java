package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;

import ast.*;

public class GraphVizVisitor implements AstVisitor<String> {

    private int state;
    private String nodeBuffer;
    private String linkBuffer;
    private String endBuffer;

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

        String instructionsState =fichier.declarations.accept(this);

        this.addNode(nodeIdentifier, "Fichier");
        this.addTransition(nodeIdentifier, instructionsState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Ident ident) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, ident.name);

        return nodeIdentifier;

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

    //modif maha pour Decl_fct_int
    @Override
    public String visit(Decl_fct_int decl_fct_int) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_fct_int");

        String idfState = decl_fct_int.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = decl_fct_int.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //modif maha pour Decl_fct_int_param
    @Override
    public String visit(Decl_fct_int_param decl_fct_int_param) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_fct_int_param");

        String idfState = decl_fct_int_param.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:decl_fct_int_param.params){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }
        String idfState = decl_fct_int_param.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);


        return nodeIdentifier;
    }
    //Decl_fct_struct
    @Override
    public String visit(Decl_fct_struct decl_fct_struct) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_fct_struct");

        String idfState = decl_fct_struct.ident1.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        
        String idfState = decl_fct_struct.ident2.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = decl_fct_struct.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    // Decl_fct_struct_param
    @Override
    public String visit(Decl_fct_struct_param decl_fct_struct_param) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_fct_struct_param");

        String idfState = decl_fct_struct_param.ident1.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        
        String idfState = decl_fct_struct_param.ident2.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:decl_fct_struct_param.params){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        String idfState = decl_fct_struct_param.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Decl_fct_struct_param_vide
    @Override
    public String visit(Decl_fct_struct_param_vide decl_fct_struct_param_vide) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Decl_fct_struct_param_vide");

        String idfState = decl_fct_struct_param_vide.ident1.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        
        String idfState = decl_fct_struct_param_vide.ident2.accept(this);
        this.addTransition(nodeIdentifier, idfState);


        String idfState = decl_fct_struct_param_vide.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_sizeof
    @Override
    public String visit(Value_sizeof value_sizeof) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Value_sizeof");

        String idfState = value_sizeof.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_expr
    @Override
    public String visit(Value_expr value_expr) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Value_expr");

        String idfState = value_expr.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_list_expr
    @Override
    public String visit(Value_list_expr value_list_expr) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Value_list_expr");

        String idfState = value_list_expr.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:value_list_expr.exprs){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        return nodeIdentifier;
    }

    //Value_list_expr_vide
    @Override
    public String visit(Value_list_expr_vide value_list_expr_vide) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Value_list_expr_vide");

        String idfState = value_list_expr_vide.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Bloc
    @Override
    public String visit(Bloc bloc) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Bloc");

        for (Ast ast:bloc.vars){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        return nodeIdentifier;
    }

    //Egal
    @Override
    public String visit(Egal egal) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "==");

        String idfState = egal.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = egal.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    

    //Mult 
    @Override
    public String visit(Mult mult) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "*");

        String idfState = mult.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = mult.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Moinsunaire
    @Override
    public String visit(Moinsunaire moinsunaire) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "-");

        String idfState = moinsunaire.ast.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Affect
    @Override
    public String visit(Affect affect) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "=");

        String idfState = affect.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = affect.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Div
    @Override
    public String visit(Div div) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "/");

        String idfState = div.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = div.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Inegal 
    @Override
    public String visit(Inegal inegal) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "!=");

        String idfState = inegal.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = inegal.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Inf
    @Override
    public String visit(Inf inf) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "<");

        String idfState = inf.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = inf.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //InfEgal
    @Override
    public String visit(InfEgal infEgal) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "<=");

        String idfState = infEgal.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        String idfState = infEgal.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }
}
