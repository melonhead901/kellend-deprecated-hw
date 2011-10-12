#ifndef lint
static const char yysccsid[] = "@(#)yaccpar	1.9 (Berkeley) 02/21/93";
#endif

#include <stdlib.h>
#include <string.h>

#define YYBYACC 1
#define YYMAJOR 1
#define YYMINOR 9
#define YYPATCH 20070509

#define YYEMPTY (-1)
#define yyclearin    (yychar = YYEMPTY)
#define yyerrok      (yyerrflag = 0)
#define YYRECOVERING (yyerrflag != 0)

extern int yyparse(void);

static int yygrowstack(void);
#define YYPREFIX "yy"
#line 44 "ratml.y"
/*
** include node typedefs
** Students: go READ this file:
*/
#include "ratml.h"

#include <stdio.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>
#line 62 "ratml.y"
typedef union { 
    char   *str;
    node_t *node;
} YYSTYPE;
#line 68 "ratml.y"

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

#line 69 "y.tab.c"
#define BHTML 257
#define BHEAD 258
#define BTITLE 259
#define BBODY 260
#define BUL 261
#define BH1 262
#define BOL 263
#define EHTML 264
#define EHEAD 265
#define ETITLE 266
#define EBODY 267
#define EUL 268
#define EH1 269
#define EOL 270
#define P 271
#define BR 272
#define LI 273
#define TEXT 274
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    2,    3,    3,    4,    4,    5,    5,   11,
   11,   14,   14,   12,    9,   10,   13,    8,    7,    7,
    6,    6,
};
short yylen[] = {                                         2,
    3,    6,    3,    2,    1,    2,    1,    2,    1,    2,
    1,    2,    1,    3,    1,    1,    3,    3,    3,    0,
    2,    0,
};
short yydefred[] = {                                      0,
    0,    0,    0,    0,   22,    0,    1,    0,    0,    0,
    0,    0,    0,    0,    0,   21,    4,   15,    6,   16,
    8,    3,   22,   10,    0,   12,   22,   22,    0,    0,
    0,    0,   13,    0,   18,   14,   17,    2,   19,
};
short yydgoto[] = {                                       2,
    4,    6,    8,    9,   10,   33,   29,   17,   19,   21,
   12,   24,   26,   13,
};
short yysindex[] = {                                   -237,
 -239,    0, -225, -215,    0, -223,    0, -257, -218, -224,
 -212, -202, -201, -199, -210,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -210,    0,    0,    0, -204, -203,
 -205, -258,    0, -222,    0,    0,    0,    0,    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,    0,    0,    0,    0, -230, -238,
 -216, -246, -260,    0, -200,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -198,    0,    0,    0,    0, -216,
    0,    0,    0, -211,    0,    0,    0,    0,    0,
};
short yygindex[] = {                                      0,
    0,    0,  -22,    0,    0,   -5,   16,    0,    0,    0,
    0,    0,    0,    0,
};
#define YYTABLESIZE 72
short yytable[] = {                                      11,
   11,   11,   15,   15,   32,   34,   11,   11,   38,   11,
   11,   11,   11,   11,    9,   16,   16,   30,    3,    1,
    9,    9,    7,    9,    9,    9,    9,    9,    7,    7,
    5,    7,    7,    5,    7,    7,    5,    5,   15,    5,
   31,   14,    5,    5,   13,   13,   13,   20,    7,   39,
   28,   16,   18,   22,   13,   13,   20,   13,   20,   23,
   27,   25,   28,   35,   37,   36,    0,   20,    0,    0,
    0,   20,
};
short yycheck[] = {                                       5,
  261,  262,  261,  261,   27,   28,  267,  268,  267,  270,
  271,  272,  273,  274,  261,  274,  274,   23,  258,  257,
  267,  268,  261,  270,  271,  272,  273,  274,  267,  268,
  261,  270,  271,  259,  273,  274,  267,  268,  261,  270,
   25,  265,  273,  274,  261,  262,  263,  272,  264,   34,
  273,  274,  271,  266,  271,  272,  268,  274,  270,  262,
  260,  263,  273,  268,  270,  269,   -1,  268,   -1,   -1,
   -1,  270,
};
#define YYFINAL 2
#ifndef YYDEBUG
#define YYDEBUG 1
#endif
#define YYMAXTOKEN 274
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"BHTML","BHEAD","BTITLE","BBODY",
"BUL","BH1","BOL","EHTML","EHEAD","ETITLE","EBODY","EUL","EH1","EOL","P","BR",
"LI","TEXT",
};
char *yyrule[] = {
"$accept : page",
"page : BHTML html EHTML",
"html : BHEAD head EHEAD BBODY body EBODY",
"head : BTITLE words ETITLE",
"body : body listHead",
"body : bodyB",
"bodyB : bodyB p",
"bodyB : bodyC",
"bodyC : bodyC br",
"bodyC : bodyD",
"bodyD : bodyD h1Head",
"bodyD : bodyE",
"bodyE : bodyE olistHead",
"bodyE : words",
"h1Head : BH1 words EH1",
"p : P",
"br : BR",
"olistHead : BOL list EOL",
"listHead : BUL list EUL",
"list : LI body list",
"list :",
"words : body TEXT",
"words :",
};
#endif
#if YYDEBUG
#include <stdio.h>
#endif

/* define the initial stack-sizes */
#ifdef YYSTACKSIZE
#undef YYMAXDEPTH
#define YYMAXDEPTH  YYSTACKSIZE
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH  500
#endif
#endif

#define YYINITSTACKSIZE 500

int      yydebug;
int      yynerrs;
int      yyerrflag;
int      yychar;
short   *yyssp;
YYSTYPE *yyvsp;
YYSTYPE  yyval;
YYSTYPE  yylval;

/* variables for the parser stack */
static short   *yyss;
static short   *yysslim;
static YYSTYPE *yyvs;
static int      yystacksize;
#line 161 "ratml.y"
 /* ---------------------------------------------------------------------- */

// The current order in an OL. 0 if we are not in an OL
int ol_loc = 0;

char* vals[]= {"0", "1","2","3","4","5","6","7","8","9","10"};

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
			
		/* parent of numbered list */
        case ol_k:
			ol_loc = 1;
            cbr(    margin);
            fmt3(p, margin+4);
            cbr(    margin+4);
			ol_loc = 0;
            break;
			
        /* parent of a list item */
        case li_k:
            cbr(    margin);
            tout.indent = -2;
			if(!ol_loc) {
				put_text("*", margin);
			} else {
				char buf[5];
				put_text(vals[ol_loc], margin);
				ol_loc++;
			}			
            fmt3(p, margin);
            break;
			
		/* parent of an h1 item */
		case h1_k:
			put_text("***", margin);
			fmt3(p,margin);
			put_text("***", margin);
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
#line 464 "y.tab.c"
/* allocate initial stack or double stack size, up to YYMAXDEPTH */
static int yygrowstack(void)
{
    int newsize, i;
    short *newss;
    YYSTYPE *newvs;

    if ((newsize = yystacksize) == 0)
        newsize = YYINITSTACKSIZE;
    else if (newsize >= YYMAXDEPTH)
        return -1;
    else if ((newsize *= 2) > YYMAXDEPTH)
        newsize = YYMAXDEPTH;

    i = yyssp - yyss;
    newss = (yyss != 0)
          ? (short *)realloc(yyss, newsize * sizeof(*newss))
          : (short *)malloc(newsize * sizeof(*newss));
    if (newss == 0)
        return -1;

    yyss  = newss;
    yyssp = newss + i;
    newvs = (yyvs != 0)
          ? (YYSTYPE *)realloc(yyvs, newsize * sizeof(*newvs))
          : (YYSTYPE *)malloc(newsize * sizeof(*newvs));
    if (newvs == 0)
        return -1;

    yyvs = newvs;
    yyvsp = newvs + i;
    yystacksize = newsize;
    yysslim = yyss + newsize - 1;
    return 0;
}

#define YYABORT goto yyabort
#define YYREJECT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse(void)
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register const char *yys;

    if ((yys = getenv("YYDEBUG")) != 0)
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = YYEMPTY;

    if (yyss == NULL && yygrowstack()) goto yyoverflow;
    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if ((yyn = yydefred[yystate]) != 0) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, reading %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: state %d, shifting to state %d\n",
                    YYPREFIX, yystate, yytable[yyn]);
#endif
        if (yyssp >= yysslim && yygrowstack())
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = YYEMPTY;
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;

    yyerror("syntax error");

#ifdef lint
    goto yyerrlab;
#endif

yyerrlab:
    ++yynerrs;

yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: state %d, error recovery shifting\
 to state %d\n", YYPREFIX, *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yysslim && yygrowstack())
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("%sdebug: error recovery discarding state %d\n",
                            YYPREFIX, *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("%sdebug: state %d, error recovery discards token %d (%s)\n",
                    YYPREFIX, yystate, yychar, yys);
        }
#endif
        yychar = YYEMPTY;
        goto yyloop;
    }

yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: state %d, reducing by rule %d (%s)\n",
                YYPREFIX, yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    if (yym)
        yyval = yyvsp[1-yym];
    else
        memset(&yyval, 0, sizeof yyval);
    switch (yyn)
    {
case 1:
#line 116 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, NULL, NULL); 
                                      /* change 0 ==> 1 for a crude tree dump */
                                      if(0) dump(yyval.node);
                                      /* format, starting with default params */
                                      fmt(yyval.node,0); 
                                      /* force out possible partial last line  */
                                      cbr(0); 
                                    }
break;
case 2:
#line 125 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-4].node, yyvsp[-1].node, NULL); /* warning: omission of NULL in epsilon rules gives obscure errors */ }
break;
case 3:
#line 127 "ratml.y"
{ yyval.node = new_node(title_k, yyvsp[-1].node, NULL, NULL); }
break;
case 4:
#line 129 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 5:
#line 130 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[0].node, NULL, NULL); }
break;
case 6:
#line 132 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 7:
#line 133 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[0].node, NULL, NULL); }
break;
case 8:
#line 135 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 9:
#line 136 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[0].node, NULL, NULL); }
break;
case 10:
#line 138 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 11:
#line 139 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[0].node, NULL, NULL); }
break;
case 12:
#line 141 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 13:
#line 142 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[0].node, NULL, NULL); }
break;
case 14:
#line 144 "ratml.y"
{ yyval.node = new_node(h1_k, yyvsp[-1].node, NULL, NULL); }
break;
case 15:
#line 146 "ratml.y"
{ yyval.node = new_node(p_k, NULL, NULL, NULL); }
break;
case 16:
#line 148 "ratml.y"
{ yyval.node = new_node(br_k, NULL, NULL, NULL); }
break;
case 17:
#line 150 "ratml.y"
{ yyval.node = new_node(ol_k, yyvsp[-1].node, NULL, NULL); }
break;
case 18:
#line 152 "ratml.y"
{ yyval.node = new_node(ul_k, yyvsp[-1].node, NULL, NULL); }
break;
case 19:
#line 154 "ratml.y"
{ yyval.node = new_node(li_k, yyvsp[-1].node, yyvsp[0].node, NULL); }
break;
case 20:
#line 155 "ratml.y"
{ yyval.node = NULL; }
break;
case 21:
#line 157 "ratml.y"
{ yyval.node = new_node(nop_k, yyvsp[-1].node, NULL, yyvsp[0].str); }
break;
case 22:
#line 158 "ratml.y"
{ yyval.node = NULL; /* warning: omission of NULL in epsilon rules gives obscure errors */ }
break;
#line 740 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("%sdebug: after reduction, shifting from state 0 to\
 state %d\n", YYPREFIX, YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("%sdebug: state %d, reading %d (%s)\n",
                        YYPREFIX, YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("%sdebug: after reduction, shifting from state %d \
to state %d\n", YYPREFIX, *yyssp, yystate);
#endif
    if (yyssp >= yysslim && yygrowstack())
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;

yyoverflow:
    yyerror("yacc stack overflow");

yyabort:
    return (1);

yyaccept:
    return (0);
}
