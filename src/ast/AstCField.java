package ast;

public class AstCField extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstVarDec varDec;   
    public AstFuncDec funcDec; 

    /*********************************************************/
    /* CONSTRUCTOR 1: When the field is a variable (varDec)  */
    /*********************************************************/
    public AstCField(AstVarDec varDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== cField -> varDec\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
        this.funcDec = null;
    }

    /*********************************************************/
    /* CONSTRUCTOR 2: When the field is a method (funcDec)   */
    /*********************************************************/
    public AstCField(AstFuncDec funcDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== cField -> funcDec\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.funcDec = funcDec;
        this.varDec = null;
    }

    /*********************************************************/
    /* The printing message for a class field AST node       */
    /*********************************************************/
    public void printMe()
    {
        /********************************/
        /* AST NODE TYPE = AST CFIELD   */
        /********************************/
        System.out.print("AST NODE CFIELD\n");

        /*****************************************************/
        /* RECURSIVELY PRINT WHICHEVER MEMBER IS NOT NULL... */
        /*****************************************************/
        if (varDec != null) varDec.printMe();
        if (funcDec != null) funcDec.printMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                "CFIELD");

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (varDec != null) AstGraphviz.getInstance().logEdge(serialNumber, varDec.serialNumber);
        if (funcDec != null) AstGraphviz.getInstance().logEdge(serialNumber, funcDec.serialNumber);
    }
}