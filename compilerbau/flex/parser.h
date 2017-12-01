/*!
\file
\brief definition of a top-down parser for Modula-2
\note This file must be extended.
*/

#ifndef _PARSER_H
#define _PARSER_H

/*!
\brief a list of all tokens (to be used as return values in Flex)

The entries can be used as indices for the array #tokenNames.

\warning This enumeration must not be changed.
*/
typedef enum {
    KEY_ARRAY, KEY_BEGIN, KEY_CHAR, KEY_CONST, KEY_DO, KEY_ELSE, KEY_ELSIF,
	KEY_END, KEY_FOR, KEY_IF, KEY_INTEGER, KEY_MODULE, KEY_OF, KEY_PROCEDURE,
	KEY_REAL, KEY_REPEAT, KEY_RETURN, KEY_THEN, KEY_TO, KEY_TYPE, KEY_UNTIL,
	KEY_VAR, KEY_WHILE, ASSIGN, OR, AND, EQ, NE, LE, GE, LEQ, GEQ, PLUS,
	MINUS, ASTERISK, SLASH, DIV, MOD, NOT, SEMICOLON, PERIOD, COLON, COMMA,
	LPAREN, RPAREN, LBRACK, RBRACK, RANGE, IDENT, INTEGER, REAL, CHAR, STRING,
	END_OF_FILE
} yytokentype;

void NEXT();                /*!< \brief read next token */
int TEST(yytokentype s);    /*!< \brief check current token against expected */
void MATCH(yytokentype s);  /*!< \brief match current token against expected */

void entering(char* s);     /*!< \brief announce entering a nonterminal rule */
void leaving(char* s);      /*!< \brief announce leaving a nonterminal rule */
void indent();              /*!< \brief organize proper indentation */

void yyparse();             /*!< \brief parse the file #yyin using #yylex */

/*************************************************************************
  please add your own code below this comment
 *************************************************************************/

void module();

#endif
