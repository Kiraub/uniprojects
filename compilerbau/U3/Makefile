.PHONY: clean

OBJECTS := main.o scanner.o parser.o diagnosis.o
GENERATED := scanner.c scanner.h

all: moco

moco: $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@

main.c: scanner.c diagnosis.h parser.h

scanner.l: parser.h diagnosis.h

scanner.c: scanner.l
	flex scanner.l

parser.c: scanner.c parser.h diagnosis.h

diagnosis.c: diagnosis.h scanner.c

clean:
	$(RM) $(OBJECTS) $(GENERATED) moco
