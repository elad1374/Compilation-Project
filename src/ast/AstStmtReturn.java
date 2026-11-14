package ast;

public class AstStmtReturn extends AstStmt
{
    public AstExp exp; // *can be set to null

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstStmtReturn(AstExp exp)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (exp != null) {
            System.out.print("====================== stmt -> RETURN exp SEMICOLON\n");
        } else {
            System.out.print("====================== stmt -> RETURN SEMICOLON\n");
        }

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.exp = exp;
    }

    public void printMe()
    {
        System.out.print("AST NODE STMT RETURN\n");

        if (exp != null) exp.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
            "STMT\nRETURN\n");
        
        if (exp != null) AstGraphviz.getInstance().logEdge(serialNumber, exp.serialNumber);
    }
}