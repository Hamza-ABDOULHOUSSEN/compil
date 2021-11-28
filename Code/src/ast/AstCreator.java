package ast;

import parser.*;
import parser.exprParser;

import java.util.ArrayList;

public class AstCreator extends exprBaseVisitor<Ast>{

	@Override
	public Ast visitFichier(exprParser.FichierContext ctx) {
		int nb_child = ctx.getChildCount();
		ArrayList<Ast> declarations = new ArrayList<>();

		for (int i=0; i<nb_child-1; i++) {
			Ast decl = ctx.getChild(i).accept(this);
			declarations.add(decl);
		}

		return new Fichier(declarations);
	}
	
	@Override
	public Ast visitDecl(exprParser.DeclContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override
	public Ast visitDeclVarInt(exprParser.DeclVarIntContext ctx) {
		int nb_child = ctx.getChildCount();
		ArrayList<Ast> decl_vars= new ArrayList<>();

		for (int i=1; i<nb_child-1; i+=2) {
			Ident ident = new Ident(ctx.getChild(i).toString());
			decl_vars.add(ident);
		}
		
		return new DeclVarInt(decl_vars);
	}
	
	@Override
	public Ast visitDeclVarStruct(exprParser.DeclVarStructContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident struct_type = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> struct_names = new ArrayList<>();

		for (int i=3; i<nb_child-1; i+=3) {
			Ident ident = new Ident(ctx.getChild(i).toString());
			struct_names.add(ident);
		}

		return new DeclVarStruct(struct_type, struct_names);
	}

	@Override
	public Ast visitDecl_typ(exprParser.Decl_typContext ctx) {
		int nb_child = ctx.getChildCount();

		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> decl_vars= new ArrayList<>();

		for (int i=3; i<nb_child-2; i++) {
			Ast expr = ctx.getChild(i).accept(this);
			decl_vars.add(expr);
		}

		return new Decl_typ(ident, decl_vars);
	}

	@Override
	public Ast visitParamInt(exprParser.ParamIntContext ctx) {

		Ident ident = new Ident(ctx.getChild(1).toString());

		return new ParamInt(ident);
	}

	@Override public Ast visitParamStruct(exprParser.ParamStructContext ctx) {
		Ident struct_type = new Ident(ctx.getChild(1).toString());
		Ident struct_name = new Ident(ctx.getChild(3).toString());

		return new ParamStruct(struct_type, struct_name);
	}

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
	@Override public Ast visitEt(exprParser.EtContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(0).accept(this);

		for (int i=2; i<nb_child; i+=2) {
			Ast right = ctx.getChild(i).accept(this);
			noeudTemporaire = new Et(noeudTemporaire, right);
		}

		return noeudTemporaire;
	}

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
	@Override public Ast visitPlus(exprParser.PlusContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(0).accept(this);

		for (int i=1; i<nb_child-1; i+=2) {
			String operation = ctx.getChild(i).toString();
			Ast right = ctx.getChild(i+1).accept(this);


			switch (operation) {
				case "+" -> noeudTemporaire = new Plus(noeudTemporaire, right);
				case "-" -> noeudTemporaire = new Moins(noeudTemporaire, right);
				default -> {
				}
			}
		}

		return noeudTemporaire;
	}

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
	@Override public Ast visitNoInstr(exprParser.NoInstrContext ctx) {
		return null;
	}

	@Override public Ast visitInstrExpr(exprParser.InstrExprContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override public Ast visitIf(exprParser.IfContext ctx) {
		Ast expr = ctx.getChild(2).accept(this);
		Ast instruction = ctx.getChild(4).accept(this);

		return new If(expr, instruction);
	}

	@Override public Ast visitIfElse(exprParser.IfElseContext ctx) {
		Ast expr = ctx.getChild(2).accept(this);
		Ast instruction1 = ctx.getChild(4).accept(this);
		Ast instruction2 = ctx.getChild(7).accept(this);

		return new IfElse(expr, instruction1, instruction2);
	}

	@Override public Ast visitWhile(exprParser.WhileContext ctx) {
		Ast expr = ctx.getChild(2).accept(this);
		Ast instruction = ctx.getChild(4).accept(this);

		return new While(expr, instruction);
	}

	@Override public Ast visitInstrBloc(exprParser.InstrBlocContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override public Ast visitReturn(exprParser.ReturnContext ctx) {
		Ast expr = ctx.getChild(1).accept(this);

		return new Return(expr);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public Ast visitBloc(exprParser.BlocContext ctx) { return visitChildren(ctx); }
}
