package ast;

public class AstExpInt extends AstExp
{
	public int value;
	public boolean MINUS;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstExpInt(int value)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.value = Math.abs(value);

		if (value < 0)
			MINUS = true;
		else
			MINUS = false;

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if(MINUS)
			System.out.format("====================== exp -> MINUS INT( %d )\n", this.value);
		else
			System.out.format("====================== exp -> INT( %d )\n", this.value);
	}

	/************************************************/
	/* The printing message for an int exp AST node */
	/************************************************/
	public void printMe()
	{
		/*******************************/
		/* AST NODE TYPE = AST INT EXP */
		/*******************************/
		if(MINUS)
			System.out.format("AST NODE EXP MINUS INT( %d )\n",value);
		else
			System.out.format("AST NODE EXP INT( %d )\n",value);

		/*********************************/
		/* Print to AST GRAPHVIZ DOT file */
		/*********************************/
		if(MINUS)
			AstGraphviz.getInstance().logNode(
					serialNumber,
				String.format("MINUS INT(%d)",value));
		else
			AstGraphviz.getInstance().logNode(
					serialNumber,
				String.format("INT(%d)",value));
	}
}
