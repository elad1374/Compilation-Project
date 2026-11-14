package ast;

public class AstStmtWhile extends AstStmt
{
    public AstExp cond;
    public AstStmtList body;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstStmtWhile(AstExp cond, AstStmtList body)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== stmt -> WHILE LPAREN exp RPAREN LBRACE stmtList RBRACE\n");
        this.cond = cond;
        this.body = body;
    }

    public void printMe()
    {
        System.out.print("AST NODE STMT WHILE\n");

        if (cond != null) cond.printMe();
        if (body != null) body.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                "STMT\nWHILE");

        if (cond != null) AstGraphviz.getInstance().logEdge(serialNumber, cond.serialNumber);
        if (body != null) AstGraphviz.getInstance().logEdge(serialNumber, body.serialNumber);
    }
}