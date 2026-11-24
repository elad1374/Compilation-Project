package ast;

/********************************************************************************/
/* Please note!!!                                                               */
/* EBNF Rule:                                                                   */
/* classDec ::= CLASS ID [ EXTENDS ID ] LBRACE cField { cField } RBRACE         */
/********************************************************************************/

public class AstClassDec extends AstDec
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public String name;
    public String superClassName; // Optional: will be null if no EXTENDS clause
    public AstCFieldList fields;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstClassDec(int lineNumber, String name, String superClassName, AstCFieldList fields)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        this.lineNumber = lineNumber;

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (superClassName != null) {
            System.out.format("====================== classDec -> CLASS ID( %s ) EXTENDS ID( %s ) LBRACE cFields RBRACE\n", name, superClassName);
        } else {
            System.out.format("====================== classDec -> CLASS ID( %s ) LBRACE cFields RBRACE\n", name);
        }

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.name = name;
        this.superClassName = superClassName;
        this.fields = fields;
    }

    /*********************************************************/
    /* The printing message for a class declaration AST node */
    /*********************************************************/
    public void printMe()
    {
        /*****************************************/
        /* AST NODE TYPE = AST CLASS DECLARATION */
        /*****************************************/
        System.out.format("AST NODE CLASS DEC( %s )\n", name);

        /**************************************************/
        /* RECURSIVELY PRINT THE LIST OF CLASS FIELDS...  */
        /**************************************************/
        if (fields != null) fields.printMe();

        /***************************************/
        /* PRINT Node to AST GRAPHVIZ DOT file */
        /***************************************/
        if (superClassName != null) {
            AstGraphviz.getInstance().logNode(
                    serialNumber,
                    String.format("CLASS\nDEC\n(%s)\nEXTENDS\n(%s)", name, superClassName));
        } else {
            AstGraphviz.getInstance().logNode(
                    serialNumber,
                    String.format("CLASS\nDEC\n(%s)", name));
        }

        /****************************************/
        /* PRINT Edges to AST GRAPHVIZ DOT file */
        /****************************************/
        if (fields != null) AstGraphviz.getInstance().logEdge(serialNumber, fields.serialNumber);
    }
}
