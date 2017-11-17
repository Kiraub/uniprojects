#include <stdio.h>
#include <stdlib.h>
#include "scanner.h"
#include "parser.h"
#include "diagnosis.h"

int main(int argc, char* argv[]) {
    /* initialize global variables */
    yyin = stdin;
    yyout = stdout;

    /* process command line parameters for input and output */
    if (argc > 3) {
        yyerror("wrong number of parameters");
    }
    if (argc > 1) {
        yyin = fopen(argv[1], "r");
        if (yyin == NULL) {
            yyerror("cannot open file for reading");
        }
    }
    if (argc > 2) {
        yyout = fopen(argv[2], "w");
        if (yyout == NULL) {
            yyerror("cannot open file for writing");
        }
    }

    /* parse input file */
    yyparse();

    /* close input and output and tidy lexer */
    fclose(yyin);
    fclose(yyout);
    yylex_destroy();

    return 0;
}
