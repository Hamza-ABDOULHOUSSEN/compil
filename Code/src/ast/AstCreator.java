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
	
	@Override public Ast visitDecl(exprParser.DeclContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override public Ast visitDeclVarInt(exprParser.DeclVarIntContext ctx) {
		int nb_child = ctx.getChildCount();
		ArrayList<Ast> decl_vars= new ArrayList<Ast>();

		for (int i=1; i<nb_child-1; i+=2) {
			Ident ident = new Ident(ctx.getChild(i).toString());
			decl_vars.add(ident);
		}
		
		return new DeclVarInt(decl_vars);
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

	@Override public Ast visitDecl_fct_int(exprParser.Decl_fct_intContext ctx) {
		Ident ident = new Ident(ctx.getChild(1).toString()) ;
		Bloc bloc = new Bloc(ctx.getChild(4).toString()) ;
		Decl_fct_int decl = new Decl_fct_int(ident, bloc) ;
		return decl ;
	}

	@Override public Ast visitDecl_fct_int_param(exprParser.Decl_fct_int_paramContext ctx) {
		int nb_child = ctx.getChildCount() ;
		Ident ident = new Ident(ctx.getChild(1).toString()) ;
		ArrayList<Ast> params = new ArrayList<Ast>() ;
		for (int i = 3; i<nb_child-2; i+=2) {
			Param param = new Param(ctx.getChild(i).toString()) ;
			params.add(param) ;
		}
		Bloc bloc = new Bloc(ctx.getChild(nb_child)) ;
		Decl_fct_int_param decl = new Decl_fct_int_param(ident, params, bloc) ;
		return decl ;
	}

	@Override public Ast visitDecl_fct_struct(exprParser.Decl_fct_structContext ctx) {
		Ident ident1 = new Ident(ctx.getChild(1).toString()) ;
		Ident ident2 = new Ident(ctx.getChild(3).toString()) ;
		Bloc bloc = new Bloc(ctx.getChild(6).toString()) ;
		Decl_fct_struct decl = new Decl_fct_struct(ident1, ident2, bloc) ;
		return decl ;
	}

	@Override public Ast visitDecl_fct_struct_param(exprParser.Decl_fct_struct_paramContext ctx) {
		int nb_child = ctx.getChildCount() ;
 		Ident ident1 = new Ident(ctx.getChild(1).toString()) ;
		Ident ident2 = new Ident(ctx.getChild(3).toString()) ;
		ArrayList<Ast> params = new ArrayList<Ast>() ;
		for (int i = 5; i<nb_child - 2; i+=2) {
			Param param = new Param(ctx.getChild(i).toString()) ;
			params.add(param) ;
		}
		Bloc bloc = new Bloc(ctx.getChild(6).toString()) ;
		Decl_fct_struct_param decl = new Decl_fct_struct_param(ident1, ident2, params, bloc) ;
		return decl ;
	}

	@Override public Ast visitDecl_fct_struct_param_vide(exprParser.Decl_fct_struct_param_videContext ctx) {
		int nb_child = ctx.getChildCount() ;
		Ident ident1 = new Ident(ctx.getChild(1).toString()) ;
		Ident ident2 = new Ident(ctx.getChild(3).toString()) ;
		Bloc bloc = new Bloc(ctx.getChild(6).toString()) ;
		Decl_fct_struct_param_vide decl = new Decl_fct_struct_param_vide(ident1, ident2, bloc) ;
		return decl ;
	}

	@Override public Ast visitBloc(exprParser.BlocContext ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> vars = new ArrayList<Ast>() ;
		for (int i = 1; i<nb_child - 1; i++) {
			Ast ast = ctx.getChild(i).accept(this);
			vars.add(ast) ;
		}
		Bloc bloc = new Bloc(vars) ;
		return bloc ;
	}

	@Override public Ast visitValue_int(exprParser.Value_intContext ctx) {
		INT Int = new INT(ctx.getChild(i).toString()) ;
		Value_int val = new Value_int(Int) ;
		return val ;
	}

	@Override public Ast visitValue_ident(exprParser.Value_identContext ctx) {
		IDENT ident = new IDENT(ctx.getChild(i).toString()) ;
		Value_ident val = new Value_ident(ident) ;
		return val ;
	}

	@Override public Ast visitValue_sizeof(exprParser.Value_sizeofContext ctx) {
		Ident ident = new Ident(ctx.getChild(i).toString()) ;
		Value_int val = new Value_int(Int) ;
		return val ;
	}

	@Override public Ast visitValue_expr(exprParser.Value_exprContext ctx) {
		Expr expr = new Expr(ctx.getChild(i).toString()) ;
		Value_int val = new Value_int(Int) ;
		return val ;
	}

	@Override public Ast visitValue_list_expr(exprParser.Value_list_expr(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		Ident ident = new Ident(ctx.getChild(0).toString) ;
		ArrayList<Ast> exprs = new ArrayList<Ast>() ;
		for (int i = 2; i < nb_child - 1; i+=2) {
			Expr expr = new Expr(ctx.getChild(i).toString()) ;
			exprs.add(expr) ;
		}
		Value_list_expr val = new Value_list_expr(ident, exprs) ;
		return val ;
	}

	@Override public Ast visitValue_list_expr_vide(exprParser.Value_list_expr_vide(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		Ident ident = new Ident(ctx.getChild(0).toString) ;
		ArrayList<Ast> exprs = new ArrayList<Ast>() ;
		for (int i = 2; i < nb_child - 2; i+=2) {
			Expr expr = new Expr(ctx.getChild(i).toString()) ;
			exprs.add(expr) ;
		}
		Value_list_expr val = new Value_list_expr(ident, exprs) ;
		return val ;
	}

	@Override public Ast visitComp_inf(exprParser.Comp_inf(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> plusList = new ArrayList<Ast>() ;
		for (int i = 0; i < nb_child; i+=2) {
			Plus plus = new Plus(ctx.getChild(i).toString()) ;
			plusList.add(plus) ;
		}
		Comp_inf comp = new Comp_inf(plusList) ;
		return comp ;
	}

	@Override public Ast visitComp_inf_egal(exprParser.Comp_inf_egal(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> plusList = new ArrayList<Ast>() ;
		for (int i = 0; i < nb_child; i+=2) {
			Plus plus = new Plus(ctx.getChild(i).toString()) ;
			plusList.add(plus) ;
		}
		Comp_inf_egal comp = new Comp_inf_egal(plusList) ;
		return comp ;
	}

	@Override public Ast visitComp_sup(exprParser.Comp_sup(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> plusList = new ArrayList<Ast>() ;
		for (int i = 0; i < nb_child; i+=2) {
			Plus plus = new Plus(ctx.getChild(i).toString()) ;
			plusList.add(plus) ;
		}
		Comp_sup comp = new Comp_sup(plusList) ;
		return comp ;
	}

	@Override public Ast visitComp_sup_egal(exprParser.Comp_sup_egal(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> plusList = new ArrayList<Ast>() ;
		for (int i = 0; i < nb_child; i+=2) {
			Plus plus = new Plus(ctx.getChild(i).toString()) ;
			plusList.add(plus) ;
		}
		Comp_sup_egal comp = new Comp_sup_egal(plusList) ;
		return comp ;
	}

	@Override public Ast visitEgal(exprParser.Egal(Context ctx) {
		int nb_child = ctx.getChildCount() ;
		ArrayList<Ast> ouList = new ArrayList<Ast>() ;
		for (int i = 0; i < nb_child; i+=2) {
			Ou ou = new Ou(ctx.getChild(i).toString()) ;
			ouList.add(ou) ;
		}
		Egal egal = new Egal(ouList) ;
		return egal ;
	}


