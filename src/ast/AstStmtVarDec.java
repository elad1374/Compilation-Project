package ast;

public class AstStmtVarDec extends AstStmt
{
    public AstVarDec varDec;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstStmtVarDec(AstVarDec varDec)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.print("====================== stmt -> varDec\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.varDec = varDec;
    }

    public void printMe()
    {
        System.out.print("AST NODE STMT VAR DEC\n");

        if (varDec != null) varDec.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
            "STMT\nVAR DEC\n");
        
        if (varDec != null) AstGraphviz.getInstance().logEdge(serialNumber, varDec.serialNumber);
    }
}