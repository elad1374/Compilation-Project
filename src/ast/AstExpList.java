package ast;

/*********************************************/
/* List of call arguments: exp { COMMA exp } */
/*********************************************/

public class AstExpList extends AstNode
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
