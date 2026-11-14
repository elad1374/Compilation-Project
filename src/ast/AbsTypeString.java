package ast;

public class AstTypeString extends AstType
{
    public AstTypeString()
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== type -> TYPE_STRING\n");
    }

    public void printMe()
    {
        System.out.print("AST NODE TYPE STRING\n");
        AstGraphviz.getInstance().logNode(serialNumber, "TYPE_STRING");
    }
}