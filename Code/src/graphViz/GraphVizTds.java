package graphViz;

import structureTds.TdsBloc;
import structureTds.TdsFunction;
import structureTds.TdsStruct;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Stack;

public class GraphVizTds /*implements AstVisitor<String>*/ {

    private int state;
    private final String startBuffer;
    private String nodeBuffer;
    private String linkBuffer;
    private final String endBuffer;

    public Stack<String> table_buffer_stack = new Stack<String>();
    public Stack<String> table_node_stack = new Stack<String>();

    public GraphVizTds(){
        this.state = 0;
        this.startBuffer = "digraph \"tds\"{\n\n";
        this.nodeBuffer = "\tnode [fontname=\"Arial\"];\n\n";
        this.linkBuffer = "\n";
        this.endBuffer = "}\n";
    }

    public void dumpGraph(String filepath) throws IOException{

        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.startBuffer + this.nodeBuffer + this.linkBuffer + this.endBuffer;
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

    public void addStartTable(String label) {
        String newNodeIdentifier = this.nextState();
        String newTable = String.format("\t%s [shape=record label=\"{ %s\n", newNodeIdentifier, label);
        if (! newNodeIdentifier.equals("N0")) {
            addTransition(table_node_stack.lastElement(), newNodeIdentifier);
        }
        this.table_node_stack.push(newNodeIdentifier);
        this.table_buffer_stack.push(newTable);
    }

    public void addEndTable() {
        this.table_node_stack.pop();
        String TableBuffer = this.table_buffer_stack.pop();
        TableBuffer += "\t}\"];\n\n";
        this.nodeBuffer += TableBuffer;
    }

    // C'EST DIFFERENT POUR LES FONCTIONS A VOIR !!!!

    public void addElement(String name, String attrib, String type, String depl){
        String TableBuffer = this.table_buffer_stack.pop();
        TableBuffer += String.format("\t| { %s | %s | %s | %s }\n", name, attrib, type, depl);
        this.table_buffer_stack.push(TableBuffer);
    }

    public void createGraph(Hashtable<String, TdsFunction> TableFunction, Hashtable<String, TdsStruct> TableStruct, ArrayList<TdsBloc>TdsBloc) {
        addStartTable("global 0-0");

        // parcours fonctions
        for (String f : TableFunction.keySet()) {
            TdsFunction tds = TableFunction.get(f);

            addStartTable( "fonction : " + f);

            LinkedHashMap<String, String> params = tds.params;
            for (String nom : params.keySet()) {
                String type = params.get(nom);
                addElement(nom, "param", type, "depl");
            }

            LinkedHashMap<String, String> variables = tds.variables;
            for (String nom : variables.keySet()) {
                String type = variables.get(nom);
                addElement(nom, "variable", type, "depl");
            }

            addEndTable();
        }

        //parcours structures
        for (String s : TableStruct.keySet()) {
            TdsStruct tds = TableStruct.get(s);

            addStartTable("structure : " + s);

            LinkedHashMap<String, String> params = tds.params;
            for (String nom : params.keySet()) {
                String type = params.get(nom);
                addElement(nom, "param", type, "depl");
            }

            addEndTable();
        }

        //parcours bloc
        /* AIIIIILLLL RIENNN NE VASSSS C'EST TROP DURE DE FAIRE CA ITERATIVEMENT
        FAUT LE FAIRE PENDANT LE PARCOURS DE L'AST !!!!!!!!!!!!!!!!!!!!!!!!!
         */

        addEndTable();
    }

}
