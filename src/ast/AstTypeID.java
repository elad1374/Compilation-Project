package ast;

public class AstTypeID extends AstType 
{
    public String id;

    public AstTypeID(int lineNumber, String id)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        this.lineNumber = lineNumber;
        
        System.out.format("====================== type -> ID( %s )\n", id);
        
        this.id = id;
    }

    public void printMe()
    {
        System.out.format("AST NODE TYPE ID\n");

        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("TYPE_ID(%s)", id));
    }
}