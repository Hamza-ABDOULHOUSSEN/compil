package structureTds;

import ast.*;
import graphViz.GraphVizTds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TdsVisitor implements AstVisitor<Object> {

    public Hashtable<String, TdsFunction> TableFunction = new Hashtable<String, TdsFunction>();
    public Hashtable<String, TdsStruct> TableStruct = new Hashtable<String, TdsStruct>();
    public ArrayList<TdsBloc> TdsBloc = new ArrayList<TdsBloc>();

    public Stack<Tds> TdsStack = new Stack<Tds>();
    public Stack<String> blocLabel = new Stack<String>();
    public int NumImbr = 0;

    public GraphVizTds graphviztds = new GraphVizTds();

    public void createGraph(String filepath) throws IOException {
        graphviztds.dumpGraph(filepath);
    }

    @Override
    public Object visit(Fichier fichier) {
        graphviztds.addStartTable("global " + NumImbr);

        ArrayList<Ast> declarations = fichier.declarations;
        for (Ast declaration : declarations) {
            declaration.accept(this);
        }

        graphviztds.addEndTable();

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

        graphviztds.addStartTable("fonction : " + nom);

        NumImbr++;
        this.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        Bloc bloc = (Bloc) def_fct_int.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();

        this.TdsStack.pop();
        this.blocLabel.pop();
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
        this.blocLabel.push("fonction");

        graphviztds.addStartTable("fonction : " + nom);

        ArrayList<Ast> params = def_fct_int_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        Bloc bloc = (Bloc) def_fct_int_param.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();

        this.TdsStack.pop();
        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctStruct def_fct_struct) {
        Ident identType = (Ident) def_fct_struct.ident1;
        Ident identNom = (Ident) def_fct_struct.ident2;
        String nom = identNom.name;
        String type = "struct " + identType ;
        TdsFunction function_table = new TdsFunction(nom, type);
        this.TableFunction.put(nom, function_table);

        graphviztds.addStartTable("fonction : " + nom);

        NumImbr++;
        this.TdsStack.push(function_table);

        Bloc bloc = (Bloc) def_fct_struct.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();

        this.TdsStack.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctStructParam def_fct_struct_param) {
        Ident identType = (Ident) def_fct_struct_param.ident1;
        Ident identNom = (Ident) def_fct_struct_param.ident2;
        String nom = identNom.name;
        String type = "struct " + identType ;
        TdsFunction function_table = new TdsFunction(nom, type);
        this.TableFunction.put(nom, function_table);

        graphviztds.addStartTable("fonction : " + nom);

        NumImbr++;
        this.TdsStack.push(function_table);

        ArrayList<Ast> params = def_fct_struct_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        Bloc bloc = (Bloc) def_fct_struct_param.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();

        this.TdsStack.pop();
        NumImbr--;

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

        String label = blocLabel.lastElement();
        NumImbr++;
        TdsBloc tdsbloc = new TdsBloc(TdsStack.lastElement(), NumImbr);
        graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
        this.TdsStack.push(tdsbloc);
        blocLabel.push("bloc");

        ArrayList<Ast> vars = bloc.vars;
        for (Ast var : vars) {
            var.accept(this);
        }

        TdsStack.pop();
        blocLabel.pop();
        graphviztds.addEndTable();

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
        graphviztds.addElement(nom, "param", "int", "depl");

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
        graphviztds.addElement(nom, "param", "struct " + type, "depl");

        TdsStack.push(actualTable);

        return null;
    }

    @Override
    public Object visit(If ifinstr) {

        blocLabel.push("Bloc If");

        Bloc blocif = (Bloc) ifinstr.instruction;
        blocif.accept(this);

        blocLabel.pop();

        return null;
    }

    @Override
    public Object visit(IfElse ifelseinstr) {

        blocLabel.push("Bloc If");
        Bloc blocif = (Bloc) ifelseinstr.instruction1;
        blocif.accept(this);
        blocLabel.pop();

        blocLabel.push("Bloc Else");
        Bloc blocelse = (Bloc) ifelseinstr.instruction2;
        blocelse.accept(this);
        blocLabel.pop();

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
