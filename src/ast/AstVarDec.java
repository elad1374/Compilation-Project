package ast;

public class AstVarDec extends AstDec 
{
    public AstType type;
    public String name;
    public AstExp exp;         
    public AstNewExp newExp;   

    /*********************************************************/
    /* varDec ::= type ID SEMICOLON                          */
    /*********************************************************/
    public AstVarDec(int lineNumber, AstType type, String name)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;

        System.out.format("====================== varDec -> type ID( %s ) SEMICOLON\n", name);
        this.type = type;
        this.name = name;
    }

    /*********************************************************/
    /* varDec ::= type ID ASSIGN exp SEMICOLON               */
    /*********************************************************/
    public AstVarDec(int lineNumber, AstType type, String name, AstExp exp)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;

        System.out.format("====================== varDec -> type ID( %s ) ASSIGN exp SEMICOLON\n", name);
        this.type = type;
        this.name = name;
        this.exp = exp;
    }

    /*********************************************************/
    /* varDec ::= type ID ASSIGN newExp SEMICOLON            */
    /*********************************************************/
    public AstVarDec(int lineNumber, AstType type, String name, AstNewExp newExp)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;
        
        System.out.format("====================== varDec -> type ID( %s ) ASSIGN newExp SEMICOLON\n", name);
        this.type = type;
        this.name = name;
        this.newExp = newExp;
    }

    public void printMe()
    {
        System.out.format("AST NODE VAR DEC( %s )\n", name);

        if (type != null) type.printMe();
        if (exp != null) exp.printMe();
        if (newExp != null) newExp.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("VAR DEC (%s)", name));

        if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
        if (exp != null) AstGraphviz.getInstance().logEdge(serialNumber, exp.serialNumber);
        if (newExp != null) AstGraphviz.getInstance().logEdge(serialNumber, newExp.serialNumber);
    }
}