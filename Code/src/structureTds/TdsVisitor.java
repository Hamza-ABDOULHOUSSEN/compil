package structureTds;

import ast.*;
import graphViz.GraphVizTds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class TdsVisitor implements AstVisitor<String> {

    String ANSI_RED = "\u001B[31m";
    String ANSI_RESET = "\u001B[0m";
    
    public Stack<String> blocLabel = new Stack<String>();
    public int NumImbr = 0;
    public int depl = 1;

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
        String retour = test.fonc_non_def("main");
        if (! retour.equals("erreur")) {
            test.test_main();
        }

        return null;
    }

    @Override
    public String visit(DefStruct def_struct) {
        Ident ident = (Ident) def_struct.ident;
        String nom = "struct " + ident.name;

        this.test.struct_deja_def(ident.name);

        // Creation de la structure et ajout dans la table
        TdsStruct struct_table = new TdsStruct(nom);
        this.test.TableStruct.put(nom, struct_table);

        // Creation du bloc et ajout dans la stack
        NumImbr++;
        graphviztds.addStartTable("structure : " + nom + " " + NumImbr);
        this.test.TdsStack.push(struct_table);

        // visite des paramètres
        ArrayList<Ast> params = def_struct.decl_vars;
        for (Ast param : params) {
            param.accept(this);
        }

        // fin du bloc et on le retire de la stack
        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        NumImbr--;
        depl=1;

        return "void";
    }

    @Override
    public String visit(DefFctInt def_fct_int) {
        Ident ident = (Ident) def_fct_int.ident;
        String nom = ident.name;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(ident.name);

        // Creation de la fonction et ajout dans la table
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.test.TableFunction.put(nom, function_table);

        // Creation du bloc et ajout dans la stack
        NumImbr++;
        graphviztds.addStartTable("fonction : int " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        // visite des blocs de la fonction
        Bloc bloc = (Bloc) def_fct_int.bloc;
        String contient_return = bloc.accept(this);
        this.test.return_bloc(nom, contient_return);

        // fin du bloc et on le retire de la stack
        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.blocLabel.pop();
        NumImbr--;
        depl=1;

        return "void";
    }

    @Override
    public String visit(DefFctIntParam def_fct_int_param) {
        Ident ident = (Ident) def_fct_int_param.ident;
        String nom = ident.name;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(ident.name);

        // Creation de la fonction et ajout dans la table
        TdsFunction function_table = new TdsFunction(nom, "int");
        this.test.TableFunction.put(nom, function_table);

        // Creation du bloc et ajout dans la stack
        Bloc bloc = (Bloc) def_fct_int_param.bloc;
        NumImbr++;
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");
        graphviztds.addStartTable("fonction : int " + nom + " " + NumImbr);

        // visite des paramètres
        ArrayList<Ast> params = def_fct_int_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        // visite des blocs de la fonction
        String contient_return = bloc.accept(this);
        this.test.return_bloc(nom, contient_return);

        // fin du bloc et on le retire de la stack
        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.blocLabel.pop();
        NumImbr--;
        depl=1;

        return "void";
    }

    @Override
    public String visit(DefFctStruct def_fct_struct) {
        Ident identType = (Ident) def_fct_struct.ident1;
        Ident identNom = (Ident) def_fct_struct.ident2;
        String nom = identNom.name;
        String type = "struct " + identType.name ;

        //test pour savoir si la fonction a deja été utilisee
        this.test.fonc_deja_def(identNom.name);

        // Creation de la structure et ajout dans la table
        TdsFunction function_table = new TdsFunction(nom, type);
        this.test.TableFunction.put(nom, function_table);

        // Creation du bloc et ajout dans la stack
        Bloc bloc = (Bloc) def_fct_struct.bloc;
        NumImbr++;
        graphviztds.addStartTable("fonction : " + type + " * " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        // visite des blocs de la fonction
        String contient_return = bloc.accept(this);
        this.test.return_bloc(nom, contient_return);

        // fin du bloc et on le retire de la stack
        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.test.struct_non_def(identType.name);
        this.blocLabel.pop();
        NumImbr--;
        depl=1;

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

        // Creation de la structure et ajout dans la table
        TdsFunction function_table = new TdsFunction(nom, type);
        this.test.TableFunction.put(nom, function_table);

        // Creation du bloc et ajout dans la stack
        Bloc bloc = (Bloc) def_fct_struct_param.bloc;
        NumImbr++;
        graphviztds.addStartTable("fonction : " + type + " * " + nom + " " + NumImbr);
        this.test.TdsStack.push(function_table);
        this.blocLabel.push("fonction");

        // visite des paramètres
        ArrayList<Ast> params = def_fct_struct_param.params;
        for (Ast param : params) {
            param.accept(this);
        }

        // visite des blocs de la fonction
        String contient_return = bloc.accept(this);
        this.test.return_bloc(nom, contient_return);

        // fin du bloc et on le retire de la stack
        graphviztds.addEndTable();
        this.test.TdsStack.pop();
        this.test.struct_non_def(identType.name);
        this.blocLabel.pop();
        NumImbr--;
        depl=1;

        return "void";
    }

    @Override
    public String visit(SizeOf sizeof) {
        Ident ident = (Ident) sizeof.ident ;
        test.struct_non_def(ident.name);
        return "int";
    }

    @Override
    public String visit(FctParam fct_param) {
        String name = ( (Ident) fct_param.ident).name;
        ArrayList<Ast> exprs = fct_param.exprs;
        ArrayList<String> declParamTypes = new ArrayList<String>() ;
        for (Ast expr : exprs) {
            declParamTypes.add(expr.accept(this)) ;
        }

        int nb = exprs.size();

        //test fonction non definie
        String type = test.fonc_non_def(name);

        if (! type.equals("erreur")) {
            //test nombre de paramètres si definie
            String erreur = test.nombre_param(name, nb);
            if (! erreur.equals("erreur")) {
                test.type_param(name, declParamTypes);
            }
            return type;
        }

        return "erreur";

    }

    @Override
    public String visit(Fct fct) {

        String name = fct.ident.name;

        //test fonction non definie
        String type = test.fonc_non_def(name);

        if (! type.equals("erreur")) {
            //test nombre de paramètres si definie
            test.nombre_param(name, 0);
            return type;
        }

        return "erreur";
    }

    @Override
    public String visit(Sup comp) {
        String type1 = comp.left.accept(this);
        String type2 = comp.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type(">", type1, type_request);
            test.test_type(">", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Egal egal) {
        String type1 = egal.left.accept(this);
        String type2 = egal.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("==", type1, type_request);
            test.test_type("==", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Mult mult) {
        String type1 = mult.left.accept(this);
        String type2 = mult.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("*", type1, type_request);
            test.test_type("*", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Not ast) {
        String type = ast.ast.accept(this);
        String type_request = "int";
        if (!type.equals("erreur")) {
            test.test_type("!", type, type_request);
            return type_request;
        }

        return "erreur";
    }

    @Override
    public String visit(Moinsunaire moinsunaire) {
        String type = moinsunaire.ast.accept(this);
        String type_request = "int";

        if (!type.equals("erreur")) {
            test.test_type("- (unaire)", type, type_request);
            return type_request;
        }

        return "erreur";
    }

    @Override
    public String visit(Affect affect) {
        Ast left = affect.left;

        //test si l'affection est possible
        if ( !(left instanceof Ident || left instanceof Fleche)) {
            System.out.println(ANSI_RED + "Erreur : affectation impossible" + ANSI_RESET);
        }

        //test type
        String type1 = affect.left.accept(this);
        String type2 = affect.right.accept(this);
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("=", type2, type1);
        }

        return "void";
    }

    @Override
    public String visit(Bloc bloc) {

        //recupère le label (bloc if, while...)
        String label = blocLabel.lastElement();

        String contient_return = "";
        int save_depl = depl;

        //si c'est une fonction, on change pas de bloc
        //on ajoute directement les attributs
        if (label.equals("fonction")) {
            ArrayList<Ast> vars = bloc.vars;
            for (Ast var : vars) {
                if (var != null) {
                    if (var instanceof Bloc) {
                        blocLabel.push("bloc");
                        String contient_return_bloc = var.accept(this);
                        if (contient_return_bloc.equals("return")) {
                            contient_return = "return";
                        }
                        blocLabel.pop();
                    }
                    else {
                        String contient_return_bloc = var.accept(this);
                        if (contient_return_bloc.equals("return")) {
                            contient_return = "return";
                        }
                    }

                }
            }
            depl = save_depl;
        }

        else {
            // Creation du bloc et ajout dans la stack
            NumImbr++;
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);
            blocLabel.push("bloc");

            // visite des instructions
            ArrayList<Ast> vars = bloc.vars;
            for (Ast var : vars) {
                if (var != null) {
                    String contient_return_bloc = var.accept(this);
                    if (contient_return_bloc.equals("return")) {
                        contient_return = "return";
                    }
                }
            }

            // fin du bloc et on le retire de la stack
            depl = save_depl;
            test.TdsStack.pop();
            blocLabel.pop();
            graphviztds.addEndTable();
            NumImbr--;
        }

        return contient_return;
    }

    @Override
    public String visit(Div div) {
        String type1 = div.left.accept(this);
        Ast right = div.right;

        if (right instanceof Int) {
            String value = ((Int) right).val;
            test.divis_zero(value);
        }

        String type2 = right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("/", type1, type_request);
            test.test_type("/", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Inegal inegal) {
        String type1 = inegal.left.accept(this);
        String type2 = inegal.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("!=", type1, type_request);
            test.test_type("!=", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Inf inf) {
        String type1 = inf.left.accept(this);
        String type2 = inf.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("<", type1, type_request);
            test.test_type("<", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(InfEgal infEgal) {
        String type1 = infEgal.left.accept(this);
        String type2 = infEgal.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("<=", type1, type_request);
            test.test_type("<=", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(SupEgal supEgal) {
        String type1 = supEgal.left.accept(this);
        String type2 = supEgal.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type(">=", type1, type_request);
            test.test_type(">=", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(DeclVarInt declVarInt) {
        ArrayList<Ast> idents = declVarInt.ident ;

        for (int i = 0; i < idents.size(); i++) {
            Ident ident = (Ident) idents.get(i) ;
            String name = ident.name;

            test.var_deja_def(name);

            //récupération du bloc actuel
            Tds actualTable = test.TdsStack.pop();

            //ajout des variables dans la table et le graph de la TDS
            actualTable.addVariable(name, "int");
            String deplacement = String.valueOf(depl*4);
            graphviztds.addElement(name, "attribut", "int", deplacement);
            depl++;

            //on remet le bloc ectuel qu'on a retiré après l'avoir modifié
            test.TdsStack.push(actualTable);
        }


        return "void";
    }

    @Override
    public String visit(DeclVarStruct declVarStruct) {
        ArrayList<Ast> idents = declVarStruct.struct_names ;
        Ident identType = (Ident) declVarStruct.struct_type ;
        String type = "struct " + identType.name ;

        String erreur = test.struct_non_def(identType.name);

        if (!erreur.equals("erreur")) {

            for (int i = 0; i < idents.size(); i++) {
                Ident ident = (Ident) idents.get(i) ;
                String name = ident.name;

                test.var_deja_def(name);

                //récupération du bloc actuel
                Tds actualTable = test.TdsStack.pop();

                //ajout des variables dans la table et le graph de la TDS
                actualTable.addVariable(name, type);
                String deplacement = String.valueOf(depl*4);
                graphviztds.addElement(name, "attribut", type, deplacement);
                depl++;

                //on remet le bloc ectuel qu'on a retiré après l'avoir modifié
                test.TdsStack.push(actualTable);
            }

        }
        return "void";
    }

    @Override
    public String visit(Ident ident) {

        // ATTENTION on ne visite Ident que si c'est une variable

        String name = ident.name;
        String type = test.var_non_def(name);
        return type;
    }

    @Override
    public String visit(Int entier) {
        return "int";
    }

    @Override
    public String visit(ParamInt paramint) {

        Ident ident = (Ident) paramint.ident;
        String nom = ident.name;

        test.var_deja_def(nom);

        //récupération du bloc actuel
        Tds actualTable = test.TdsStack.pop();

        //ajout des paramètres dans la table et le graph de la TDS
        actualTable.addParam(nom, "int");
        graphviztds.addElement(nom, "param", "int", "- X -");

        //on remet le bloc ectuel qu'on a retiré après l'avoir modifié
        test.TdsStack.push(actualTable);

        return "int";
    }

    @Override
    public String visit(ParamStruct paramstruct) {

        Ident identType = paramstruct.struct_type;
        Ident identName = paramstruct.struct_name;
        String type = "struct " + identType.name;
        String nom = identName.name;

        String non_def = this.test.struct_non_def(identType.name);
        if (! non_def.equals("non def")) {
            test.var_deja_def(nom);
        }

        //récupération du bloc actuel
        Tds actualTable = test.TdsStack.pop();

        //ajout des variables dans la table et le graph de la TDS
        actualTable.addParam(nom, type);
        graphviztds.addElement(nom, "param", type, "- X -");

        //on remet le bloc ectuel qu'on a retiré après l'avoir modifié
        test.TdsStack.push(actualTable);

        return type;
    }

    @Override
    public String visit(If ifinstr) {

        String type = ifinstr.expr.accept(this);
        test.cond(type);

        int save_depl = depl;

        blocLabel.push("Bloc If");

        Ast blocif = ifinstr.instruction;
        if (blocif == null) {
            createBloc();
        }
        else if (blocif instanceof Bloc) {
            blocif.accept(this);
            depl = save_depl;
        }
        else {
            String label = blocLabel.lastElement();
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);

            NumImbr++;
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);

            blocif.accept(this);

            depl = save_depl;
            NumImbr--;
            graphviztds.addEndTable();
            test.TdsStack.pop();
        }

        blocLabel.pop();

        return "void";
    }

    @Override
    public String visit(IfElse ifelseinstr) {

        String type = ifelseinstr.expr.accept(this);
        test.cond(type);

        blocLabel.push("Bloc If");
        Ast blocif = ifelseinstr.instruction1;

        String contient_return_if = "";
        int save_depl = depl;

        if (blocif == null) {
            createBloc();
        }
        else if (blocif instanceof Bloc) {
            contient_return_if = blocif.accept(this);
            depl = save_depl;
        }
        else {
            String label = blocLabel.lastElement();
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);

            NumImbr++;
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);

            contient_return_if = blocif.accept(this);

            depl = save_depl;
            NumImbr--;
            graphviztds.addEndTable();
            test.TdsStack.pop();
        }

        blocLabel.pop();

        String contient_return_else = "";

        blocLabel.push("Bloc Else");
        Ast blocelse = ifelseinstr.instruction2;

        if (blocelse == null) {
            createBloc();
        }
        else if (blocelse instanceof Bloc) {
            contient_return_else = blocelse.accept(this);
            depl = save_depl;
        }
        else {
            String label = blocLabel.lastElement();
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);

            NumImbr++;
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);

            contient_return_else = blocelse.accept(this);

            depl = save_depl;
            NumImbr--;
            graphviztds.addEndTable();
            test.TdsStack.pop();
        }

        blocLabel.pop();
        String contient_return = "";
        if (contient_return_if.equals("return") && contient_return_else.equals("return")) {
            contient_return = "return";
        }

        return contient_return;
    }

    @Override
    public String visit(While whileinstr) {

        String type = whileinstr.expr.accept(this);
        test.cond(type);

        int save_depl = depl;

        blocLabel.push("Bloc While");

        Ast blocwhile = whileinstr.instruction;

        if (blocwhile == null) {
            createBloc();
        }
        else if (blocwhile instanceof Bloc) {
            blocwhile.accept(this);
            depl = save_depl;
        }
        else {
            String label = blocLabel.lastElement();
            TdsBloc tdsbloc = new TdsBloc(test.TdsStack.lastElement(), NumImbr);

            NumImbr++;
            graphviztds.addStartTable("bloc : " + label + "  " + NumImbr);
            this.test.TdsStack.push(tdsbloc);

            blocwhile.accept(this);

            depl = save_depl;
            NumImbr--;
            graphviztds.addEndTable();
            test.TdsStack.pop();
        }

        blocLabel.pop();

        return "void";
    }

    @Override
    public String visit(Return ret) {

        //on récupère ainsi le type de l'expression
        String type = ret.expr.accept(this);
        if (!type.equals("erreur")) {
            this.test.return_type(type);
        }

        return "return";
    }

    @Override
    public String visit(Et et) {
        String type1 = et.left.accept(this);
        String type2 = et.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("&&", type1, type_request);
            test.test_type("&&", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Ou ou) {
        String type1 = ou.left.accept(this);
        String type2 = ou.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("||", type1, type_request);
            test.test_type("||", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Plus plus) {
        String type1 = plus.left.accept(this);
        String type2 = plus.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("+", type1, type_request);
            test.test_type("+", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Moins moins) {
        String type1 = moins.left.accept(this);
        String type2 = moins.right.accept(this);
        String type_request = "int";
        if (!type1.equals("erreur") && !type2.equals("erreur")) {
            test.test_type("-", type1, type_request);
            test.test_type("-", type2, type_request);
            return type_request;
        }
        return "erreur";
    }

    @Override
    public String visit(Fleche fleche) {

        Ast gauche = fleche.value;
        Ident droite = (Ident) fleche.ident;
        String typeg = "";
        String named = droite.name;

        if (gauche instanceof Int) {
            System.out.println(ANSI_RED + "Erreur struct => entier -> ident" + ANSI_RESET);
            return "erreur";
        }
        else if (gauche instanceof SizeOf) {
            System.out.println(ANSI_RED + "Erreur struct => size of -> ident" + ANSI_RESET);
            return "erreur";
        }
        else {
            typeg = gauche.accept(this);
            if (typeg.equals("erreur")) {
                return "erreur";
            }
        }

        String type = test.test_fleche_def(typeg, named);
        return type;
    }
}
