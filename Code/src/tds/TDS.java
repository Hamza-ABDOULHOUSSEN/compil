package tds;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TDS {
    private ArrayList<TDSEntry> entries;
    private TDS father = null;

    public TDS() {
        this.entries = new ArrayList<>();
    }

    public int addEntry(TDSEntry entry) {
        entries.add(entry);
        return entries.size();
    }

    public TDS getFatherTDS() {
		return this.father;
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
        else ret += "son of another one\n";
        for (TDSEntry entry : entries) {
            ret += strind + "Entry: ";
            ret += entry.toString() + "\n";
        }

        for (TDSEntry entry: entries) {
            if (entry instanceof TDSStructEntry) {
                ret  += strind "containing following TDS (Struct):\n"
                TDSStructEntry sentry = (TDSStructEntry)entry;
                ret += strind + sentry.entries.toString2(ind + 1);
            }
        }

        for (TDSEntry entry: entries) {
            if (entry instanceof TDSIfEntry) {
                ret += strind + "containing following TDS (IfThen/IfThenElse):\n";
                TDSIfEntry fentry = (TDSIfEntry)entry;
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
                ret += strind + "containing following TDS (While):\n";
                TDSWhileEntry fentry = (TDSWhileEntry)entry;
                if (fentry.bloc != null && fentry.bloc.getTable().toString() != null) {
                    ret += strind + fentry.bloc.getTable().toString2(ind + 1);
                }
            }
        }
        retur ret;
    }
}
