#Source files
SOURCES := u1hw.c
# Object files
OBJECTS := u1hw.o
# Name of the achive for the distribution
ARCHIVE := U1HW

CC := gcc

# main target
all: HelloWorld

# to build object-file u1-hw.o u1-hw.c is needed
u1hw.o: u1hw.c

# compile files and build executable
# $^ = inputs
# $@ = name of the target
HelloWorld: $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@

# build distribution
dist:
	tar -zcf $(ARCHIVE).tar.gz $(SOURCES) Makefile

# delete all files that were created by building the program
clean:
	$(RM) $(OBJECTS) $(ARCHIVE).tar.gz HelloWorld
