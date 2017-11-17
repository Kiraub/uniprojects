#include <stdio.h>

int main( int argc, char * argv[] )
{
    printf("This is %s.\n", argv[0]);
    char c;
    FILE f = fopen(argv[1], 'r');
    while ( c = fgetchar() != EOF )
    {
        putchar(c);
    }
    fclose(f);
}
