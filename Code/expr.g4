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
        | 'struct' IDENT '*' IDENT '(' (param (','param)*) ')' bloc   #Decl_fct_struct_param
        | 'struct' IDENT '*' IDENT '(' ')' bloc   #Decl_fct_struct_param_vide
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
fleche : value '->' IDENT | value ;
value : INT                                 #Value_int
        |IDENT                              #Value_ident
        |IDENT '(' (expr ',')* expr ')'     #Value_list_expr
        |IDENT '(' ')'                      #Value_list_expr_vide
        |'sizeof' '(' 'struct' IDENT ')'    #Value_sizeof
        |'('expr')'                         #Value_expr
        ;

instruction : ';'                                                           #NoInstr
                | expr ';'                                                  #InstrExpr
                | 'if' '(' expr ')' instruction ';'                         #If
                | 'if' '(' expr ')' instruction ';' 'else' instruction      #IfElse
                | 'while' '(' expr ')' instruction                          #While
                |  bloc                                                     #InstrBloc
                | 'return' expr ';'                                         #Return
                ;

bloc : '{' decl_vars* instruction* '}' ;

OPERATEUR : '=' | '==' | '!=' | '<' | '<=' | '>' | '>=' | '+' | '-' | '*' | '/' | '&&' | '||' ;

//INT : ('0'..'9')+;

//IDENT : ('A'..'Z' | 'a'..'z' | '_')+ ('A'..'Z' | 'a'..'z' | '_' | INT)*;

IDENT : (LETTER) (LETTER|CHIFFRE)* ;

CHIFFRE : ('0'..'9');

INT : '0' | ('1'..'9')CHIFFRE* | LETTER ;

LETTER : 'A'..'Z' | 'a'..'z' | '_';

WS : ('\n' |'\t' | ' ')+ -> skip ;

