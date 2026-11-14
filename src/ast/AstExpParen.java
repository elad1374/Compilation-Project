package ast;

public class AstExpParen extends AstExp
{
	public AstExp exp;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstExpParen(AstExp exp)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.format("====================== exp -> LPAREN exp RPAREN\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.exp = exp;
	}

	/************************************************/
	/* The printing message for an int exp AST node */
	/************************************************/
	public void printMe()
	{
		/*******************************/
		/* AST NODE TYPE = AST INT EXP */
		/*******************************/
		System.out.print("AST NODE EXP PAREN\n");

		/*****************************/
		/* RECURSIVELY PRINT exp ... */
		/*****************************/
		if (var != null) exp.printMe();

        /**********************************/
        /* Print to AST GRAPHVIZ DOT file */
        /**********************************/
		AstGraphviz.getInstance().logNode(
				serialNumber,
			String.format("PAREN"));

        /****************************************/
        /* PRINT Edge to AST GRAPHVIZ DOT file  */
        /****************************************/
        if (exp != null) AstGraphviz.getInstance().logEdge(serialNumber, exp.serialNumber);
    
	}
}
