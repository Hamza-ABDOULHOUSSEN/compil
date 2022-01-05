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
	public Ast visitDef_struct(exprParser.Def_structContext ctx) {
		int nb_child = ctx.getChildCount();

		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> decl_vars= new ArrayList<>();

		for (int i=3; i<nb_child-2; i++) {
			Ast expr = ctx.getChild(i).accept(this);
			decl_vars.add(expr);
		}

		return new DefStruct(ident, decl_vars);
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

	@Override public Ast visitExpr(exprParser.ExprContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override
	public Ast visitDefFctInt(exprParser.DefFctIntContext ctx) {
		Ident ident = new Ident(ctx.getChild(1).toString());
		Ast bloc = ctx.getChild(4).accept(this);
		DefFctInt decl = new DefFctInt(ident, bloc);
		return decl;
	}

	@Override
	public Ast visitDefFctIntParam(exprParser.DefFctIntParamContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(1).toString());
		ArrayList<Ast> params = new ArrayList<Ast>();
		for (int i = 3; i < nb_child - 2; i += 2) {
			Ast param = ctx.getChild(i).accept(this);
			params.add(param);
		}
		Ast bloc = ctx.getChild(nb_child - 1).accept(this);
		DefFctIntParam decl = new DefFctIntParam(ident, params, bloc);
		return decl;
	}

	@Override
	public Ast visitDefFctStruct(exprParser.DefFctStructContext ctx) {
		Ident ident1 = new Ident(ctx.getChild(1).toString());
		Ident ident2 = new Ident(ctx.getChild(3).toString());
		Ast bloc = ctx.getChild(6).accept(this);
		DefFctStruct decl = new DefFctStruct(ident1, ident2, bloc);
		return decl;
	}

	@Override
	public Ast visitDefFctStructParam(exprParser.DefFctStructParamContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident1 = new Ident(ctx.getChild(1).toString());
		Ident ident2 = new Ident(ctx.getChild(3).toString());
		ArrayList<Ast> params = new ArrayList<>();
		for (int i = 5; i < nb_child - 2; i += 2) {
			Ast param = ctx.getChild(i).accept(this);
			params.add(param);
		}
		Ast bloc = ctx.getChild(nb_child - 1).accept(this);
		DefFctStructParam decl = new DefFctStructParam(ident1, ident2, params, bloc);
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
	public Ast visitFctParam(exprParser.FctParamContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(0).toString());
		ArrayList<Ast> exprs = new ArrayList<>();
		for (int i = 2; i < nb_child - 1; i += 2) {
			Ast expr = ctx.getChild(i).accept(this);
			exprs.add(expr);
		}
		FctParam val = new FctParam(ident, exprs);
		return val;
	}

	@Override
	public Ast visitFct(exprParser.FctContext ctx) {
		int nb_child = ctx.getChildCount();
		Ident ident = new Ident(ctx.getChild(0).toString());
		Fct val = new Fct(ident);
		return val;
	}

	@Override
	public Ast visitSizeOf(exprParser.SizeOfContext ctx) {
		Ident ident = new Ident(ctx.getChild(3).toString());
		SizeOf val = new SizeOf(ident);
		return val;
	}

	@Override
	public Ast visitParenthese(exprParser.ParentheseContext ctx) {
		Ast expr = ctx.getChild(1).accept(this);
		Parenthese val = new Parenthese(expr);
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

		for (int i = nb_child - 2; i > -1; i--) {
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

	@Override public Ast visitOu(exprParser.OuContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(0).accept(this);

		for (int i=2; i<nb_child; i+=2) {
			Ast right = ctx.getChild(i).accept(this);
			noeudTemporaire = new Ou(noeudTemporaire, right);
		}

		return noeudTemporaire;
	}

	@Override public Ast visitEt(exprParser.EtContext ctx) {
		int nb_child = ctx.getChildCount();

		Ast noeudTemporaire = ctx.getChild(0).accept(this);

		for (int i=2; i<nb_child; i+=2) {
			Ast right = ctx.getChild(i).accept(this);
			noeudTemporaire = new Et(noeudTemporaire, right);
		}

		return noeudTemporaire;
	}

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

	@Override public Ast visitFleche(exprParser.FlecheContext ctx) {

		int nb_child = ctx.getChildCount();

		if (nb_child==1) {
			return ctx.getChild(0).accept(this);
		}
		else {
			Ast noeudTemporaire = new Ident(ctx.getChild(nb_child - 1).toString());

			for (int i = nb_child - 3; i > 1; i -= 2) {
				Ast left = new Ident(ctx.getChild(i).toString());
				noeudTemporaire = new Fleche(left, noeudTemporaire);
			}
			Ast left = ctx.getChild(0).accept(this);
			noeudTemporaire = new Fleche(left, noeudTemporaire);

			return noeudTemporaire;
		}
	}

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
		Ast instruction2 = ctx.getChild(6).accept(this);

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

}