package ast;

public class AstTypeInt extends AstType
{
    public AstTypeInt()
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== type -> TYPE_INT\n");
    }

    public void printMe()
    {
        System.out.print("AST NODE TYPE INT\n");
        AstGraphviz.getInstance().logNode(serialNumber, "TYPE_INT");
    }
}