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
    public String visit(DeclVarInt declvarint) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "DeclInt");

        for (Ast ast:declvarint.ident){
            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(DeclVarStruct declvarstruct) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "DeclStruct");

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
        this.addNode(nodeIdentifier, "DefStruct");

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
    public String visit(ParamInt paramint) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Int");

        String idfState = paramint.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(ParamStruct paramstruct) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Struct");

        String idfState = paramstruct.struct_type.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = paramstruct.struct_name.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(If ifinstr) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "BlocIf");

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
        this.addNode(nodeIdentifier, "BlocIfElse");

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
        this.addNode(nodeIdentifier, "BlocWhile");

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
        this.addNode(nodeIdentifier, "BlocReturn");

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
    public String visit(Ou ou) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "||");

        String idfState = ou.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);
        idfState = ou.right.accept(this);
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

    @Override
    public String visit(DeclFctInt decl_fct_int) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "DefFctInt");

        String idfState = decl_fct_int.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        idfState = decl_fct_int.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //modif maha pour Decl_fct_int_param
    @Override
    public String visit(DeclFctIntParam decl_fct_int_param) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "DefFctInt");

        String idfState = decl_fct_int_param.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:decl_fct_int_param.params){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }
        idfState = decl_fct_int_param.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);


        return nodeIdentifier;
    }
    //Decl_fct_struct
    @Override
    public String visit(DeclFctStruct decl_fct_struct) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "DefFctStruct");

        String idfState = decl_fct_struct.ident1.accept(this);
        this.addTransition(nodeIdentifier, idfState);


        idfState = decl_fct_struct.ident2.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        idfState = decl_fct_struct.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    // Decl_fct_struct_param
    @Override
    public String visit(DeclFctStructParam decl_fct_struct_param) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "defFctStruct");

        String idfState = decl_fct_struct_param.ident1.accept(this);
        this.addTransition(nodeIdentifier, idfState);


        idfState = decl_fct_struct_param.ident2.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:decl_fct_struct_param.params){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        idfState = decl_fct_struct_param.bloc.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_sizeof
    @Override
    public String visit(Value_sizeof value_sizeof) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "sizeof");

        String idfState = value_sizeof.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_expr
    @Override
    public String visit(Parenthese parenthese) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "()");

        String idfState = parenthese.expr.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Value_list_expr
    @Override
    public String visit(FctParam fctparam) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Fct");

        String idfState = fctparam.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        for (Ast ast:fctparam.exprs){

            String astState = ast.accept(this);
            this.addTransition(nodeIdentifier, astState);

        }

        return nodeIdentifier;
    }

    //Value_list_expr_vide
    @Override
    public String visit(Fct fct) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Fct");

        String idfState = fct.ident.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Bloc
    @Override
    public String visit(Bloc bloc) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "Bloc");

        for (Ast ast:bloc.vars){

            if (ast != null) {
                String astState = ast.accept(this);
                this.addTransition(nodeIdentifier, astState);
            }

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

        idfState = egal.right.accept(this);
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

        idfState = mult.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Not ast) {
        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "!");

        String idfState = ast.ast.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Moinsunaire
    @Override
    public String visit(Moinsunaire moinsunaire) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, "MoinsUnaire");

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

        idfState = affect.right.accept(this);
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

        idfState = div.right.accept(this);
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

        idfState = inegal.right.accept(this);
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

        idfState = inf.right.accept(this);
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

        idfState = infEgal.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //Sup
    @Override
    public String visit(Sup sup) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, ">");

        String idfState = sup.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        idfState = sup.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

    //SupEgal
    @Override
    public String visit(SupEgal supEgal) {

        String nodeIdentifier = this.nextState();
        this.addNode(nodeIdentifier, ">=");

        String idfState = supEgal.left.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        idfState = supEgal.right.accept(this);
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;
    }

}
