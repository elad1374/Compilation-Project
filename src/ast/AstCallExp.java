package ast;

/******************************************************************/
/* EBNF Rule:                                                     */
/* callExp ::= [ var DOT ] ID LPAREN [ exp { COMMA exp } ] RPAREN */
/******************************************************************/

public class AstCallExp extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstVar var;      // Can be null if there is no 'var DOT'
    public String id;
    public AstExpList exps; // Can be null if there are no arguments

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstCallExp(int lineNumber, AstVar var, String id, AstExpList exps)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();
        
        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (var != null && exps != null) {
            System.out.format("====================== callExp -> var DOT ID( %s ) LPAREN exps RPAREN\n", id);
        } else if (var != null && exps == null) {
            System.out.format("====================== callExp -> var DOT ID( %s ) LPAREN RPAREN\n", id);
        } else if (var == null && exps != null) {
            System.out.format("====================== callExp -> ID( %s ) LPAREN exps RPAREN\n", id);
        } else { // var == null && exps == null
            System.out.format("====================== callExp -> ID( %s ) LPAREN RPAREN\n", id);
        }

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.var = var;
        this.id = id;
        this.exps = exps;
    }

    /*********************************************************/
    /* The printing message for a call expression AST node   */
    /*********************************************************/
    public void printMe()
    {
        /***********************************/
        /* AST NODE TYPE = AST CALL EXP    */
        /***********************************/
        System.out.format("AST NODE CALL EXP( %s )\n", id);

        /***************************************************/
        /* RECURSIVELY PRINT THE VAR AND THE ARGUMENTS...  */
        /***************************************************/
        if (var != null) var.printMe();
        if (exps != null) exps.printMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("CALL\nEXP\n(%s)", id));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (var != null) AstGraphviz.getInstance().logEdge(serialNumber, var.serialNumber);
        if (exps != null) AstGraphviz.getInstance().logEdge(serialNumber, exps.serialNumber);
    }
}

/******************************************************************/
/* This represents the linked list of expressions (arguments)     */
/******************************************************************/

class AstExpList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstExp head;
    public AstExpList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstExpList(int lineNumber, AstExp head, AstExpList tail)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;

        if (tail != null) System.out.print("====================== exps -> exp COMMA exps\n");
        if (tail == null) System.out.print("====================== exps -> exp\n");

        this.head = head;
        this.tail = tail;
    }

    public void printMe()
    {
        System.out.print("AST NODE EXP LIST\n");
        
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "EXP\nLIST");
        
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}