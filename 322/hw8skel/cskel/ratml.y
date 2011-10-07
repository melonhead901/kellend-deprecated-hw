/*
** CSE 322, Lex/Yacc SKELETON: 
**
** ratml.y: "RATML" formatter, yacc half
**
** see also ratml.l, ratml.h
**
** W. L. Ruzzo
**
*/

/*
** STUDENTS:
**
**   A: Add your name and student number below:
**
**      Name:
**      Number:
**
**   B: You're FREE to change anything, but you don't NEED to 
**      change anything except the %type declarations and the 
**      yacc rules sections.
*/

/* 
** declare all lex tokens; all are of "string" type,
** but only TEXT passes a non-NULL string.
** token names correspond to html tags.
** "B" tokens are begining tags of wrappers;
** "E" tokens are corresponding end tags.
** "P", "BR" etc. are assumed NOT to have matching end tag.
*/
%token <str> BHTML BHEAD BTITLE BBODY BUL
%token <str> EHTML EHEAD ETITLE EBODY EUL
%token <str> P BR LI TEXT 

/*
** declare all grammar nonterminals; 
** all are of "node" type
*/
%type <node> page aa bb pp words

%{
/*
** include node typedefs
** Students: go READ this file:
*/
#include "ratml.h"

#include <stdio.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>
%}

/*
** "union" declares the types of values associated with all 
** tokens and nonterminals in the grammar.
**   - lexer passes string associated with all TEXT tokens;
**   - "parse tree" nodes are associated with all nonterminals
*/
%union { 
    char   *str;
    node_t *node;
}

%{

/* 
** Following items are associated the text output stream; 
** see fmt() and associated routines
*/

#define LINEBUF 200
#define LINEWD 80

typedef struct {
    int indent;         /* temporary indent for current line; <0 gives outdent */
    char buf[LINEBUF];
} tout_t;

tout_t tout = {0, ""};

/* 
** function prototypes 
*/
node_t *new_node(enum node_kind kind, node_t *l, node_t *r, char *t);
char *strdupl(char*);
void dump(node_t *p);
void fmt (node_t *p, int margin);
void fmt3(node_t *p, int margin);
void br   (int margin);
void cbr  (int margin);
void blank(int margin);
void put_text(char *text, int margin);

%}

/*
** the start symbol in the grammar 
*/
%start page

%% /* ---------------------------------------------------------------------- */

/*
** Yacc rules section.
**
** Students: you definitely need to change this (but read it first).
**
** This sample grammer says a page has one or more P tokens, plus
** optional TEXT tokens.  It serves no purpose other than to show an
** example of grammar/lex/parse tree/formatter interfaces.
*/

page:   aa bb                       { $$ = new_node(nop_k, $1, $2, NULL); 
                                      /* change 0 ==> 1 for a crude tree dump */
                                      if(0) dump($$);
                                      /* format, starting with default params */
                                      fmt($$,0); 
                                      /* force out possible partial last line  */
                                      cbr(0); 
                                    }
      ;

aa:     words P                     { $$ = new_node(nop_k, $1, NULL, NULL); } 
      ;

words:  words TEXT                  { $$ = new_node(nop_k, $1, NULL, $2); }
      | /* empty */                 { $$ = NULL; /* warning: omission of NULL in epsilon rules gives obscure errors */ }
      ; 

bb:     bb TEXT                     { $$ = new_node(nop_k, $1, NULL, $2); }
      | bb pp                       { $$ = new_node(nop_k, $1, $2, NULL); } 
      | /* empty */                 { $$ = NULL; /* warning: omission of NULL in epsilon rules gives obscure errors */ }
      ;

pp:     P                           { $$ = new_node(p_k, NULL, NULL, NULL); }
      ;

%% /* ---------------------------------------------------------------------- */

/*************************************************************
** new_node: create a new "parse tree" node
** and initialize its fields.
**
** See html.h for description of "parse tree".  Really.
*/
node_t *new_node(enum node_kind kind, node_t *l, node_t *r, char *t) {
    node_t *newp;

    newp = (node_t *) malloc(sizeof(node_t));
    newp -> kind = kind;
    newp -> l = l;
    newp -> r = r;
    newp -> t = t;
    return newp;
}

/*************************************************************
** dump: recursively walks & prints the tree 
**
** Format: "(kind, left-subtree, right-subtree, text)\n"
** This might be helpful for debugging.
*/
void dump(node_t *p) {
  if(p != NULL) {
    printf("(%d,",p->kind);
    dump(p->l); printf(",");
    dump(p->r); printf(",");
    if(p->t != NULL) {printf("%s",p->t);}
    printf(")\n");
  }
}

/*************************************************************
** Fmt: formatting routines.
**
** all take margin parameter.
** margin = left margin; initially 0; incremented in nested lists
*/

/*************************************************************
** fmt: main formatting routine
**
** print contents of this node (the text pointer, if any)
** and, recursively, its subtrees (if any), adjusting margin, 
** as needed for each kind of formatting element.
*/
void fmt(node_t *p, int margin) {
    if(p == NULL) return;
    switch (p->kind) {
        /* line break tag */
        case br_k:
            br(     margin); 
            fmt3(p, margin);
            break;

        /* paragraph tag */
        case p_k:
            blank(  margin); 
            fmt3(p, margin);
            break;

        /* no formatting op (but subtrees might) */
        case nop_k:
            fmt3(p, margin);
            break;

        /* parent of a title */
        case title_k:
            fmt3(p, margin);
            blank(  margin);
            break;

        /* parent of unnumbered list */
        case ul_k: 
            cbr(    margin);
            fmt3(p, margin+4);
            cbr(    margin+4);
            break;

        /* parent of a list item */
        case li_k:
            cbr(    margin);
            tout.indent = -2;
            put_text("*", margin);
            fmt3(p, margin);
            break;

        default:
            printf("invalid node kind %d.\n", p->kind);
            fmt3(p, margin);
    }
}

/*************************************************************
** fmt3: 
**
** fmt all three subparts of a node.
** arg must be non-NULL
*/
void fmt3(node_t *p, int margin) {
    fmt(     p->l, margin);
    fmt(     p->r, margin); 
    put_text(p->t, margin); 
}

/*************************************************************
** blank: print a blank line, forcing out any 
** partially full line if necessary
*/
void blank(int margin) {
    cbr(margin);
    printf("\n");
}

/*************************************************************
** conditional line break: force out any 
** partially full line if necessary;
** nothing to do if line buffer empty 
*/
void cbr(int margin) {
    if('\0' != tout.buf[0]) {
        br(margin);
    }
}

/*************************************************************
** line break: output current line, indented, as needed, 
** and reset for next line.
*/
void br(int margin) {
    int slack;

    /* calculate how many leading spaces are */
    /* needed to achieve correct alignment.  */

    slack = margin + tout.indent;

    /* indent as needed */
    while (slack-- > 0) {
        printf(" ");
    }

    /* print and reset */
    printf("%s\n", tout.buf);
    tout.buf[0] = '\0';
    tout.indent = 0;
}

/*************************************************************
** put_text:
** add next word to text out stream; do line break first if 
** it would otherwise extend past right margin.
**
** KNOWN BUG: if next word is longer than LINEWD, a long
** line will be printed; if it is longer than LINEBUF-1
** it will be truncated.  (LINEBUF is much bigger than 
** LINEWD, so it's unlikely.)
*/
void put_text(char *text, int margin) {

    if(text==NULL) return;

    /* line break if new word overflows line */
    if (margin 
        + tout.indent 
        + strlen(tout.buf)
        + (tout.buf[0] != '\0')   /* 1 separating space, if nonempty */
        + strlen(text) > LINEWD) 
    {
        br(margin);
    }

    /* separate new word from previous, if any, by one space */
    if (tout.buf[0] != '\0') {
        strcat(tout.buf, " ");  
    }

    /* add next word */
    strncat(tout.buf, text, LINEBUF-1);
}

/*****
 * 
 * main program -- 
 * if we're still debugging the lexer, then 
 * just dump successive tokens to stdout.
 * Otherwise, just call yyparse.
 *
 */

extern char* yytext;

int main(void) {
  
#if(defined(LEXDEBUG) && LEXDEBUG)

  int tokencode;

  while(tokencode=yylex()){
    printf("Token:%d, String:%s.\n", tokencode, yytext);
  }

#else

  yyparse();

#endif

  return 0;
}
