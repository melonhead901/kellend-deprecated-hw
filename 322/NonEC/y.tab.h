#define BHTML 257
#define BHEAD 258
#define BTITLE 259
#define BBODY 260
#define BUL 261
#define EHTML 262
#define EHEAD 263
#define ETITLE 264
#define EBODY 265
#define EUL 266
#define P 267
#define BR 268
#define LI 269
#define TEXT 270
typedef union { 
    char   *str;
    node_t *node;
} YYSTYPE;
extern YYSTYPE yylval;
