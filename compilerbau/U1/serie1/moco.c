/* Author:  Erik Bauer
 * Version: 171103
 * Nutzung: Aufruf des Programms aus der shell mit der Eingabedatei/Ausgabedatei "moco inexample outexample"
 * Beschreibung: Dieses Programm liest eine Eingabe Datei im Textformat ein und gibt gefundende Modula2 Bezeichner aus.
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define DEBUG 0
#define DEBUGLEVEL 1

void init();
// Funktion zur Initialisierung
void appendc(char* cs, char c);
// Funktion zum erweitern des Strings
void clear_buffers();
// Funktion zum leeren des Strings
void dispose_buffers();
// Funktion zum freigeben des dynamischen Speichers

void scanner();
// Funktion zum lesen des Eingabestreams yyin, sowie zum Ausgeben von yytext an yyout

void debuglog(char* msg, int line, int level);
// Funktion zum ausgeben von debug log

void yyerror(char* msg);
// Funktion zur Ausgabe von Fehlermeldungen
char* yytext;
// Variable welche stets den aktuellen Bezeichner beinhaltet

FILE* yyin;
FILE* yyout;

int main(int argc, char* argv[])
{
    init();
    debuglog("Init.", __LINE__, 3);

    if(argc>3)
    {
        yyerror("wrong number of parameters");
    } if(argc>2)
    {
        yyout = fopen(argv[2], "w");
        if(yyout==NULL)
        {
            yyerror("can't open file for writing");
        }
    } if(argc>1)
    {
        yyin = fopen(argv[1], "r");
        if(yyin==NULL)
        {
            yyerror("can't open file for reading");
        }
    }

    scanner();

    dispose_buffers();

    return 0;
}

void init()
{
    yyin = stdin;
    yyout = stdout;
    yytext = malloc(sizeof(char));
    yytext[0] = '\0';
    return;
}

void appendc(char* cs, char c)
{
    int lcs = strlen(cs)+1;
    // alte laenge des strings entspricht offset des nullzeichens plus eins
    cs = realloc(cs, (lcs+1)*sizeof(char));
    // neue laenge entspricht alter laenge + neues zeichen + nullzeichen
    debuglog("String neu alloziiert.", __LINE__, 3);
    cs[lcs-1] = c;
    cs[lcs] = '\0';
    debuglog("String neu beschrieben.", __LINE__, 3);
    return;
}

void clear_buffers()
{
    free(yytext);
    yytext = malloc(sizeof(char));
    yytext[0] = '\0';
    return;
}

void dispose_buffers()
{
    free(yytext);
    return;
}


void scanner()
{
    char c;
    // neustes eingelesenes Zeichen
    int i = 0;
    // ignorier boolean
    do
    {
        c = fgetc(yyin);
        if(isalnum(c) && i==0)
        {
            if(yytext[0] || isalpha(c))
            {
                appendc(yytext, c);
                debuglog("Appended word.", __LINE__, 2);
            } else
            {
                i = 1;
                debuglog("Started ignoring alnum.", __LINE__, 3);
            }
        } else if(i==1)
        {
            while(isalnum(c=fgetc(yyin)))
            {
                debuglog("Ignored character inside of ignored word.", __LINE__, 2);
            }
            ungetc(c, yyin);
            i = 0;
            debuglog("Stopped ignoring alnum.", __LINE__, 3);
        } else
        {
            if(yytext[0] != '\0')
            {
                fprintf(yyout, "%s\n", yytext);
                clear_buffers();
                debuglog("Printed and reset word.", __LINE__, 1);
            } else
            {
                debuglog("Ignored character outside of ignored word.", __LINE__, 2);
            }
        }
    } while( c!=EOF );
    return;
}

void debuglog(char* msg, int line, int level)
{
    if( DEBUG && (DEBUGLEVEL >= level))
    {
        fprintf(stdout,"DEBUG:\n\t%s\n\tfile: %s\n\tline: %d\n", msg, __FILE__, line);
    }
    return;
}


void yyerror(char* msg)
{
    debuglog("An error occured.", __LINE__, 3);
    fprintf(stderr, "error in %s: %s\n", __FILE__, msg);
    exit(EXIT_FAILURE);
}