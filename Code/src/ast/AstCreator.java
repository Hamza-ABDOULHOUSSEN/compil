package ast;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import parser.*;
import parser.exprParser;

import java.util.ArrayList;

public class AstCreator extends exprBaseVisitor<Ast>{

	@Override public Ast visitFichier(exprParser.FichierContext ctx) {
		Ast declarations = ctx.getChild(0).accept(this);
		return new Fichier(declarations);
	}
	
	@Override public Ast visitDecl(exprParser.DeclContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
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
	@Override public Ast visitDecl_typ(exprParser.Decl_typContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitDecl_fct(exprParser.Decl_fctContext ctx) { return visitChildren(ctx); }
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
	@Override public Ast visitInstruction(exprParser.InstructionContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitBloc(exprParser.BlocContext ctx) { return visitChildren(ctx); }

}
