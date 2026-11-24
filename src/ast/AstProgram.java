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
    public AstProgram(int lineNumber, AstDecList decs)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

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
