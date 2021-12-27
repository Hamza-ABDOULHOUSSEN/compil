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

decl_fct : 'int' IDENT '(' ')' bloc   #DeclFctInt
        | 'int' IDENT '(' ( param (',' param)*) ')' bloc   #DeclFctIntParam
        | 'struct' IDENT '*' IDENT '(' ')' bloc   #DeclFctStruct
        | 'struct' IDENT '*' IDENT '(' (param (','param)*) ')' bloc   #DeclFctStructParam
        ;

param : 'int' IDENT        #ParamInt
        | 'struct' IDENT '*' IDENT     #ParamStruct
        ;

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
fleche : value ('->' IDENT)* ;

value : INT                                 #Value_int
        |IDENT                              #Value_ident
        |IDENT '(' (expr ',')* expr ')'     #FctParam
        |IDENT '(' ')'                      #Fct
        |'sizeof' '(' 'struct' IDENT ')'    #SizeOf
        |'('expr')'                         #Parenthese
        ;

instruction : ';'                                                           #NoInstr
                | expr ';'                                                  #InstrExpr
                | 'if' '(' expr ')' instruction                             #If
                | 'if' '(' expr ')' instruction 'else' instruction          #IfElse
                | 'while' '(' expr ')' instruction                          #While
                |  bloc                                                     #InstrBloc
                | 'return' expr ';'                                         #Return
                ;

bloc : '{' decl_vars* instruction* '}' ;

IDENT : (LETTER) (LETTER|CHIFFRE)* ;

INT : '0' | ('1'..'9') CHIFFRE* | LETTER ;

LETTER : 'A'..'Z' | 'a'..'z' | '_';

CHIFFRE : ('0'..'9');

WS : ('\n' |'\t' | ' ' )+ -> skip ;
