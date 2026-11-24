package ast;

public class AstStmtCallExp extends AstStmt 
{
    public AstCallExp callExp; 

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstStmtCallExp(int lineNumber, AstCallExp callExp)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;
        
        System.out.print("====================== stmt -> callExp SEMICOLON\n");
        this.callExp = callExp;
    }

    public void printMe()
    {
        System.out.print("AST NODE STMT CALL EXP\n");

        if (callExp != null) callExp.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                "STMT\nCALL EXP");

        if (callExp != null) AstGraphviz.getInstance().logEdge(serialNumber, callExp.serialNumber);
    }
}