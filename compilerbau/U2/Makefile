.Phony: clean

SOURCES := main.c
OBJECTS := main.o
ARCHIVE := moco

all: moco

main.o: main.c

moco: $(OBJECTS)	
	$(CC) $(LDFLAGS) $^ -o $@

dist:
	tar -zcf $(ARCHIVE).tar.gz $(SOURCES) Makefile

clean:
	$(RM) $(OBJECTS) $(ARCHIVE).tar.gz moco