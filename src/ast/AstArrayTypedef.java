package ast;

public class AstArrayTypedef extends AstDec
{
    public String name;
    public AstType type;

    public AstArrayTypedef(String name, AstType type)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        
        System.out.format("====================== arrayTypedef -> ARRAY ID( %s ) EQ type LBRACK RBRACK SEMICOLON\n", name);
        
        this.name = name;
        this.type = type;
    }

    public void printMe()
    {
        System.out.format("AST NODE ARRAY TYPEDEF( %s )\n", name);

        if (type != null) type.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                String.format("ARRAY TYPEDEF (%s)", name));

        if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
    }
}