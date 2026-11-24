package ast;

/********************************************/
/* List of class members: cField { cField } */
/********************************************/

public class AstCFieldList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstCField head;
    public AstCFieldList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstCFieldList(int lineNumber, AstCField head, AstCFieldList tail)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (tail != null) System.out.print("====================== cFields -> cField cFields\n");
        if (tail == null) System.out.print("====================== cFields -> cField\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.head = head;
        this.tail = tail;
    }

    public void printMe()
    {
        /******************************************/
        /* AST NODE TYPE = AST CLASS FIELD LIST   */
        /******************************************/
        System.out.print("AST NODE CFIELD LIST\n");
        
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "CFIELD\nLIST");
        
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}
