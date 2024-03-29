package tds;

import java.util.ArrayList;

import ast.*;

public class TDSStructDecl {
    public Ast ident;
    public ArrayList<Ast> termes;

    public TDSStructDecl(DefStruct ds) throws IllegalArgumentException {
        this.ident = ds.ident;
        this.termes = new ArrayList<>();

        for (ast.Ast terme: ds.decl_vars) {
            if (terme instanceof DeclVarInt) {
                DeclVarInt declvarint = (DeclVarInt)terme;
                for (Ast Ident: declvarint.ident) {
                    this.termes.add(Ident);
                }
            }
            else if (terme instanceof DeclVarStruct) {
                DeclVarStruct declvarstruct = (DeclVarStruct)terme;
                for (Ast Ident: declvarstruct.struct_names) {
                    this.termes.add(Ident);
                }
            }
            else throw new IllegalArgumentException("Wrong argument in type declaration");
        }
    }

    public String toString() {
        return "Struct" + ident;
    }
}