grammar expr;

@header{
package parser;
}

fichier : (decl)* EOF ;

decl : decl_typ | decl_fct ;

decl_vars : 'int' IDENT (',' IDENT)* ';'   #DeclVarInt
        | 'struct' IDENT '*' IDENT (',' '*' IDENT)* ';'   #DeclVarStruct
        ;

decl_typ : 'struct' IDENT '{' decl_vars* '}' ';' ;

decl_fct : 'int' IDENT '(' ( param (',' param)*)? ')' bloc 
        | 'struct' IDENT '*' IDENT '(' (param (','param)*)? ')' bloc ;

param : 'int' IDENT 
        | 'struct' IDENT '*' IDENT ;

expr :  INT
        | IDENT
        | expr '->' IDENT
        | IDENT '(' expr (',' expr)* ')'
        | '!' expr | '-' expr
        | expr OPERATEUR expr
        | 'sizeof' '(' 'struct' IDENT ')'
        | '(' expr ')' ;

instruction : ';'
                | expr ';'
                | 'if' '(' expr ')' instruction ';'
                | 'if' '(' expr ')' instruction ';' 'else' instruction
                | 'while' '(' expr ')' instruction
                |  bloc
                | 'return' expr ';' ;

bloc : '{' decl_vars* instruction* '}' ;

OPERATEUR : '=' | '==' | '!=' | '<' | '<=' | '>' | '>=' | '+' | '-' | '*' | '/' | '&&' | '||' ;

INT : ('0'..'9')+;

IDENT : ('A'..'Z' | 'a'..'z' | '_')+ ('A'..'Z' | 'a'..'z' | '_' | INT)*;

COMMENT :
    //   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    '/*' .*? '*/'
    ;
LINE_COMMENT :
    //: '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    '//' ~('\n'|'\r')* '\r'? '\n'
    ;



WS : ('\n' |'\t' | ' ')+ -> skip ;

