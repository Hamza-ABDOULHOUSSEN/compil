package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;

import ast.Ast;
import ast.AstVisitor;
import ast.Fichier;
import ast.Ident;
import ast.DeclVarInt;

public class GraphVizVisitor implements AstVisitor<String> {

    private int state;
    private String nodeBuffer;
    private String linkBuffer;

    public GraphVizVisitor(){
        this.state = 0;
        this.nodeBuffer = "digraph \"ast\"{\n\n\tnodesep=1;\n\tranksep=1;\n\n";
        this.linkBuffer = "\n";
    }

    public void dumpGraph(String filepath) throws IOException{
            
        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.nodeBuffer + this.linkBuffer;
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

        String idfState = declvarint.ident.accept(this);

        this.addNode(nodeIdentifier, "Decl_Var_Int");
        this.addTransition(nodeIdentifier, idfState);

        return nodeIdentifier;

    }
    
}