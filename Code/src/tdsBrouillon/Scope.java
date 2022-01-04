package net.certiv.metal.symbol;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import net.certiv.metal.types.ScopeType;
import net.certiv.metal.util.Strings;

public class Scope {

    public final int genId;

    public ScopeType type;
    public Scope enclosingScope;
    protected Map<String, Symbol> symbolMap = new LinkedHashMap<String, Symbol>();

    public Scope(ScopeType type, final int genId, Scope enclosingScope) {
        this.type = type;
        this.genId = genId;
        this.enclosingScope = enclosingScope;
    }

    /** 
     * Define a new variable in the current scope 
     * This is the entry point for adding new variables
     */
    public void define(String name, ArrayList<String> parameters) {
        String params = Strings.asString(parameters, true, ".");
        Symbol symbol = new Symbol(null, name + params, null);
        define(symbol);
    }

    /** Define a symbol in the current scope */
    private void define(Symbol symbol) {
        symbol.setScope(this);
        symbolMap.put(symbol.name, symbol);
    }

    /**
     * Look up the symbol name in this scope and, if not found, 
     * progressively search the enclosing scopes. 
     * Return null if not found in any applicable scope.
     */
    private Symbol resolve(String name) {
        Symbol symbol = symbolMap.get(name);
        if (symbol != null) return symbol;
        if (enclosingScope != null) return enclosingScope.resolve(name);
        return null; // not found
    }
    /**
     * Lookup a variable starting in the current scope.
     * This is the entry point for lookups
     */
    public Symbol resolve(String name, ArrayList<String> parameters) {
        String params = Strings.asString(parameters, true, ".");
        return resolve(name + params);
    }

    /** Where to look next for symbols */
    public Scope enclosingScope() {
        return enclosingScope;
    }

    public String toString() {
        return symbolMap.keySet().toString();
    }
}


package net.certiv.metal.types;

public enum ScopeType {
    GLOBAL,
    LOCAL;
}



package net.certiv.metal.symbol;

import net.certiv.metal.converter.BaseDescriptor;
import net.certiv.metal.types.ValueType;

public class Symbol {

    protected Scope scope; // the owning scope
    protected BaseDescriptor descriptor;
    protected String name;
    protected ValueType type;

    public Symbol(BaseDescriptor descriptor, String name, ValueType type) {
        this.descriptor = descriptor;
        this.name = name;
        this.type = type;
    }

    public BaseDescriptor getDescriptor() {
        return descriptor;
    }

    public String getName() {
        return name;
    }

    public ValueType getType() {
        return type;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }

    public int genId() {
        return scope.genId;
    }

    public String toString() {
        if (type != null) return '<' + getName() + ":" + type + '>';
        return getName();
    }
}

package net.certiv.metal.symbol;

import java.util.ArrayList;
import java.util.Stack;

import net.certiv.metal.types.ScopeType;
import net.certiv.metal.util.Log;

public class SymbolTable {

    protected Stack<Scope> scopeStack;
    protected ArrayList<Scope> allScopes;
    protected int genId;

    public SymbolTable() {
        init();
    }

    protected void init() {
        scopeStack = new Stack<>();
        allScopes = new ArrayList<>();
        genId = 0;

        Scope globals = new Scope(ScopeType.GLOBAL, nextGenId(), null);
        scopeStack.push(globals);
        allScopes.add(globals);
    }

    public Scope pushScope() {
        Scope enclosingScope = scopeStack.peek();
        Scope scope = new Scope(ScopeType.LOCAL, nextGenId(), enclosingScope);
        scopeStack.push(scope);
        allScopes.add(scope);
        return scope;
    }

    public void popScope() {
        scopeStack.pop();
    }

    public Scope currentScope() {
        if (scopeStack.size() > 0) {
            return scopeStack.peek();
        }
        Log.error(this, "Unbalanced scope stack.");
        return allScopes.get(0);
    }

    public Scope getScope(int genId) {
        for (Scope scope : scopeStack) {
            if (scope.genId == genId) return scope;
        }
        return null;
    }

    private int nextGenId() {
        genId++;
        return genId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Scope scope : scopeStack.subList(0, scopeStack.size() - 1)) {
            sb.append(scope.toString());
        }
        return sb.toString();
    }
}