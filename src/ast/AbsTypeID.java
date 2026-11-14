package ast;

public class AstTypeID extends AstType 
{
    public String id;

    public AstTypeID(String id)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        
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