/*
** CSE 322, Lex/Yacc Solution: 
**
** ratml.h: "RATML" formatter, header file
**
** see also ratml.l, ratml.y
**
** W. L. Ruzzo
**
*/

/*
** Students: You are free to change this file, but it's not necessary
** to do so. 
**
** DO read the parse tree documentation in the long comment below.
*/

#define TRUE 1
#define FALSE 0

/*
** enum for kinds of "parse tree" nodes; see explanation below
*/
enum node_kind {/* what kind of node:                       */
    nop_k,      /* no formatting op (but subtrees might)    */
    title_k,    /* parent of a title                        */
    ul_k,       /* parent of unnumbered list                */
    li_k,       /* parent of a list item                    */
	h1_k,		/* parent of an h1 item						*/
    p_k,        /* paragraph tag  (childless?)              */
    br_k,       /* line break tag (childless?)              */
	ol_k		/* ordered list item						*/
  } ;

/*
** typedef for "parse tree" nodes; see explanation below
*/
typedef struct node_s {
  enum node_kind kind;         
  struct node_s *l, *r; /* child pointers (if any)         */
  char   *t;            /* associated text string (if any) */
} node_t;


/* *****************************************************************

                THE RATML "PARSE TREE"

Your parser should build a tree structure that reflects the structure
of the ratml page.  I loosely call it a "parse tree"; it isn't
literally the parse tree according to your grammar, but it has the
same structural info, such as what stuff is in a list versus outside
it.  All of the tree nodes will be of type node_t, defined above.
Each has 4 fields:

 1. a code indicating what kind of node it is (the enum above)
 2. pointer to a left child node
 3. pointer to a right child node 
 4. pointer to a character string

The "kind" code should always be set.  Few if any nodes will use all
three of the pointers; set unused ones to NULL.

The formatting routine will traverse the tree in
left-to-right order, printing whatever text strings it finds along the
way.  If all nodes are of the "nop_k" kind, meaning "no special
formatting operation", then all the text will be printed in default
format: left justified, not indented, etc.  

Other kinds of nodes cause changes in the output formatting of nodes
in their subtree.  Notably, text below a "ul_k" node will be more
deeply indented.  I suggest you build your tree so that a <UL>
... </UL> wrapper creates a single "ul_k" node having just one child,
say the left, which is the root of the subtree representing everything
within that wrapper; right child and text pointers will be NULL.

Other suggestions:

  <P> and <BR> are not wrappers; don't give them children or
  associated text.  The formatter's action when encountering these
  nodes is to generate a blank line or line break, resp.

  A string of words in a paragraph become a linked list of nop_k
  nodes, each of whose text pointer points to one of the words (as
  returned by the lexer).

The above outline is a suggestion, not a requirement.  In fact, the
formatter will traverse and process all non-null pointers in each
node, so if you find it convenient to have children of br_k nodes or
whatever, that's fine.  Look at "fmt" and associated routines in
ratml.y to see in detail how the formatter works.

********************************************************************* */
