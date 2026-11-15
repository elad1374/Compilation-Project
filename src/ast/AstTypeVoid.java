package ast;

public class AstTypeVoid extends AstType
{
    public AstTypeVoid(int lineNumber)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;
        
        System.out.print("====================== type -> TYPE_VOID\n");
    }

    public void printMe()
    {
        System.out.print("AST NODE TYPE VOID\n");
        AstGraphviz.getInstance().logNode(serialNumber, "TYPE_VOID");
    }
}