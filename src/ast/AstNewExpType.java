package ast;

public class AstNewExpType extends AstNewExp
{
    public AstType type;

    public AstNewExpType(int lineNumber, AstType type)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;
        
        System.out.print("====================== newExp -> NEW type\n");
        this.type = type;
    }

    public void printMe()
    {
        System.out.print("AST NODE NEW EXP TYPE\n");

        if (type != null) type.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                "NEW TYPE");

        if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
    }
}