package structureTds;

import ast.*;
import graphViz.GraphVizTds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class TdsVisitor implements AstVisitor<String> {
    
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
    public String visit(Fichier fichier) {
        graphviztds.addStartTable("global " + NumImbr);

        ArrayList<Ast> declarations = fichier.declarations;
        for (Ast declaration : declarations) {
            declaration.accept(this);
        }

        graphviztds.addEndTable();
        //test le main_non_def
        test.fonc_non_def("main");
        


        return null;
    }

    @Override
    public String visit(DefStruct def_struct) {
        Ident ident = (Ident) def_struct.ident;
        String nom = "struct " + ident.name;
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
        NumImbr--;

        return null;
    }

    @Override
    public String visit(DefFctInt def_fct_int) {
        Ident ident = (Ident) def_fct_int.ident;
        String nom = ident.name;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(ident.name);

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
    public String visit(DefFctIntParam def_fct_int_param) {
        Ident ident = (Ident) def_fct_int_param.ident;
        String nom = ident.name;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(ident.name);

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
    public String visit(DefFctStruct def_fct_struct) {
        Ident identType = (Ident) def_fct_struct.ident1;
        Ident identNom = (Ident) def_fct_struct.ident2;
        String nom = identNom.name;
        String type = "struct " + identType.name ;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(identNom.name);

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

        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public String visit(DefFctStructParam def_fct_struct_param) {
        Ident identType = (Ident) def_fct_struct_param.ident1;
        Ident identNom = (Ident) def_fct_struct_param.ident2;
        String nom = identNom.name;
        String type = "struct " + identType.name ;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(identNom.name);

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

        this.blocLabel.pop();
        NumImbr--;

        return null;
    }

    @Override
    public String visit(SizeOf sizeof) {
        return "int";
    }

    @Override
    public String visit(FctParam fct_param) {
        String name = ( (Ident) fct_param.ident).name;
        ArrayList<Ast> exprs = fct_param.exprs;
        int nb = exprs.size();
        String type = test.fonc_non_def(name);
        test.nombre_param(name, nb);
        return type;
    }

    @Override
    public String visit(Fct fct) {

        String name = ( (Ident) fct.ident).name;
        String type = test.fonc_non_def(name);
        test.nombre_param(name, 0);

        return type;
    }

    @Override
    public String visit(Sup comp) {
        String type1 = comp.left.accept(this);
        String type2 = comp.right.accept(this);
        String type_request = "int";
        test.test_type(">", type1, type_request);
        test.test_type(">", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Egal egal) {
        String type1 = egal.left.accept(this);
        String type2 = egal.right.accept(this);
        String type_request = "int";
        test.test_type("==", type1, type_request);
        test.test_type("==", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Mult mult) {
        String type1 = mult.left.accept(this);
        String type2 = mult.right.accept(this);
        String type_request = "int";
        test.test_type("*", type1, type_request);
        test.test_type("*", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Not ast) {
        String type = ast.ast.accept(this);
        String type_request = "int";
        test.test_type("!", type, type_request);
        return type_request;
    }

    @Override
    public String visit(Moinsunaire moinsunaire) {
        String type = moinsunaire.ast.accept(this);
        String type_request = "int";
        test.test_type("- (unaire)", type, type_request);
        return type_request;
    }

    @Override
    public String visit(Affect affect) {
        Ast left = affect.left;
        if ( !(left instanceof Ident || left instanceof Fleche)) {
            throw new RuntimeException("Erreur : affectation impossible");
        }
        String type1 = affect.left.accept(this);
        String type2 = affect.right.accept(this);
        test.test_type("=", type2, type1);
        return type1;
    }

    @Override
    public String visit(Bloc bloc) {

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
    public String visit(Div div) {
        String type1 = div.left.accept(this);
        String type2 = div.right.accept(this);
        String type_request = "int";
        test.test_type("/", type1, type_request);
        test.test_type("/", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Inegal inegal) {
        String type1 = inegal.left.accept(this);
        String type2 = inegal.right.accept(this);
        String type_request = "int";
        test.test_type("!=", type1, type_request);
        test.test_type("!=", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Inf inf) {
        String type1 = inf.left.accept(this);
        String type2 = inf.right.accept(this);
        String type_request = "int";
        test.test_type("<", type1, type_request);
        test.test_type("<", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(InfEgal infEgal) {
        String type1 = infEgal.left.accept(this);
        String type2 = infEgal.right.accept(this);
        String type_request = "int";
        test.test_type("<=", type1, type_request);
        test.test_type("<=", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(SupEgal supEgal) {
        String type1 = supEgal.left.accept(this);
        String type2 = supEgal.right.accept(this);
        String type_request = "int";
        test.test_type(">=", type1, type_request);
        test.test_type(">=", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(DeclVarInt declVarInt) {
        ArrayList<Ast> idents = declVarInt.ident ;

        Tds actualTable = test.TdsStack.pop();
        for (int i = 0; i < idents.size(); i++) {
            Ident ident = (Ident) idents.get(i) ;
            actualTable.addVariable(ident.name, "int");
            graphviztds.addElement(ident.name, "attribut", "int", "depl");
        }
        test.TdsStack.push(actualTable);
        return null;
    }

    @Override
    public String visit(DeclVarStruct declVarStruct) {
        ArrayList<Ast> idents = declVarStruct.struct_names ;
        Ident identType = (Ident) declVarStruct.struct_type ;
        String type = "struct " + identType.name ;

        Tds actualTable = test.TdsStack.pop();
        for (int i = 0; i < idents.size(); i++) {
            Ident ident = (Ident) idents.get(i) ;
            actualTable.addVariable(ident.name, type);
            graphviztds.addElement(ident.name, "attribut", type, "depl");
        }
        test.TdsStack.push(actualTable);
        return null;
    }

    @Override
    public String visit(Ident ident) {

        // ATTENTION on ne visite Ident que si c'est une variable

        String name = ((Ident) ident).name;
        String type = test.var_non_def(name);
        return type;
    }

    @Override
    public String visit(Int entier) {
        return "int";
    }

    @Override
    public String visit(ParamInt paramint) {

        Tds actualTable = test.TdsStack.pop();

        Ident ident = (Ident) paramint.ident;
        String nom = ident.name;

        actualTable.addParam(nom, "int");
        graphviztds.addElement(nom, "param", "int", "depl");

        test.TdsStack.push(actualTable);

        return null;
    }

    @Override
    public String visit(ParamStruct paramstruct) {
        Tds actualTable = test.TdsStack.pop();

        Ident ident = (Ident) paramstruct.struct_type;
        String type = "struct " + ident.name;

        ident = (Ident) paramstruct.struct_name;
        String nom = ident.name;

        actualTable.addParam(nom, type);
        graphviztds.addElement(nom, "param", type, "depl");

        test.TdsStack.push(actualTable);

        return null;
    }

    @Override
    public String visit(If ifinstr) {

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
    public String visit(IfElse ifelseinstr) {

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
    public String visit(While whileinstr) {

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
    public String visit(Return ret) {
        String type = ret.expr.accept(this);
        return type;
    }

    @Override
    public String visit(Et et) {
        String type1 = et.left.accept(this);
        String type2 = et.right.accept(this);
        String type_request = "int";
        test.test_type("&&", type1, type_request);
        test.test_type("&&", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Ou ou) {
        String type1 = ou.left.accept(this);
        String type2 = ou.right.accept(this);
        String type_request = "int";
        test.test_type("||", type1, type_request);
        test.test_type("||", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Plus plus) {
        String type1 = plus.left.accept(this);
        String type2 = plus.right.accept(this);
        String type_request = "int";
        test.test_type("+", type1, type_request);
        test.test_type("+", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Moins moins) {
        String type1 = moins.left.accept(this);
        String type2 = moins.right.accept(this);
        String type_request = "int";
        test.test_type("-", type1, type_request);
        test.test_type("-", type2, type_request);
        return type_request;
    }

    @Override
    public String visit(Fleche fleche) {
        
        Ast gauche = fleche.value;
        Ident droite = (Ident) fleche.ident;
        String typeg;
        String named = droite.name;

        if (gauche instanceof Int) {
            throw new RuntimeException("Erreur struct => entier -> ident");
        }
        else if (gauche instanceof SizeOf) {
            throw new RuntimeException("Erreur struct => size of -> ident");
        }
        else {
            typeg = gauche.accept(this);
        }

        String type = test.test_fleche_def(typeg, named);

         return type;
    }
}
