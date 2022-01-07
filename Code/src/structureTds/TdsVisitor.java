package structureTds;

import ast.*;
import graphViz.GraphVizTds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class TdsVisitor implements AstVisitor<Object> {
    
    public Stack<String> blocLabel = new Stack<String>();
    public int NumImbr = 0;

    public GraphVizTds graphviztds = new GraphVizTds();
    public TestSemantique test = new TestSemantique();

    public void createGraph(String filepath) throws IOException {
        graphviztds.dumpGraph(filepath);
    }

    public void createBloc() {
        NumImbr++;
        String label = blocLabel.lastElement();
        TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);
        graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
        this.test.TdsStack.push(tdsbloc);

        NumImbr--;
        test.TdsStack.pop();
        graphviztds.addEndTable();
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
        Ident ident = (Ident) def_struct.ident;
        String nom = ident.name;
        TdsStruct struct_table = new TdsStruct(nom);
        this.test.TableStruct.put(nom, struct_table);

        NumImbr++;
        graphviztds.addStartTable("structure : " + nom + " " + NumImbr);
        this.test.TdsStack.push(struct_table);

        ArrayList<Ast> params = def_struct.decl_vars;
        for (Ast param : params) {
            param.accept(this);
        }

        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.test.struct_deja_def(ident.name);
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctInt def_fct_int) {
        Ident ident = (Ident) def_fct_int.ident;
        String nom = ident.name;
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.test.TableFunction.put(nom, function_table);

        NumImbr++;
        graphviztds.addStartTable("fonction : int " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        Bloc bloc = (Bloc) def_fct_int.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctIntParam def_fct_int_param) {
        Ident ident = (Ident) def_fct_int_param.ident;
        String nom = ident.name;
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.test.TableFunction.put(nom, function_table);

        NumImbr++;
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");
        graphviztds.addStartTable("fonction : int " + nom + " " + NumImbr);

        ArrayList<Ast> params = def_fct_int_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        Bloc bloc = (Bloc) def_fct_int_param.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctStruct def_fct_struct) {
        Ident identType = (Ident) def_fct_struct.ident1;
        Ident identNom = (Ident) def_fct_struct.ident2;
        String nom = identNom.name;
        String type = "struct " + identType.name ;
        TdsFunction function_table = new TdsFunction(nom, type);
        this.test.TableFunction.put(nom, function_table);

        NumImbr++;
        graphviztds.addStartTable("fonction : " + type + " * " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        Bloc bloc = (Bloc) def_fct_struct.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.test.struct_non_def(identType.name);
        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(DefFctStructParam def_fct_struct_param) {
        Ident identType = (Ident) def_fct_struct_param.ident1;
        Ident identNom = (Ident) def_fct_struct_param.ident2;
        String nom = identNom.name;
        String type = "struct " + identType.name ;
        TdsFunction function_table = new TdsFunction(nom, type);
        this.test.TableFunction.put(nom, function_table);

        NumImbr++;
        graphviztds.addStartTable("fonction : " + type + " * " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        ArrayList<Ast> params = def_fct_struct_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        Bloc bloc = (Bloc) def_fct_struct_param.bloc;
        bloc.accept(this);

        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.test.struct_non_def(identType.name);
        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public Object visit(SizeOf sizeof) {
        Ident ident = (Ident) sizeof.ident ;
        test.struct_non_def(ident.name);
        return null;
    }

    @Override
    public Object visit(Parenthese parenthese) {
        parenthese.expr.accept(this);
        return null;
    }

    @Override
    public Object visit(FctParam fct_param) {
        String name = ( (Ident) fct_param.ident).name;
        ArrayList<Ast> exprs = fct_param.exprs;
        int nb = exprs.size();
        test.fonc_non_def(name);
        test.nombre_param(name, nb);
        test.type_param(name, fct_param.exprs);

        return null;
    }

    @Override
    public Object visit(Fct fct) {

        String name = ( (Ident) fct.ident).name;
        test.fonc_non_def(name);
        test.nombre_param(name, 0);
        test.type_param(name, new ArrayList<Ast>());

        return null;
    }

    @Override
    public Object visit(Sup comp) {
        comp.left.accept(this);
        comp.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Egal egal) {
        egal.left.accept(this);
        egal.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Mult mult) {
        mult.left.accept(this);
        mult.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Not ast) {
        ast.ast.accept(this);
        return null;
    }

    @Override
    public Object visit(Moinsunaire moinsunaire) {
        moinsunaire.ast.accept(this);
        return null;
    }

    @Override
    public Object visit(Affect affect) {
        affect.left.accept(this);
        affect.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Bloc bloc) {

        String label = blocLabel.lastElement();

        if (label.equals("fonction")) {
            ArrayList<Ast> vars = bloc.vars;
            for (Ast var : vars) {
                if (var != null) {
                    var.accept(this);
                }
            }
        }
        else {
            NumImbr++;
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);
            blocLabel.push("bloc");

            ArrayList<Ast> vars = bloc.vars;
            for (Ast var : vars) {
                if (var != null) {
                    var.accept(this);
                }
            }

            test.TdsStack.pop();
            blocLabel.pop();
            graphviztds.addEndTable();
            NumImbr--;
        }

        return null;
    }

    @Override
    public Object visit(Div div) {
        div.left.accept(this);
        div.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Inegal inegal) {
        inegal.left.accept(this);
        inegal.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Inf inf) {
        inf.left.accept(this);
        inf.right.accept(this);
        return null;
    }

    @Override
    public Object visit(InfEgal infEgal) {
        infEgal.left.accept(this);
        infEgal.right.accept(this);
        return null;
    }

    @Override
    public Object visit(SupEgal supEgal) {
        supEgal.left.accept(this);
        supEgal.right.accept(this);
        return null;
    }

    @Override
    public Object visit(DeclVarInt declVarInt) {
        ArrayList<Ast> idents = declVarInt.ident ;

        Tds actualTable = test.TdsStack.pop();
        for (int i = 0; i < idents.size(); i++) {
            Ident ident = (Ident) idents.get(i) ;
            actualTable.addVariable(ident.toString(), "int");
            graphviztds.addElement(ident.name, "attribut", "int", "depl");
        }
        test.TdsStack.push(actualTable);
        return null;
    }

    @Override
    public Object visit(DeclVarStruct declVarStruct) {
        ArrayList<Ast> idents = declVarStruct.struct_names ;
        Ident identType = (Ident) declVarStruct.struct_type ;
        String type = "struct " + identType.name ;

        Tds actualTable = test.TdsStack.pop();
        for (int i = 0; i < idents.size(); i++) {
            Ident ident = (Ident) idents.get(i) ;
            actualTable.addVariable(ident.toString(), type);
            graphviztds.addElement(ident.name, "attribut", type, "depl");
        }
        test.TdsStack.push(actualTable);
        test.struct_non_def(identType.name);
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

        Tds actualTable = test.TdsStack.pop();

        Ident ident = (Ident) paramint.ident;
        String nom = ident.name;

        actualTable.addParam(nom, "int");
        graphviztds.addElement(nom, "param", "int", "depl");

        test.TdsStack.push(actualTable);

        return null;
    }

    @Override
    public Object visit(ParamStruct paramstruct) {
        Tds actualTable = test.TdsStack.pop();

        Ident ident = (Ident) paramstruct.struct_type;
        String type = "struct " + ident.name;

        ident = (Ident) paramstruct.struct_name;
        String nom = ident.name;

        actualTable.addParam(nom, type);
        graphviztds.addElement(nom, "param", type, "depl");

        test.TdsStack.push(actualTable);
        this.test.struct_non_def(ident.name);


        return null;
    }

    @Override
    public Object visit(If ifinstr) {

        ifinstr.expr.accept(this);

        blocLabel.push("Bloc If");

        Ast blocif = ifinstr.instruction;
        if (blocif == null) {
            createBloc();
        }
        else if (blocif instanceof Bloc || blocif instanceof If || blocif instanceof IfElse || blocif instanceof While ) {
            blocif.accept(this);
        }
        else {
            createBloc();
            blocif.accept(this);
        }
        
        blocLabel.pop();

        return null;
    }

    @Override
    public Object visit(IfElse ifelseinstr) {

        ifelseinstr.expr.accept(this);

        blocLabel.push("Bloc If");
        Ast blocif = ifelseinstr.instruction1;

        if (blocif == null) {
            createBloc();
        }
        else if (blocif instanceof Bloc || blocif instanceof If || blocif instanceof IfElse || blocif instanceof While ) {
            blocif.accept(this);
        }
        else {
            createBloc();
            blocif.accept(this);
        }
        
        blocLabel.pop();

        blocLabel.push("Bloc Else");
        Ast blocelse = ifelseinstr.instruction2;

        if (blocelse == null) {
            createBloc();
        }
        else if (blocelse instanceof Bloc || blocelse instanceof If || blocelse instanceof IfElse || blocelse instanceof While ) {
            blocelse.accept(this);
        }
        else {
            createBloc();
            blocelse.accept(this);
        }
        
        blocLabel.pop();

        return null;
    }

    @Override
    public Object visit(While whileinstr) {

        whileinstr.expr.accept(this);

        blocLabel.push("Bloc While");

        Ast blocwhile = whileinstr.instruction;

        if (blocwhile == null) {
            createBloc();
        }
        else if (blocwhile instanceof Bloc || blocwhile instanceof If || blocwhile instanceof IfElse || blocwhile instanceof While ) {
            blocwhile.accept(this);
        }
        else {
            createBloc();
            blocwhile.accept(this);
        }
        
        blocLabel.pop();

        return null;
    }

    @Override
    public Object visit(Return ret) {
        ret.expr.accept(this);
        return null;
    }

    @Override
    public Object visit(Et et) {
        et.left.accept(this);
        et.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Ou ou) {
        ou.left.accept(this);
        ou.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Plus plus) {
        plus.left.accept(this);
        plus.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Moins moins) {
        moins.left.accept(this);
        moins.right.accept(this);
        return null;
    }

    @Override
    public Object visit(Fleche fleche) {
        fleche.value.accept(this);
        return null;
    }
}
