import ast.Ast;
import ast.AstCreator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import parser.exprLexer;
import parser.exprParser;
import structureTds.TdsVisitor;

import java.io.IOException;

public class Main3 {
    public static void main(String[] args) throws IOException {

        if (args.length < 1){
            System.out.println("Error : Expected 1 argument filepath, found 0");
            return;
        }

        String testFile = args[0];
        String name = args[1];

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

            // Visiteur de représentation graphique + appel
            TdsVisitor tdsvisitor = new TdsVisitor();
            ast.accept(tdsvisitor);

            String filepath = "./out/dot/" + name + ".dot";

            tdsvisitor.createGraph(filepath);


        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
