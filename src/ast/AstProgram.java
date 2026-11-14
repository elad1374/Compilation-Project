package ast;

/********************************************************************************/
/* EBNF Rule:                                                                   */
/* program ::= dec { dec }                                                      */
/********************************************************************************/

public class AstProgram extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstDecList decs;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstProgram(AstDecList decs)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== program -> decs\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.decs = decs;
    }

    /*********************************************************/
    /* The printing message for the program root AST node    */
    /*********************************************************/
    public void printMe()
    {
        /*********************************/
        /* AST NODE TYPE = AST PROGRAM   */
        /*********************************/
        System.out.print("AST NODE PROGRAM\n");

        /**************************************************/
        /* RECURSIVELY PRINT THE LIST OF DECLARATIONS...  */
        /**************************************************/
        if (decs != null) decs.printMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                "PROGRAM");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (decs != null) AstGraphviz.getInstance().logEdge(serialNumber, decs.serialNumber);

        // TODO optional here or later we do it on main
        // AstGraphviz.getInstance().finalizeFile();
    }
}

/******************************************************************/
/* CLASSES BELOW ARE IN THE SAME FILE, VISIBLE ONLY IN 'ast' PACK */
/* This represents the linked list of declarations (dec)          */
/******************************************************************/

class AstDecList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstDec head;
    public AstDecList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstDecList(AstDec head, AstDecList tail)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (tail != null) System.out.print("====================== decs -> dec decs\n");
        if (tail == null) System.out.print("====================== decs -> dec\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.head = head;
        this.tail = tail;
    }

    public void printMe()
    {
        /******************************************/
        /* AST NODE TYPE = AST DECLARATION LIST   */
        /******************************************/
        System.out.print("AST NODE DEC LIST\n");
        
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "DEC\nLIST");
        
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}