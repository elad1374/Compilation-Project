package ast;

import java.io.File;
import java.io.PrintWriter;

public class AstGraphviz
{
	/***********************/
	/* The file writer ... */
	/***********************/
	private PrintWriter fileWriter;
	
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static AstGraphviz instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	private AstGraphviz() {}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static AstGraphviz getInstance()
	{
		if (instance == null)
		{
			instance = new AstGraphviz();
			
			/********************************************************/
			/* Initialize a file writer. The output folder may not  */
			/* exist (it is not in git), so create it first. If the */
			/* file still cannot be opened, fileWriter stays null   */
			/* and all logging below is skipped: the AST image is a */
			/* debug aid and must never crash the parser.           */
			/********************************************************/
			try
			{
				String dirname="./output/";
				String filename="AST_IN_GRAPHVIZ_DOT_FORMAT.txt";
				new File(dirname).mkdirs();
				instance.fileWriter = new PrintWriter(dirname+filename);

				/******************************************************/
				/* Print Directed Graph header in Graphviz dot format */
				/******************************************************/
				instance.fileWriter.print("digraph\n");
				instance.fileWriter.print("{\n");
				instance.fileWriter.print("graph [ordering = \"out\"]\n");
			}
			catch (Exception e)
			{
				instance.fileWriter = null;
				e.printStackTrace();
			}
		}
		return instance;
	}

	/***********************************/
	/* Log node in graphviz dot format */
	/***********************************/
	public void logNode(int nodeSerialNumber,String nodeName)
	{
		if (fileWriter == null) return;
		fileWriter.format(
			"v%d [label = \"%s\"];\n",
			nodeSerialNumber,
			nodeName);
	}

	/***********************************/
	/* Log edge in graphviz dot format */
	/***********************************/
	public void logEdge(
		int fatherNodeSerialNumber,
		int sonNodeSerialNumber)
	{
		if (fileWriter == null) return;
		fileWriter.format(
			"v%d -> v%d;\n",
			fatherNodeSerialNumber,
			sonNodeSerialNumber);
	}
	
	/******************************/
	/* Finalize graphviz dot file */
	/******************************/
	public void finalizeFile()
	{
		if (fileWriter == null) return;
		fileWriter.print("}\n");
		fileWriter.close();
	}
}
