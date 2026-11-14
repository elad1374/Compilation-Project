package ast;

public class AstStmtIf extends AstStmt
{
    public AstExp cond;
    public AstStmtList body;
    public AstStmtList elseBody; 

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    
    public AstStmtIf(AstExp cond, AstStmtList body)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== stmt -> IF LPAREN exp RPAREN LBRACE stmtList RBRACE\n");
        this.cond = cond;
        this.body = body;
        this.elseBody = null;
    }

    public AstStmtIf(AstExp cond, AstStmtList body, AstStmtList elseBody)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== stmt -> IF LPAREN exp RPAREN LBRACE stmtList RBRACE ELSE LBRACE stmtList RBRACE\n");
        this.cond = cond;
        this.body = body;
        this.elseBody = elseBody;
    }

    public void printMe()
    {
        System.out.print("AST NODE STMT IF\n");

        if (cond != null) cond.printMe();
        if (body != null) body.printMe();
        if (elseBody != null) elseBody.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                "STMT\nIF");

        if (cond != null) AstGraphviz.getInstance().logEdge(serialNumber, cond.serialNumber);
        if (body != null) AstGraphviz.getInstance().logEdge(serialNumber, body.serialNumber);
        if (elseBody != null) AstGraphviz.getInstance().logEdge(serialNumber, elseBody.serialNumber);
    }
}