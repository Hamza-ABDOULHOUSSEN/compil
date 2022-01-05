package tds;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.stream.Collectors;
//import org.stringtemplate.v4.compiler.CodeGenerator.conditional_return;

import ast.Ident;

public class TDS {
    private ArrayList<TDSEntry> entries;
    private HashMap<String, TDSEntry> refEntries;
    private HashMap<String, TDSStructDecl> refStructs;
    private TDS father = null;

    public TDS() {
        this.entries = new ArrayList<>();
        this.refEntries = new HashMap<>();
		this.refStructs = new HashMap<>();
    }

    public void addEntry(TDSEntry entry) throws IllegalArgumentException{
        if (entry instanceof TDSFuncEntry || entry instanceof TDSVarEntry) { 
            TDSNamedEntry tnEntry = (TDSNamedEntry) entry;
			if (refEntries.containsKey(tnEntry.getName())) throw new IllegalArgumentException("declaration d'une variable déjà connue !");
			refEntries.put(tnEntry.getName(), entry);
		}
		entries.add(entry);
    }

    public void addRefStruct(TDSStructDecl struct) {
        Ident newident = (Ident) struct.ident;
		this.refStructs.put(newident.name, struct);
	}

    public TDSEntry getRefEntry(String idfEntry) {
		if (refEntries.containsKey(idfEntry)) {
			return refEntries.get(idfEntry);
		}
		else if (this.father != null) {
			return father.getRefEntry(idfEntry);
		}
		else return null;
	}

    public TDSStructDecl getRefStruct(String idfStruct) {
		if (refStructs.containsKey(idfStruct)) {
			return refStructs.get(idfStruct);
		}
		else if (this.father != null) {
			return father.getRefStruct(idfStruct);
		}
		else return null;
	}

    public TDS getFatherTDS() {
		return this.father;
	}

    public void setFatherTDS(TDS father) {
		this.father = father;
	}

    public String toString() {
        return toString2(0);
    }

    public String toString2(int ind) {
        String strind = "";
        for (int i = 0 ; i < ind ; i++) {
            strind += " |";
        }
        String ret = strind + "TDS :";
        if (this.father == null) ret += "root\n";
        else ret += "sub\n";
        for (TDSEntry entry : entries) {
            ret += strind + ">Entry: ";
            ret += entry.toString() + "\n";
        }

        for (TDSEntry entry: entries) {
            if (entry instanceof TDSIfEntry) {
                TDSIfEntry fentry = (TDSIfEntry)entry;
                if (fentry.thenBloc == null && fentry.elseBloc == null) continue;
                ret += strind + "containing following TDS (IfThen/IfThenElse):\n";
                if (fentry.thenBloc != null && fentry.thenBloc.getTable().toString() != null) {
                    ret += strind + fentry.thenBloc.getTable().toString2(ind + 1);
                }
                if (fentry.elseBloc != null && fentry.elseBloc.getTable().toString() != null) {
                    ret += strind + fentry.elseBloc.getTable().toString2(ind + 1);
                }
            }
        }

        for (TDSEntry entry: entries) {
            if (entry instanceof TDSWhileEntry) {
				TDSWhileEntry fentry = (TDSWhileEntry)entry;
				if (fentry.bloc == null) continue;
				ret += strind + "containing following TDS (While):\n";
				if (fentry.bloc != null && fentry.bloc.getTable().toString() != null) {
					ret += strind + fentry.bloc.getTable().toString2(ind + 1);
				}
			}
        }

        for (TDSEntry entry: entries) {
			if (entry instanceof TDSFuncEntry) {
				TDSFuncEntry fentry = (TDSFuncEntry)entry;
				if (fentry.bloc == null) continue;
				ret += strind + "containing following TDS (Func):\n";
				if (fentry.bloc != null && fentry.bloc.getTable().toString() != null) {
					ret += strind + fentry.bloc.getTable().toString2(ind + 1);
				}
			}
		}
        return ret;
    }
}
