package tds;

import ast.*;

public class TdsVisitor implements AstVisitor<Void> {
    private TDS table;
    private String visitingStruct = null;
    private Tool t;

    public TdsVisitor() {
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
        // on rajoute l'entrée courant à la TDS
        this.addEntry(new TDSVarEntry(ident));
        return null;
    }

    @Override
    public Void visit(Ident ident) {
        // on rajoute l'entrée courante à la TDS
        if (visitingStruct == null) throw new IllegalAccessError();
        if (this.table.getRefStruct(this.visitingStruct) == null) {
            throw new IllegalArgumentException("struct not found");
        }
        TDSStructDecl TDSsD = this.table.getRefStruct(this.visitingStruct);
        this.addEntry(new TDSVarEntry(TDSsD, ident));
        return null;
    }

    @Override
    public Void visit(DefStruct defStruct) {
        if (this.table.getRefStruct(defStruct.ident) != null) {
            throw new IllegalArgumentException("Invalid ident");
        }
        this.table.addRefStruct(new TDSStructDecl(defStruct));
        return null;
    }

    @Override
    public Void visit(DefFctInt defFctInt) {
        TDSFuncEntry entry = new TDSFuncEntry(defFctInt, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(Bloc bloc) {
        this.addEntry(new TDSBlocEntry(this.table, bloc));
        return null;
    }

    @Override
    public Void visit(DefFctIntParam defFctIntParam) {
        TDSFuncEntry entry = new TDSFuncEntry(defFctIntParam, this.table);
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
    public Void visit(DefFctStruct defFctStruct) {
        TDSFuncEntry entry = new TDSFuncEntry(defFctStruct, this.table);
        this.addEntry(entry);
        return null;
    }

    @Override
    public Void visit(DefFctStructParam defFctStructParam) {
        TDSFuncEntry entry = new TDSFuncEntry(defFctStructParam, this.table);
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
    public Void visit(While while1) {
        TDSWhileEntry entry = new TDSWhileEntry(while1, this.table);
        if (entry.bloc != null) {
            //this.addEntry(entry.bloc);
        }
        this.addEntry(entry);
        return null;
    }

    
}