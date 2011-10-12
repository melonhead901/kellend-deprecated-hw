/*
** CSE 322, Lex/Yacc SKELETON: 
**
** jratml.flex: "RATML" formatter, java version, flex half, 
**
** see also jratml.y
**
** W. L. Ruzzo
**
*/

/*
** STUDENTS:
**
**   A: Add your name, Student ID number below:
**
**      Name:
**      Number:
**
**   B: You're FREE to change anything, but you don't NEED to 
**      change anything except the lex rules section.
*/

/* 
** Notes on the lexer/parser interface:
**
** 1. the TEXT token type passes (through yylval) a non-NULL 
**    value, namely a pointer to the text string.
** 2. Token names are defined in jratml.y, and written to Parser.java 
**    when byacc/j runs.
*/

%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

/* 
** table of recognized ratml tags, and associated token codes.  
** Obviously, codes must agree with those in jratml.y.
**
** Students: you don't need to use this, or the 
** associated lookup routine, but it might be convenient.
*/

final static String tag[] = {
  "<HTML>",      "<HEAD>",       "<TITLE>",      "<BODY>",     "<UL>",
 "</HTML>",     "</HEAD>",      "</TITLE>",     "</BODY>",    "</UL>",
     "<P>",        "<BR>",          "<LI>"
  };

final static short tagcode[] = {
  Parser.BHTML, Parser.BHEAD, Parser.BTITLE, Parser.BBODY, Parser.BUL, 
  Parser.EHTML, Parser.EHEAD, Parser.ETITLE, Parser.EBODY, Parser.EUL, 
  Parser.P,     Parser.BR,    Parser.LI
  };


/************************************************************* 
** lookup string in table of recognized tags.
** convert to upper case first, so case-insensitive.
** if found, return associated token code.
** if not found, it's a TEXT token.
** Binary search would be faster...
*/
short lookup(String str) {
    for(int i=0; i < tag.length; i++) {
        if(str.equals(tag[i])) {
            return tagcode[i];
        }
    }
    return Parser.TEXT;
}

%}

%%
/***  Lex rules below  ***  Students: definitely change them.  ***/
 
[ \t\n]                 { ; /* whitespace; don't bother parser */ }

[a-zA-Z]+ |
.                       { // text words & punctation to parser 
                          yyparser.yylval = new ParserVal(yytext());
                          return(Parser.TEXT);
                        }
 
"<P>"                   { // return paragraph token code, no text.
                          return(Parser.P); 
                        }

