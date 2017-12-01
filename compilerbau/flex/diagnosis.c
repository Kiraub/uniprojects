/*!
\file
\brief implementation of diagnosis functions
\note This file may be extended for the bonus task.
*/

#include <stdio.h>
#include <stdlib.h>
#include "scanner.h"
#include "diagnosis.h"

/*!
\param msg  The error message to print.
\post Program will exit with exit code 1.
\note This function may be extended for the bonus task.
*/
void yyerror(char const* msg) {
    fprintf(stderr, "error in line %d: %s\n", yylineno, msg);
    fprintf(stderr, "last token: %s\n", yytext);
    exit(1);
}
