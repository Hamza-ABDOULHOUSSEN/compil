package tds;

import ast.*;

public class TDSVisitor implements AstVisitor<Void> {
    public TDS table;
    private String visitingStruct = null;
    private Tool t;

    public TDSVisitor() {
        table = new TDS();
        t = new Tool(table);
    }

    private void addEntry(TDSEntry entry) {
        try {
            table.addEntry(entry);
        }
        catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public TDS getTable() {
        return this.table;
    }

    @Override
    public Void visit(Fichier fichier) {
        // descente dans l'arbre
        for (Ast ast : fichier.declarations) {
            ast.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Ident ident) {
        // on rajoute l'entrée courante à la TDS
        this.addEntry(new TDSVarEntry(ident));
        //return null;
        /*
        if (visitingStruct == null) throw new IllegalAccessError();
        if (this.table.getRefStruct(this.visitingStruct) == null) {
            throw new IllegalArgumentException("struct not found");
        }
         */
        TDSStructDecl TDSsD = this.table.getRefStruct(this.visitingStruct);
        this.addEntry(new TDSVarEntry(TDSsD, ident));
        return null;
    }


    @Override
    public Void visit(DefStruct def_struct) {
        Ident newident = (Ident) def_struct.ident  ;
        if (this.table.getRefStruct(newident.name) != null) {
            throw new IllegalArgumentException("Invalid ident");
        }
        this.table.addRefStruct(new TDSStructDecl(def_struct));
        return null;
    }

    @Override
    public Void visit(DefFctInt def_fct_int) {
        TDSFuncEntry entry = new TDSFuncEntry(def_fct_int, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(Bloc bloc) {
        this.addEntry(new TDSBlocEntry(this.table, bloc));
        return null;
    }

    @Override
    public Void visit(DefFctIntParam def_fct_int_param) {
        TDSFuncEntry entry = new TDSFuncEntry(def_fct_int_param, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(ParamInt paramInt) {
        this.addEntry(new TDSParamEntry(paramInt));
        return null;
    }

    @Override
    public Void visit(ParamStruct paramStruct) {
        this.addEntry(new TDSParamEntry(paramStruct));
        return null;
    }

    @Override
    public Void visit(DefFctStruct def_fct_struct) {
        TDSFuncEntry entry = new TDSFuncEntry(def_fct_struct, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(DefFctStructParam def_fct_struct_param) {
        TDSFuncEntry entry = new TDSFuncEntry(def_fct_struct_param, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(If ifThen) {
        TDSIfEntry entry = new TDSIfEntry(ifThen, this.table);
        if (entry.thenBloc != null) {
        }
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(IfElse ifElse) {
        TDSIfEntry entry = new TDSIfEntry(ifElse, this.table);
        if (entry.thenBloc != null) {
        }
        if (entry.elseBloc != null) {
        }
        this.addEntry(entry);
        return null;
    }


///////////////Changer
    @Override
    public Void visit(While whileinstr) {
        TDSWhileEntry entry = new TDSWhileEntry(whileinstr, this.table);
        if (entry.bloc != null) {
        }
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(DeclVarInt var) {
        for (Ast ast : var.ident) {
            ast.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(SizeOf sizeOf) {
        return null;
    }

    @Override
    public Void visit(Parenthese parenthese) {
        return null;
    }

    @Override
    public Void visit(FctParam fct_param) {
        Ident newident = (Ident) fct_param.ident  ;
        /*
        if (this.table.getRefEntry(newident.name) == null) throw new IllegalArgumentException("No such function");
        TDSEntry tdsEntry = this.table.getRefEntry(newident.name);
        if (!(tdsEntry instanceof TDSFuncEntry)) throw new IllegalArgumentException("No such function");
        TDSFuncEntry tdsFuncEntry = (TDSFuncEntry) tdsEntry;
        if (tdsFuncEntry.params.size() != fct_param.exprs.size()) throw new IllegalArgumentException("Illegal argument");
        for (int i = 0; i < tdsFuncEntry.params.size(); i++) {
            if (tdsFuncEntry.params.get(i) instanceof ParamInt && t.typeOf(fct_param.exprs.get(i))!=0) {
                throw new IllegalArgumentException("Illegal argument");
            }
            if (tdsFuncEntry.params.get(i) instanceof ParamStruct && t.typeOf(fct_param.exprs.get(i))!=1) {
                throw new IllegalArgumentException("Illegal argument");
            }
        }
         */
        return null;
    }
    
    @Override
    public Void visit(Fct fct) {
        Ident newident = (Ident) fct.ident  ;
        if (this.table.getRefEntry(newident.name) == null) throw new IllegalArgumentException("No such function");
        TDSEntry tdsEntry = this.table.getRefEntry(newident.name);
        if (!(tdsEntry instanceof TDSFuncEntry)) throw new IllegalArgumentException("No such function");
        TDSFuncEntry tdsFuncEntry = (TDSFuncEntry) tdsEntry;
        if (!tdsFuncEntry.params.isEmpty()) throw new IllegalArgumentException("Illegal argument");
        return null;
    }

    @Override
    public Void visit(Sup sup) {
        return null;
    }

    @Override
    public Void visit(Egal egal) {
        return null;
    }

    @Override
    public Void visit(Mult mult) {
        return null;
    }

    @Override
    public Void visit(Not ast) {
        return null;
    }

    @Override
    public Void visit(Moinsunaire moinsunaire) {
        return null;
    }

    @Override
    public Void visit(Affect affect) {
        return null;
    }



    @Override
    public Void visit(Div div) {
        return null;
    }

    @Override
    public Void visit(Inegal inegal) {
        return null;
    }

    @Override
    public Void visit(Inf inf) {
        return null;
    }

    @Override
    public Void visit(InfEgal infEgal) {
        return null;
    }

    @Override
    public Void visit(SupEgal supEgal) {
        return null;
    }

    @Override
    public Void visit(DeclVarStruct declvarstruct) {
        Ident newstruct_type = (Ident) declvarstruct.struct_type  ;
        this.visitingStruct = newstruct_type.name;
        for (Ast ast : declvarstruct.struct_names) {
            ast.accept(this);
        }
        this.visitingStruct = null;
        return null;
    }

    @Override
    public Void visit(Int entier) {
        return null;
    }

    @Override
    public Void visit(Return ret) {
        // TODO something can be done with semantical check
        return null;
    }

    @Override
    public Void visit(Et et) {
        return null;
    }

    @Override
    public Void visit(Ou ou) {
        return null;
    }

    @Override
    public Void visit(Plus plus) {
        return null;
    }

    @Override
    public Void visit(Moins moins) {
        return null;
    }



    @Override
    public Void visit(Fleche fleche) {
        return null;
    }
}