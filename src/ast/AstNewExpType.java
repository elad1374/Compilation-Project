package ast;

public class AstNewExpType extends AstNewExp
{
    public AstType type;

    public AstNewExpType(AstType type)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
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