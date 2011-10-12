# download and install jflex from http://jflex.de/
# download and install byacc/j from http://byaccj.sourceforge.net/
# set following 3 lines to the relevant paths on your system 
JFLEX  = ~ruzzo/src/jflex-1.4.3/jflex-1.4.3/bin/jflex 
BYACCJ = ~ruzzo/src/byaccj/yacc.macosx
JAVAC  = javac

# set following to 1 causes "parser" to just dump token 
# list; helpful for lex debugging; set to 0 to run yacc parser
LEXDEBUG = 0

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	rm -f *~ *.class *.java

Parser.class: Yylex.java Parser.java Makefile test.html
	$(JAVAC) Parser.java
	java Parser $(LEXDEBUG) test.html

Yylex.java: jratml.flex
	$(JFLEX) jratml.flex

Parser.java: jratml.y
	$(BYACCJ) -J jratml.y
