#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

FILE* yyin;
FILE* yyout;
int yylen;
char* yytext;

void yyerror(char* msg) {
	fprintf(stderr, "error: %s\n", msg);
	exit(1);
}

int main(int argc, char* argv[]) {
	int capacity = 2;
	int current_character;

	yyin = stdin;
	yyout = stdout;
	yylen = 0;
	yytext = (char*) calloc(capacity, sizeof(char));

	if (yytext == NULL) {
		yyerror("could not allocate memory");
	}

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

	current_character = fgetc(yyin);
	while (current_character != EOF) {
		if (isalpha(current_character)) {
			yytext[yylen++] = current_character;
			
			current_character = fgetc(yyin);
			
			while (isalpha(current_character) || isdigit(current_character)) {
				if (yylen+1 >= capacity) {
					capacity *= 2;
					yytext = (char*) realloc(yytext, capacity);
					if (yytext == NULL) {
						yyerror("could not allocate memory");
					}
				}
				
				yytext[yylen++] = (char) current_character;
				yytext[yylen] = '\0';
				current_character = fgetc(yyin);				
			}
			
			fprintf(yyout, "%s\n", yytext);

			memset(yytext, 0, capacity);
			yylen = 0;
		} else {
			current_character = fgetc(yyin);
		}
		
	}

	fclose(yyin);
	fclose(yyout);
	free(yytext);

	return 0;
} 
