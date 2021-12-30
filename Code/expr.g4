grammar expr;

@header{
package parser;
}

fichier : (decl)* EOF ;

decl : def_struct | decl_fct ;

decl_vars : 'int' IDENT (',' IDENT)* ';'   #DeclVarInt
        | 'struct' IDENT '*' IDENT (',' '*' IDENT)* ';'   #DeclVarStruct
        ;

def_struct : 'struct' IDENT '{' decl_vars* '}' ';' ;

decl_fct : 'int' IDENT '(' ')' bloc   #DefFctInt
        | 'int' IDENT '(' ( param (',' param)*) ')' bloc   #DefFctIntParam
        | 'struct' IDENT '*' IDENT '(' ')' bloc   #DefFctStruct
        | 'struct' IDENT '*' IDENT '(' (param (','param)*) ')' bloc   #DefFctStructParam
        ;

param : 'int' IDENT        #ParamInt
        | 'struct' IDENT '*' IDENT     #ParamStruct
        ;

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

COMMENT : '//' ~('\r'|'\n')* ('\r'|'\n')?
        |'/*' .*? '*/'
        ;

WS : (('\n'|'\t'|'\r'|' ')|COMMENT)+ -> skip;