package ast;

/**********************************************************/
/* List of function parameters: type ID { COMMA type ID } */
/**********************************************************/

public class AstFormalList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstFormal head;
    public AstFormalList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstFormalList(int lineNumber, AstFormal head, AstFormalList tail)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (tail != null) System.out.print("====================== formals -> formal COMMA formals\n");
        if (tail == null) System.out.print("====================== formals -> formal\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.head = head;
        this.tail = tail;
    }

    public void printMe()
    {
        /*****************************************/
        /* AST NODE TYPE = AST FORMAL PARAM LIST */
        /*****************************************/
        System.out.print("AST NODE FORMAL LIST\n");
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "FORMAL\nLIST");
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}
