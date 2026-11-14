package ast;
import java.util.List;

public class AstCallExp extends AstNode
{
	public AstVar var;        // optional
    public String id;         // func name
    public List<AstExp> exps; // exp list
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstCallExp(AstVar var, String id, List<AstExp> exps)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
        if(var != null)
		    System.out.format("====================== callExp -> var DOT ID( %s ) LPAREN exps RPAREN\n", id);
        else
            System.out.format("====================== callExp -> ID( %s ) LPAREN exps RPAREN\n", id);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.var = var;
        this.id = id;
        this.exps = exps;
	}

	/************************************************/
	/* The printing message for an int exp AST node */
	/************************************************/
	public void printMe()
	{
		/*******************************/
		/* AST NODE TYPE = AST INT EXP */
		/*******************************/
		System.out.format("AST NODE CALL EXP( %s )\n",id);
        
        if(var != null) var.printMe();
        
        if(exps != null){
            for(AstExp exp : exps){
                if(exp != null) exp.printMe();
            }
        }

		/*********************************/
		/* Print to AST GRAPHVIZ DOT file */
		/*********************************/
		AstGraphviz.getInstance().logNode(
					serialNumber,
				String.format("CALL(%s)", id));

        /****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
        if(var != null) AstGraphviz.getInstance().logEdge(serialNumber,var.serialNumber);

        if(exps != null){
            for(AstExp exp : exps){
                if(exp != null) AstGraphviz.getInstance().logEdge(serialNumber,exp.serialNumber);
            }
        }
	}
}
