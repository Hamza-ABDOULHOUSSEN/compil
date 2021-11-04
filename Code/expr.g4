grammar expr;

@header{
package parser;
}

fichier : (decl)* EOF ;

decl : decl_typ | decl_fct ;

decl_vars : 'int' IDF+ ',' ';' | 'struct' IDF  ('*' IDF)+  ',' ';' ;

decl_typ : 'struct' IDF '{' decl_vars* '}' ';' ;

decl_fct : 'int' IDF '(' param* ',' ')' bloc;

param : 'int' IDF | 'struct' IDF '*' IDF ;

expr :  INT
        | IDF
        | expr '->' IDF
        | IDF '(' expr* ',' ')'
        | '!' expr | '-' expr
        | expr OPERATEUR expr
        | 'sizeof' '(' 'struct' IDF ')'
        | '(' expr ')' ;

instruction : ';'
                | expr ';'
                | 'if' '(' expr ')' instruction
                |'if' '(' expr ')' instruction 'else' instruction
                | 'while' '(' expr ')' instruction
                | bloc
                | 'return' expr ';' ;

bloc : '{' decl_vars* instruction* '}' ;


OPERATEUR : '=' | '==' | '!=' | '<' | '<=' | '>' | '>=' | '+' | '-' | '*' | '/' | '&&' | '||' ;

INT : ('0'..'9')+;

IDF : ('A'..'Z' | 'a'..'z' | '_')+ ('A'..'Z' | 'a'..'z' | '_' | INT)*;

WS : ('\n' |'\t' | ' ')+ -> skip ;

