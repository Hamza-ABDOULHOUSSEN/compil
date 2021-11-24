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

decl_fct : 'int' IDENT '(' ')' bloc   #Decl_fct_int
        | 'int' IDENT '(' ( param (',' param)*) ')' bloc   #Decl_fct_int_param
        | 'struct' IDENT '*' IDENT '(' ')' bloc   #Decl_fct_struct
        | 'struct' IDENT '*' IDENT '(' (param (','param)*)? ')' bloc   #Decl_fct_struct_param
        ;

param : 'int' IDENT
        | 'struct' IDENT '*' IDENT ;

//expr était sous cette forme avant priorité et associativité
//expr :  INT
//        | IDENT
//        | expr '->' IDENT
//        | IDENT '(' expr (',' expr)* ')'
//        | '!' expr | '-' expr
//        | expr OPERATEUR expr
//        | 'sizeof' '(' 'struct' IDENT ')'
//        | '(' expr ')' ;

expr : egal ; 

egal : (ou '=')* ou;
ou : et ('||' et)* ;
et : diff ('&&' diff)*;
diff : comp (('==' | '!=')comp)* ;
comp : plus (('<' | '<=' | '>' | '>=')plus)* ; 
plus : mult (('+' | '-')mult)* ;
mult : unaire (('*' | '/')unaire)* ;
unaire : ('!' | '-')* fleche ;
fleche : value '->' IDENT | value ;

value : INT 
        |IDENT
        |IDENT '(' (expr ',')* expr? ')'
        |'sizeof' '(' 'struct' IDENT ')'
        |'('expr')' ;

instruction : ';'
                | expr ';'
                | 'if' '(' expr ')' instruction ';'
                | 'if' '(' expr ')' instruction ';' 'else' instruction
                | 'while' '(' expr ')' instruction
                |  bloc
                | 'return' expr ';' ;

bloc : '{' decl_vars* instruction* '}' ;

OPERATEUR : '=' | '==' | '!=' | '<' | '<=' | '>' | '>=' | '+' | '-' | '*' | '/' | '&&' | '||' ;

//INT : ('0'..'9')+;

//IDENT : ('A'..'Z' | 'a'..'z' | '_')+ ('A'..'Z' | 'a'..'z' | '_' | INT)*;

IDENT : (LETTER)(LETTER|CHIFFRE)* ;

CHIFFRE : ('0'..'9');

INT : '0' | ('1'..'9')CHIFFRE* | LETTER ;

LETTER : 'A'..'Z' | 'a'..'z' | '_';

WS : ('\n' |'\t' | ' ')+ -> skip ;

