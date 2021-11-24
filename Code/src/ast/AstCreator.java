package ast;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import parser.exprBaseVisitor;
import parser.exprParser;

import java.util.ArrayList;

public class AstCreator extends exprBaseVisitor<Ast>{

	@Override public Ast visitFichier(exprParser.FichierContext ctx) {
		Ast declarations = ctx.getChild(0).accept(this);
		return new Fichier(declarations);
	}
	
	@Override public Ast visitDecl(exprParser.DeclContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override public Ast visitDeclVarInt(exprParser.DeclVarIntContext ctx) {
		String idfString = ctx.getChild(1).toString();

		//Création des sous AST
		Ident ident = new Ident(idfString);
		
		return new DeclVarInt(ident);
	}
	
	@Override public Ast visitDeclVarStruct(exprParser.DeclVarStructContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override public Ast visitDecl_typ(exprParser.Decl_typContext ctx) {
		int nb_child = ctx.getChildCount();

		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> decl_vars= new ArrayList<Ast>();

		for (int i=3; i<nb_child-2; i++) {
			Ast expr = ctx.getChild(i).accept(this);
			decl_vars.add(expr);
		}

		return new Decl_typ(ident, decl_vars);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override public Ast visitParam(exprParser.ParamContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitExpr(exprParser.ExprContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitEgal(exprParser.EgalContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitOu(exprParser.OuContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitEt(exprParser.EtContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitDiff(exprParser.DiffContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitComp(exprParser.CompContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitPlus(exprParser.PlusContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitMult(exprParser.MultContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitUnaire(exprParser.UnaireContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitFleche(exprParser.FlecheContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitValue(exprParser.ValueContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitInstruction(exprParser.InstructionContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitBloc(exprParser.BlocContext ctx) { return visitChildren(ctx); }
}
