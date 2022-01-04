import ast.*;
import tds.*;
import graphViz.GraphVizVisitor;

public class Main4 {
    public static void main(String[] args){
        TdsVisitor tdsViz = new TdsVisitor();
        ast.accept(tdsViz);
        System.out.println(tdsViz.table.toString());
    }
}