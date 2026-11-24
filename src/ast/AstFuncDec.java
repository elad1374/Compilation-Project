package ast;

/******************************************************************************************/
/* Please note!!!                                                                         */
/* EBNF Rule:                                                                             */
/* funcDec ::= type ID LPAREN [ type ID { COMMA type ID } ] RPAREN LBRACE stmtList RBRACE */
/******************************************************************************************/

public class AstFuncDec extends AstDec
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstType returnType;
    public String name;
    public AstFormalList formals; // The parameter list (can be null if empty)
    public AstStmtList body;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstFuncDec(int lineNumber, AstType returnType, String name, AstFormalList formals, AstStmtList body)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (formals != null) {
            System.out.format("====================== funcDec -> type ID( %s ) LPAREN formals RPAREN LBRACE stmtList RBRACE\n", name);
        } else {
            System.out.format("====================== funcDec -> type ID( %s ) LPAREN RPAREN LBRACE stmtList RBRACE\n", name);
        }

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.returnType = returnType;
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

    /*********************************************************/
    /* The printing message for a function declaration node  */
    /*********************************************************/
    public void printMe()
    {
        /********************************************/
        /* AST NODE TYPE = AST FUNCTION DECLARATION */
        /********************************************/
        System.out.format("AST NODE FUNC DEC( %s )\n", name);

        /*******************************************************/
        /* RECURSIVELY PRINT RETURN TYPE, FORMALS, AND BODY... */
        /*******************************************************/
        if (returnType != null) returnType.printMe();
        if (formals != null) formals.printMe();
        if (body != null) body.printMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("FUNC\nDEC\n(%s)", name));

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (returnType != null) AstGraphviz.getInstance().logEdge(serialNumber, returnType.serialNumber);
        if (formals != null) AstGraphviz.getInstance().logEdge(serialNumber, formals.serialNumber);
        if (body != null) AstGraphviz.getInstance().logEdge(serialNumber, body.serialNumber);
    }
}
