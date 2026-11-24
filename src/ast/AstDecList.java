package ast;

/***********************************************************/
/* List of top-level declarations: program ::= dec { dec } */
/***********************************************************/

public class AstDecList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstDec head;
    public AstDecList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstDecList(int lineNumber, AstDec head, AstDecList tail)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

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
