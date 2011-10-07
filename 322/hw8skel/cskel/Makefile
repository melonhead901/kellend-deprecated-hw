# LEXDEBUG=1  causes "main()" to just call yylex() & list all tokens returned.
#   This might be useful while you debug your lex definitions.
# LEXDEBUG=0 causes "main()" to call the parser instead.
CC = gcc -DLEXDEBUG=0 
LEX = lex
YACC = yacc -t

###
#
# default make entry runs parser (rebuilt if needed, of course) on a
# simple test file.  You should make your own, much simpler, test files 
# the early stages of debugging.  I suggest you name them test1.html, 
# etc., and duplicate/modify the following 3 lines accordingly.
test.out: test.html parser
	./parser < test.html > test.out
	cat test.out

parser: lex.yy.o y.tab.o
	$(CC) -o parser y.tab.o lex.yy.o

lex.yy.o: lex.yy.c y.tab.h 

lex.yy.o y.tab.o: ratml.h

lex.yy.c: ratml.l y.tab.h Makefile
	$(LEX) ratml.l

y.tab.c y.tab.h: ratml.y Makefile
	$(YACC) -dv ratml.y

####
#
# "make clean" removes all rebuildable files.
#
clean:
	rm -f *~ lex.yy.c lex.yy.o y.tab.c y.tab.h y.tab.o y.output parser test.out

###
#
# "test.out.std" is the result of running Ruzzo's solution on
# test.html.  "make diff" will compare your output to it.  A correct
# solution is not required to exactly reproduce it (and exactly
# reproducing it isn't a guarantee that your solution is correct) but
# you might find this convenient anyway, for late-stage debugging.
# You should make your own, much simpler, test files for the early stages.
diff:	test.out
	diff test.out test.out.std

#### 
#
# HW #7 turnin:
# 
# You might find it convenient to add some rules so that "make turnin"
# will collect your solution for grading.  E.g., do a "make clean",
# then bundle up all relevant files using tar, gzip etc.  If your
# solution includes files other than the standard ones, please be sure
# to include a working Makefile and documentation so we know how to
# rebuild your program.
#
# If you do extensions to the basic assignment, please document them
# in a README file, and include a test file named testme.html that
# will show off what you've done.  (Use testme2.html, testme3.html,
# ... if you need to give us more than one test file.)
#


