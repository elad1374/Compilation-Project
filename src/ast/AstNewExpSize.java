package ast;

public class AstNewExpSize extends AstNewExp
{
    public AstType type;
    public AstExp exp;

    public AstNewExpSize(AstType type, AstExp exp)
    {
        serialNumber = AstNodeSerialNumber.getFresh();
        System.out.print("====================== newExp -> NEW type LBRACK exp RBRACK\n");
        this.type = type;
        this.exp = exp;
    }

    public void printMe()
    {
        System.out.print("AST NODE NEW EXP ARRAY\n");

        if (type != null) type.printMe();
        if (exp != null) exp.printMe();

        AstGraphviz.getInstance().logNode(
                serialNumber,
                "NEW SIZE");

        if (type != null) AstGraphviz.getInstance().logEdge(serialNumber, type.serialNumber);
        if (exp != null) AstGraphviz.getInstance().logEdge(serialNumber, exp.serialNumber);
    }
}