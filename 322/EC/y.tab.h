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
typedef union { 
    char   *str;
    node_t *node;
} YYSTYPE;
extern YYSTYPE yylval;
