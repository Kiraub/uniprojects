/* Author:  Erik Bauer
 * Version: 171103
 * Nutzung: Aufruf des Programms aus der shell mit der Eingabedatei/Ausgabedatei "moco inexample outexample"
 * Beschreibung:
 *  Dieses Programm liest eine Eingabe Datei im Textformat ein und gibt gefundende Modula2 Bezeichner aus.
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INTRO 1
#define DEBUG 0

FILE * fin;
FILE * fout;

int finuse;
int foutuse;

int checker(int check);
void errormsg(char* msg);

int main( int argc, char * argv[] )
{
    // Optionale Ausgabe von Programmaufruf und Dateiname
    if(INTRO)
    {
        printf("Hallo, dies ist %s\n", argv[0]);
        if(argc>1)
        {
            printf("Eingabedatei: %s\n", argv[1]);
        }
        if(argc>2)
        {
            printf("Ausgabedatei: %s\n", argv[2]);
        }
    }

    fin = stdin;
    finuse = 0;
    fout = stdout;
    foutuse = 0;

    if(argc>3)
    {
        errormsg("Zu viele Parameter.");
        return EXIT_SUCCESS;
    } else if(argc>2)
    {
        fin = fopen(argv[1], "r");
        finuse = 1;
        fout = fopen(argv[2], "w");
        foutuse = 1;
    } else if(argc>1)
    {
        fin = fopen(argv[1], "r");
        finuse = 1;
    } else
    {
        // Lies und Schreib in stdin/out
    }
    char buff[100];
    int index = 0;
    do
    {
        if(finuse)
        {
            buff[index] = fgetc(fin);
        } else 
        {
            // WIP
            buff[index] = getchar();
        }
        if(DEBUG)
        {
            printf("buff[index]= %c , index= %d\n", buff[index], index);
        }
        // Terminiere Zeichenkette
        if(buff[index] == ' ' || buff[index] == '\n' || buff[index] == EOF || buff[index] == ';' || buff[index] == '.')
        {
            // Kein leeres Wort ausgeben
            if(index && buff[0])
            {
                buff[index] = '\0';
                if(foutuse)
                {
                    fprintf(fout, "%s\n", buff);
                } else
                {
                    printf("%s\n", buff);
                }
                index = -1;
            }
        } else if(isalpha(buff[index]))
        // Naechstes Zeichen ist legal
        {
            if(DEBUG)
            {
                printf("Zeichen ist legal.\n");
            }
        } else if(index>=1 && isdigit(buff[index]))
        // Naechstes Zeichen ist eine Zahl
        {
            if(DEBUG)
            {
                printf("Zahl ist legal.\n");
            }
        } else
        // Naechstes Zeichen ist illegal
        {
            if(DEBUG)
            {
                printf("Zeichen oder Zahl ist illegal.\n");
            }
            buff[0] = '\0';
        }
        if(DEBUG)
        {
            getchar();
        }
    } while (buff[index++]!=EOF);
    return EXIT_SUCCESS;
}

void errormsg(char* msg)
{
    printf("Fehler: %s\n", msg);
    return;
}