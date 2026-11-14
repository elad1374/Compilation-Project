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
    public AstClassDec(String name, String superClassName, AstCFieldList fields)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

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

/******************************************************************/
/* This represents the linked list of fields (variables/methods)  */
/******************************************************************/

class AstCFieldList extends AstNode
{
    /****************/
    /* DATA MEMBERS */
    /****************/
    public AstCField head;
    public AstCFieldList tail;

    /*******************/
    /*  CONSTRUCTOR(S) */
    /*******************/
    public AstCFieldList(AstCField head, AstCFieldList tail)
    {
        /******************************/
        /* SET A UNIQUE SERIAL NUMBER */
        /******************************/
        serialNumber = AstNodeSerialNumber.getFresh();

        /***************************************/
        /* PRINT CORRESPONDING DERIVATION RULE */
        /***************************************/
        if (tail != null) System.out.print("====================== cFields -> cField cFields\n");
        if (tail == null) System.out.print("====================== cFields -> cField\n");

        /*******************************/
        /* COPY INPUT DATA MEMBERS ... */
        /*******************************/
        this.head = head;
        this.tail = tail;
    }

    public void printMe()
    {
        /******************************************/
        /* AST NODE TYPE = AST CLASS FIELD LIST   */
        /******************************************/
        System.out.print("AST NODE CFIELD LIST\n");
        
        if (head != null) head.printMe();
        if (tail != null) tail.printMe();

        AstGraphviz.getInstance().logNode(serialNumber, "CFIELD\nLIST");
        
        if (head != null) AstGraphviz.getInstance().logEdge(serialNumber, head.serialNumber);
        if (tail != null) AstGraphviz.getInstance().logEdge(serialNumber, tail.serialNumber);
    }
}