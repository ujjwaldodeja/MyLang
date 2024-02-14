grammar MyLang;

program: sharedVars statementList EOF;

sharedVars: sharedDeclaration*;
sharedDeclaration: SHARED type varName (COMMA varName)* SEMICOLON;

statementList: statement*;

statement: type varName (COMMA varName)* SEMICOLON                  #declaration
         | type varName ASSIGN expression SEMICOLON                 #declarationAndAssignment
         | varName ASSIGN expression SEMICOLON                      #assignment
         | IF LBRACKET expression RBRACKET LBRACE statementList RBRACE
            (ELSEIF LBRACKET expression RBRACKET LBRACE statementList RBRACE)*
            (ELSE LBRACE statementList RBRACE)?                     #ifClause
         | WHILE LBRACKET expression RBRACKET LBRACE statementList RBRACE #whileClause
         | FORK LBRACKET ID RBRACKET LBRACE statementList RBRACE    #fork
         | PRINT LBRACKET expression RBRACKET SEMICOLON             #print
         | GETLOCK LBRACKET ID RBRACKET SEMICOLON                   #getLock
         | RELEASELOCK LBRACKET ID RBRACKET SEMICOLON               #releaseLock
         | JOIN LBRACKET ID RBRACKET SEMICOLON                      #join;


//Variable types
type: INT #intType
    | BOOL #boolType;

//expressions (evaluate to a bool or int value / array)
expression: value                               #constValue
          | prefixOperator expression           #prefixOp
          | expression multOperator expression  #multiplication
          | expression addOperator expression   #addition
          | expression comparator expression    #comparison
          | expression boolOperator expression  #boolOp
          | LBRACKET expression RBRACKET        #brackets
          | varValue                            #variableValue;

prefixOperator: NOT | MINUS;
multOperator: STAR | SLASH;
addOperator: PLUS | MINUS;
comparator: EQUALS | SMALLER | SMALLEREQUALS | BIGGER | BIGGEREQUALS | NOTEQUAL;
boolOperator: AND | OR;

//Constant (RHS) values
value: MINUS? NUM                       #number
     | TRUE                             #trueVal
     | FALSE                            #falseVal
     | LSQBR value (COMMA value)* RSQBR #arrayVal; //[x, 5, -9]

//Variable declaration or assignment
varName: ID                         #single
       | ID LSQBR NUM RSQBR         #arrayIndex;

//Variable inside (RHS) expression
varValue: ID                         #singleVal
        | ID LSQBR expression RSQBR  #arrayIndexVal; // x[4+y];


//reserved keywords
PRINT: 'print';
FORK: 'fork';
GETLOCK: 'get_lock';
RELEASELOCK: 'release_lock';
JOIN: 'join';
SHARED: 'shared';
INT: 'int';
BOOL: 'bool';
IF: 'if';
ELSEIF: 'elseif';
ELSE: 'else';
WHILE: 'while';
TRUE: 'true';
FALSE: 'false';

ID: LETTER (LETTER | DIGIT)*;
NUM: DIGIT (DIGIT)*;

SLASH: '/';
EQUALS: '==';
SMALLER: '<';
SMALLEREQUALS: '<=';
BIGGER: '>';
BIGGEREQUALS: '>=';
NOTEQUAL: '!=';
NOT: '!';
MINUS: '-';
PLUS: '+';
STAR: '*';
AND: '&&';
OR: '||';
ASSIGN: '=';
LBRACKET: '(';
RBRACKET: ')';
LBRACE: '{';
RBRACE: '}';
LSQBR: '[';
RSQBR: ']';
COMMA: ',';
SEMICOLON: ';';

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

// Skipped token types
COMMENT: SLASH SLASH .*? '\n'-> skip;
WS: [ \t\r\n]+ -> skip;
