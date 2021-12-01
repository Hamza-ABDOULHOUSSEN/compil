package ast;

import parser.*;
import parser.exprParser;

import java.util.ArrayList;

public class AstCreator extends exprBaseVisitor<Ast> {

	@Override
	public Ast visitFichier(exprParser.FichierContext ctx) {
		Ast declarations = ctx.getChild(0).accept(this);
		return new Fichier(declarations);
	}

	@Override
	public Ast visitDecl(exprParser.DeclContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override
	public Ast visitDeclVarInt(exprParser.DeclVarIntContext ctx) {
		int nb_child = ctx.getChildCount();
		ArrayList<Ast> decl_vars = new ArrayList<Ast>();

		for (int i = 1; i < nb_child - 1; i += 2) {
			Ident ident = new Ident(ctx.getChild(i).toString());
			decl_vars.add(ident);
		}

		return new DeclVarInt(decl_vars);
	}

	@Override
	public Ast visitDeclVarStruct(exprParser.DeclVarStructContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitDecl_typ(exprParser.Decl_typContext ctx) {
		int nb_child = ctx.getChildCount();

		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> decl_vars = new ArrayList<Ast>();

		for (int i = 3; i < nb_child - 2; i++) {
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

	@Override
	public Ast visitParam(exprParser.ParamContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public Ast visitExpr(exprParser.ExprContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitOu(exprParser.OuContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public Ast visitEt(exprParser.EtContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitPlus(exprParser.PlusContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitFleche(exprParser.FlecheContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitInstruction(exprParser.InstructionContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */

	@Override
	public Ast visitDecl_fct_int(exprParser.Decl_fct_intContext ctx) {
		Ident ident = new Ident(ctx.getChild(1).toString());
		Ast bloc = ctx.getChild(4).accept(this);
		Decl_fct_int decl = new Decl_fct_int(ident, bloc);
		return decl;
	}

	@Override
	public Ast visitDecl_fct_int_param(exprParser.Decl_fct_int_paramContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> params = new ArrayList<Ast>();
		for (int i = 3; i < nb_child - 2; i += 2) {
			Ast param = ctx.getChild(i).accept(this);
			params.add(param);
		}
		Ast bloc = ctx.getChild(nb_child - 1).accept(this);
		Decl_fct_int_param decl = new Decl_fct_int_param(ident, params, bloc);
		return decl;
	}

	@Override
	public Ast visitDecl_fct_struct(exprParser.Decl_fct_structContext ctx) {
		Ident ident1 = new Ident(ctx.getChild(1).toString());
		Ident ident2 = new Ident(ctx.getChild(3).toString());
		Ast bloc = ctx.getChild(6).accept(this);
		Decl_fct_struct decl = new Decl_fct_struct(ident1, ident2, bloc);
		return decl;
	}

	@Override
	public Ast visitDecl_fct_struct_param(exprParser.Decl_fct_struct_paramContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident1 = new Ident(ctx.getChild(1).toString());
		Ident ident2 = new Ident(ctx.getChild(3).toString());
		ArrayList<Ast> params = new ArrayList<>();
		for (int i = 5; i < nb_child - 2; i += 2) {
			Ast param = ctx.getChild(i).accept(this);
			params.add(param);
		}
		Ast bloc = ctx.getChild(nb_child - 1).accept(this);
		Decl_fct_struct_param decl = new Decl_fct_struct_param(ident1, ident2, params, bloc);
		return decl;
	}

	@Override
	public Ast visitDecl_fct_struct_param_vide(exprParser.Decl_fct_struct_param_videContext ctx) {
		Ident ident1 = new Ident(ctx.getChild(1).toString());
		Ident ident2 = new Ident(ctx.getChild(3).toString());
		Ast bloc = ctx.getChild(6).accept(this);
		Decl_fct_struct_param_vide decl = new Decl_fct_struct_param_vide(ident1, ident2, bloc);
		return decl;
	}

	@Override
	public Ast visitBloc(exprParser.BlocContext ctx) {
		int nb_child = ctx.getChildCount();
		ArrayList<Ast> vars = new ArrayList<>();
		for (int i = 1; i < nb_child - 1; i++) {
			Ast ast = ctx.getChild(i).accept(this);
			vars.add(ast);
		}
		Bloc bloc = new Bloc(vars);
		return bloc;
	}

	@Override
	public Ast visitValue_int(exprParser.Value_intContext ctx) {
		Int entier = new Int(ctx.getChild(0).toString());
		return entier;
	}

	@Override
	public Ast visitValue_ident(exprParser.Value_identContext ctx) {
		Ident ident = new Ident(ctx.getChild(0).toString());
		return ident;
	}

	@Override
	public Ast visitValue_list_expr(exprParser.Value_list_exprContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(0).toString());
		ArrayList<Ast> exprs = new ArrayList<>();
		for (int i = 2; i < nb_child - 1; i += 2) {
			Ast expr = ctx.getChild(i).accept(this);
			exprs.add(expr);
		}
		Value_list_expr val = new Value_list_expr(ident, exprs);
		return val;
	}

	@Override
	public Ast visitValue_list_expr_vide(exprParser.Value_list_expr_videContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(0).toString());
		Value_list_expr_vide val = new Value_list_expr_vide(ident);
		return val;
	}

	@Override
	public Ast visitValue_sizeof(exprParser.Value_sizeofContext ctx) {
		Ident ident = new Ident(ctx.getChild(3).toString());
		Value_sizeof val = new Value_sizeof(ident);
		return val;
	}

	@Override
	public Ast visitValue_expr(exprParser.Value_exprContext ctx) {
		Ast expr = ctx.getChild(1).accept(this);
		Value_expr val = new Value_expr(expr);
		return val;
	}

	@Override
	public Ast visitComp(exprParser.CompContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(0).accept(this);

		for (int i = 1; i < nb_child - 1; i += 2) {
			String operation = ctx.getChild(i).toString();
			Ast right = ctx.getChild(i + 1).accept(this);
			switch (operation) {
				case ">" -> noeudTemporaire = new Sup(noeudTemporaire, right);
				case ">=" -> noeudTemporaire = new SupEgal(noeudTemporaire, right);
				case "<" -> noeudTemporaire = new Inf(noeudTemporaire, right);
				case "<=" -> noeudTemporaire = new InfEgal(noeudTemporaire, right);
				default -> {
				}
			}
		}

		return noeudTemporaire;

	}


	@Override
	public Ast visitEgal(exprParser.EgalContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(nb_child - 1).accept(this);

		for (int i = nb_child - 3; i > -1; i -= 2) {
			Ast left = ctx.getChild(i).accept(this);
			noeudTemporaire = new Affect(left, noeudTemporaire);
		}

		return noeudTemporaire;
	}

	@Override
	public Ast visitDiff(exprParser.DiffContext ctx) {
		int nb_child = ctx.getChildCount();
		Ast noeudTemporaire = ctx.getChild(0).accept(this);
		for (int i = 1; i < nb_child - 1; i += 2) {
			String operation = ctx.getChild(i).toString();
			Ast right = ctx.getChild(i + 1).accept(this);
			switch (operation) {
				case "==" -> noeudTemporaire = new Egal(noeudTemporaire, right);
				case "!=" -> noeudTemporaire = new Inegal(noeudTemporaire, right);
				default -> {
				}
			}
		}
		return noeudTemporaire;
	}

	@Override
	public Ast visitMult(exprParser.MultContext ctx) {
		int nb_child = ctx.getChildCount();
		Ast noeudTemporaire = ctx.getChild(0).accept(this);
		for (int i = 1; i < nb_child - 1; i += 2) {
			String operation = ctx.getChild(i).toString();
			Ast right = ctx.getChild(i + 1).accept(this);
			switch (operation) {
				case "*" -> noeudTemporaire = new Mult(noeudTemporaire, right);
				case "/" -> noeudTemporaire = new Div(noeudTemporaire, right);
				default -> {
				}
			}
		}
		return noeudTemporaire;
	}

	@Override
	public Ast visitUnaire(exprParser.UnaireContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(nb_child - 1).accept(this);

		for (int i = nb_child - 2; i > -1; i++) {
			String operation = ctx.getChild(i).toString();
			switch (operation) {
				case "!" -> noeudTemporaire = new Not(noeudTemporaire);
				case "-" -> noeudTemporaire = new Moinsunaire(noeudTemporaire);
				default -> {
				}
			}
		}

		return noeudTemporaire;
	}
}

