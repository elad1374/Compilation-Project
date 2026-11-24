package ast;

/***********************************/
/* One function parameter: type ID */
/***********************************/

public class AstFormal extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstType type;
    public String name;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstFormal(int lineNumber, AstType type, String name)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        System.out.format("====================== formal -> type ID( %s )\n", name);

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.type = type;
        this.name = name;
    }

    public void printMe()
    {
        /************************************/
        /* AST NODE TYPE = AST FORMAL PARAM */
        /************************************/
        System.out.format("AST NODE FORMAL( %s )\n", name);
        if (type != null) type.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, String.format("FORMAL\n(%s)", name));
        if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
    }
}
