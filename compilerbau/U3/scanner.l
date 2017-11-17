/*!
\file
\brief a scanner for Modula-2
*/

%option outfile="scanner.c" header-file="scanner.h"
%option noyywrap
%option nounput
%option yylineno

%{
#include "parser.h"
#include "diagnosis.h"

int comment_depth = 0;
%}

digit [0-9]
letter [a-zA-Z]
character [^'\n\r]
stringelement [^"]

%x COMMENT

%%

"(*"                           { BEGIN(COMMENT); ++comment_depth; }
<COMMENT>"(*"                  { BEGIN(COMMENT); ++comment_depth; }
<COMMENT>"*)"                  { if (--comment_depth == 0) BEGIN(INITIAL); }
<COMMENT>[\n\r\t ]             { /* skip whitespace */ }
<COMMENT>.                     { /* ignore comments */ }

"ARRAY"                        { return KEY_ARRAY; }
"BEGIN"                        { return KEY_BEGIN; }
"CHAR"                         { return KEY_CHAR; }
"CONST"                        { return KEY_CONST; }
"DO"                           { return KEY_DO; }
"ELSE"                         { return KEY_ELSE; }
"ELSIF"                        { return KEY_ELSIF; }
"END"                          { return KEY_END; }
"FOR"                          { return KEY_FOR; }
"IF"                           { return KEY_IF; }
"INTEGER"                      { return KEY_INTEGER; }
"MODULE"                       { return KEY_MODULE; }
"OF"                           { return KEY_OF; }
"PROCEDURE"                    { return KEY_PROCEDURE; }
"REAL"                         { return KEY_REAL; }
"REPEAT"                       { return KEY_REPEAT; }
"RETURN"                       { return KEY_RETURN; }
"THEN"                         { return KEY_THEN; }
"TO"                           { return KEY_TO; }
"TYPE"                         { return KEY_TYPE; }
"UNTIL"                        { return KEY_UNTIL; }
"VAR"                          { return KEY_VAR; }
"WHILE"                        { return KEY_WHILE; }

":="                           { return ASSIGN; }
"|"|"OR"                       { return OR; }
"&"|"AND"                      { return AND; }
"="                            { return EQ; }
"<>"|"#"                       { return NE; }
"<"                            { return LE; }
">"                            { return GE; }
"<="                           { return LEQ; }
">="                           { return GEQ; }
"+"                            { return PLUS; }
"-"                            { return MINUS; }
"*"                            { return ASTERISK; }
"/"                            { return SLASH; }
"DIV"                          { return DIV; }
"MOD"                          { return MOD; }
"~"|"NOT"                      { return NOT; }

";"                            { return SEMICOLON; }
"."                            { return PERIOD; }
":"                            { return COLON; }
","                            { return COMMA; }
"("                            { return LPAREN; }
")"                            { return RPAREN; }
"["                            { return LBRACK; }
"]"                            { return RBRACK; }
".."                           { return RANGE; }

"'"{character}"'"              { yytext++; yytext[yyleng-2] = 0; return CHAR; }
"\""({stringelement})*"\""     { yytext++; yytext[yyleng-2] = 0; return STRING; }
{letter}({letter}|{digit})*    { return IDENT; }
{digit}({digit})*              { return INTEGER; }
{digit}({digit})*"."({digit})* { return REAL; }

[\n\r\t ]                      { /* skip whitespace */ }

<<EOF>>                        { return END_OF_FILE; }

.                              { yyerror("lexical error"); }

%%
