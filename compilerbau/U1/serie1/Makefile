#Source files
SOURCES := moco.c
# Object files
OBJECTS := moco.o
# Name of the achive for the distribution
ARCHIVE := MOCO

# main target
all: moco

# to build object-file u1-hw.o u1-hw.c is needed
moco.o: moco.c

# compile files and build executable
# $^ = inputs
# $@ = name of the target
moco: $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@

# build distribution
dist:
	tar -zcf $(ARCHIVE).tar.gz $(SOURCES) Makefile

# delete all files that were created by building the program
clean:
	$(RM) $(OBJECTS) $(ARCHIVE).tar.gz Moco
