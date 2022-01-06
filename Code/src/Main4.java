import ast.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import parser.exprLexer;
import parser.exprParser;
import tds.*;

import java.io.IOException;

public class Main4 {

    public static void main(String[] args){
        if (args.length < 1){
            System.out.println("Error : Expected 1 argument filepath, found 0");
            return;
        }

        String testFile = args[0];

        try {

            //chargement du fichier et construction du parser

            CharStream input = CharStreams.fromFileName(testFile);
            exprLexer lexer = new exprLexer(input);
            CommonTokenStream stream = new CommonTokenStream(lexer);
            exprParser parser = new exprParser(stream);

            // Récupération du noeud program (le noeud à la racine)
            exprParser.FichierContext program = parser.fichier();

            // Visiteur de création de l'AST + création de l'AST
            AstCreator creator = new AstCreator();
            Ast ast = program.accept(creator);

            // Visiteur de la table des symboles
            TDSVisitor tdsViz = new TDSVisitor();
            ast.accept(tdsViz);
            System.out.println(tdsViz.table.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
