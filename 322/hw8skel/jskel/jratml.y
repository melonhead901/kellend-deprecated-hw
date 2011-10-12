/*
** CSE 322, Lex/Yacc SKELETON: 
**
** jratml.y: "RATML" formatter, java version, yacc half
**
** see also jratml.flex
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
%{
  import java.io.*;

  /*************************************************************
   * the basic "parse tree" Node class; has left & right subtrees,
   * with various subclasses for different markup elements.  Print
   * methods walk tree and print it, roughly in postorder.
   */
  class Node {
    Node left; 
    Node right;
    Node(Object ileft, Object iright){
      left  = (Node) ileft;  //cast here saves many others in rule actions
      right = (Node) iright;
    }
    
    void print(int margin){
      if(left  != null) left.print(margin);
      if(right != null) right.print(margin);
    }
    
    // helper functions and data for rendering formatted output:

    static String line = "";    // output lines assembled here
    static int tempIndent = 0;  // temporary indent, current line only

    /*************************************************************
     ** blank: print a blank line, forcing out any partially full line
     ** if necessary
     */
    void blank(int margin) {
      cbr(margin);
      System.out.println();
    }

    /*************************************************************
     ** conditional line break: force out any partially full line if
     ** necessary; nothing to do if line buffer empty
     */
    void cbr(int margin){
      if(line.length() != 0) {
	br(margin);
      }
    }

    /*************************************************************
     ** line break: output current line, indented as needed, and reset
     ** for next line.
     */
    void br(int margin){
      int slack =  margin + tempIndent;
      while(slack-- > 0) {
	System.out.print(" ");
      }
      System.out.println(line);
      line = "";
      tempIndent = 0;
    }
  
    /*************************************************************
     ** put_text: add next word to text out stream; do line break
     ** first if it would otherwise extend past right margin.
     **
     ** KNOWN BUG: if next word is longer than LINEWD, a long
     ** line will be printed.
     */
    void put_text(int margin, String text){
      // need between-word space?
      int sp = (line.length() > 0) ? 1 : 0 ; 
      if(margin+tempIndent+sp+line.length()+text.length() > 80){
	br(margin);
      } 
      if(line.length() > 0) {
	line += " ";
      }
      line += text;
    }
  }

  // top level of parse tree; left is title, right is body
  class NodePage extends Node {
    NodePage(Object left, Object right){
      super(left,right);
    }
    void print(){
      if(left  != null) left.print(0);
      blank(0);
      if(right != null) right.print(0);
      cbr(0);
    }
  }

  // node holding a TEXT token
  class NodeTEXT extends Node {
    String text;
    NodeTEXT(Object left, Object istring){
      super(left, null);
      text = (String) istring;
    }
    void print(int margin){
      if(left!=null) left.print(margin);
      put_text(margin, text);
    }
  }

  // node for <BR> tag
  class NodeBR extends Node {
    NodeBR(){super(null,null);}
    void print(int margin){
      cbr(margin);
      if(left!=null) left.print(margin);
    }
  }

  // node for <P> tag
  class NodeP extends Node {
    NodeP(){super(null,null);}
    void print(int margin){
      blank(margin);
    }
  }

  // node for <UL></UL> wrapper
  class NodeUL extends Node {
    NodeUL(Object left){super(left, null);}
    void print(int margin){
      cbr(margin);
      if(left  != null)  left.print(margin+4);
      if(right != null) right.print(margin+4);
      cbr(margin+4);
    }
  }


  // node for <LI> tag
  class NodeLI extends Node {
    NodeLI(Object left){super(left,null);}
    void print(int margin){
      cbr(margin);
      tempIndent = -2;
      put_text(margin, "*");
      if(left != null) left.print(margin);
    }
  }

%}

/* 
** declare all lex tokens; TEXT passes a string value (hence 
** the <sval>); token code alone suffices for the others.
** token names correspond to html tags.
** "B" tokens are begining tags of wrappers;
** "E" tokens are corresponding end tags.
** "P", "BR" etc. are assumed NOT to have matching end tag.
**
** STUDENTS: these are what my solution uses, 
** but you are free to change them.
*/
%token BHTML BHEAD BTITLE BBODY BUL
%token EHTML EHEAD ETITLE EBODY EUL
%token P BR LI 
%token <sval> TEXT

/*
** declare all grammar nonterminals; all carry an Object
** type (hence the <obj>) via their $$, etc. variables. 
** first one/first rule define the start symbol.
*/
%type <obj> page aa bb pp words

%%

/*
** Yacc rules section.
**
** Students: you definitely need to change this (but read it first).
**
** This sample grammer says a page has one or more P tokens, plus
** optional TEXT tokens.  It serves no purpose other than to show an
** example of grammar/lex/parse tree/formatter interfaces.
*/

page:   aa bb                   { $$ = new NodePage($1, $2);
                                  ((NodePage)$$).print();
                                }
      ;

aa:     words P                 { $$ = new Node($1, null); } 
      ;

words:  words TEXT              { $$ = new NodeTEXT( $1, $2); }
      | /* empty */             { $$ = null; }
      ; 

bb:     bb TEXT                 { $$ = new NodeTEXT( $1, $2); }
      | bb pp                   { $$ = new Node($1, $2); } 
      | /* empty */             { $$ = null; }
      ;

pp:     P                       { $$ = new NodeP(); }
      ;

%%

  private Yylex lexer;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }

  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }

  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }

  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java + JFlex Ratml demo");
    System.out.println("=============================");
    if ( args.length < 1 ) {
      System.out.println("Usage: java Parser 0/1 filename");
      System.out.println("where 0 means run yacc; 1 means just dump lex tokens");
    }
    Parser yyparser = new Parser(new FileReader(args[1]));
    yyparser.yydebug = false ; //true turns on yacc debug printouts;
    if(args[0].equals("1")){
      System.out.println("token dump:");
      //dump tokens
      int tok;
      while((tok=yyparser.lexer.yylex()) > 0){
	System.out.print("Tok="+tok);
	if(null != yyparser.yylval && null != yyparser.yylval.sval){
	  System.out.print(","+yyparser.yylval.sval);
	}
	System.out.println('.');
      }
    }
    else {
      yyparser.yyparse();
    }
  }
