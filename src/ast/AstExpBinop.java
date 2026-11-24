package ast;

public class AstExpBinop extends AstExp
{
	/*****************************************************/
	/* Operator codes: pass one of these as the op value */
	/*****************************************************/
	public static final int PLUS   = 0;
	public static final int MINUS  = 1;
	public static final int TIMES  = 2;
	public static final int DIVIDE = 3;
	public static final int LT     = 4;
	public static final int GT     = 5;
	public static final int EQ     = 6;
	public static final int AND    = 7;
	public static final int OR     = 8;

	public int op;
	public AstExp left;
	public AstExp right;
	
	/******************/
	/* CONSTRUCTOR(S) */
	/******************/
	public AstExpBinop(int lineNumber, AstExp left, AstExp right, int op)
	{
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		serialNumber = AstNodeSerialNumber.getFresh();

		this.lineNumber = lineNumber;

		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		System.out.print("====================== exp -> exp BINOP exp\n");

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	/*************************************************/
	/* The printing message for a binop exp AST node */
	/*************************************************/
	public void printMe()
	{
		String sop="";
		
		/*********************************/
		/* CONVERT op to a printable sop */
		/*********************************/
		if (op == PLUS)   {sop = "+";}
		if (op == MINUS)  {sop = "-";}
		if (op == TIMES)  {sop = "*";}
		if (op == DIVIDE) {sop = "/";}
		if (op == LT)     {sop = "<";}
		if (op == GT)     {sop = ">";}
		if (op == EQ)     {sop = "=";}
		if (op == AND)    {sop = "and";}
		if (op == OR)     {sop = "or";}
		
		/*************************************/
		/* AST NODE TYPE = AST BINOP EXP */
		/*************************************/
		System.out.print("AST NODE BINOP EXP\n");

		/**************************************/
		/* RECURSIVELY PRINT left + right ... */
		/**************************************/
		if (left != null) left.printMe();
		if (right != null) right.printMe();
		
		/***************************************/
		/* PRINT Node to AST GRAPHVIZ DOT file */
		/***************************************/
		AstGraphviz.getInstance().logNode(
				serialNumber,
			String.format("BINOP(%s)",sop));
		
		/****************************************/
		/* PRINT Edges to AST GRAPHVIZ DOT file */
		/****************************************/
		if (left  != null) AstGraphviz.getInstance().logEdge(serialNumber,left.serialNumber);
		if (right != null) AstGraphviz.getInstance().logEdge(serialNumber,right.serialNumber);
	}
}
