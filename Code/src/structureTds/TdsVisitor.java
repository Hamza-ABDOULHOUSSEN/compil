package structureTds;

import ast.*;
import graphViz.GraphVizTds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TdsVisitor implements AstVisitor {

    public Hashtable<String, TdsFunction> TableFunction = new Hashtable<String, TdsFunction>();
    public Hashtable<String, TdsStruct> TableStruct = new Hashtable<String, TdsStruct>();
    public ArrayList<TdsBloc> TdsBloc = new ArrayList<TdsBloc>();

    public Stack<Tds> TdsStack = new Stack<Tds>();
    public int NumImbr = 0;

    public void createGraph(String filepath) throws IOException {
        GraphVizTds graphviz = new GraphVizTds();
        graphviz.createGraph(TableFunction, TableStruct, TdsBloc);
        graphviz.dumpGraph(filepath);
    }

    @Override
    public Object visit(Fichier fichier) {
        return null;
    }

    @Override
    public Object visit(DefStruct def_struct) {
        return null;
    }

    @Override
    public Object visit(DeclVarInt var) {
        return null;
    }

    @Override
    public Object visit(DefFctInt def_fct_int) {
        Ident ident = (Ident) def_fct_int.ident;
        String nom = ident.name;
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.TableFunction.put(nom, function_table);

        NumImbr++;
        this.TdsStack.push(function_table);

        Bloc bloc = (Bloc) def_fct_int.bloc;
        bloc.accept(this);

        this.TdsStack.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctIntParam def_fct_int_param) {
        Ident ident = (Ident) def_fct_int_param.ident;
        String nom = ident.name;
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.TableFunction.put(nom, function_table);
        NumImbr++;
        this.TdsStack.push(function_table);

        ArrayList<Ast> params = def_fct_int_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        Bloc bloc = (Bloc) def_fct_int_param.bloc;
        bloc.accept(this);

        this.TdsStack.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctStruct def_fct_struct) {
        return null;
    }

    @Override
    public Object visit(DefFctStructParam def_fct_struct_param) {
        return null;
    }

    @Override
    public Object visit(SizeOf sizeof) {
        return null;
    }

    @Override
    public Object visit(Parenthese parenthese) {
        return null;
    }

    @Override
    public Object visit(FctParam fct_param) {
        return null;
    }

    @Override
    public Object visit(Fct fct) {
        return null;
    }

    @Override
    public Object visit(Sup comp) {
        return null;
    }

    @Override
    public Object visit(Egal egal) {
        return null;
    }

    @Override
    public Object visit(Mult mult) {
        return null;
    }

    @Override
    public Object visit(Not ast) {
        return null;
    }

    @Override
    public Object visit(Moinsunaire moinsunaire) {
        return null;
    }

    @Override
    public Object visit(Affect affect) {
        return null;
    }

    @Override
    public Object visit(Bloc bloc) {
        return null;
    }

    @Override
    public Object visit(Div div) {
        return null;
    }

    @Override
    public Object visit(Inegal inegal) {
        return null;
    }

    @Override
    public Object visit(Inf inf) {
        return null;
    }

    @Override
    public Object visit(InfEgal infEgal) {
        return null;
    }

    @Override
    public Object visit(SupEgal supEgal) {
        return null;
    }

    @Override
    public Object visit(DeclVarStruct declvarstruct) {
        return null;
    }

    @Override
    public Object visit(Ident ident) {
        return null;
    }

    @Override
    public Object visit(Int entier) {
        return null;
    }

    @Override
    public Object visit(ParamInt paramint) {

        Tds actualTable = TdsStack.pop();

        Ident ident = (Ident) paramint.ident;
        String nom = ident.name;

        actualTable.addParam(nom, "int");

        TdsStack.push(actualTable);

        return null;
    }

    @Override
    public Object visit(ParamStruct paramstruct) {
        Tds actualTable = TdsStack.pop();

        Ident ident = (Ident) paramstruct.struct_type;
        String type = ident.name;

        ident = (Ident) paramstruct.struct_name;
        String nom = ident.name;

        actualTable.addParam(nom, type);

        TdsStack.push(actualTable);

        return null;
    }

    @Override
    public Object visit(If ifinstr) {
        return null;
    }

    @Override
    public Object visit(IfElse ifelseinstr) {
        return null;
    }

    @Override
    public Object visit(While whileinstr) {
        return null;
    }

    @Override
    public Object visit(Return ret) {
        return null;
    }

    @Override
    public Object visit(Et et) {
        return null;
    }

    @Override
    public Object visit(Ou ou) {
        return null;
    }

    @Override
    public Object visit(Plus plus) {
        return null;
    }

    @Override
    public Object visit(Moins moins) {
        return null;
    }

    @Override
    public Object visit(Fleche fleche) {
        return null;
    }
}
