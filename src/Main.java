import java.io.*;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;
   
public class Main
{
	
	static public void main(String argv[])
	{
		Lexer l;
		Symbol s;
		FileReader fileReader;
		PrintWriter fileWriter;
		String inputFileName = argv[0];
		String outputFileName = argv[1];
		String tokenName;
		
		try
		{
			/********************************/
			/* [1] Initialize a file reader */
			/********************************/
			fileReader = new FileReader(inputFileName);

			/********************************/
			/* [2] Initialize a file writer */
			/********************************/
			fileWriter = new PrintWriter(outputFileName);
			
			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(fileReader);

			/***********************/
			/* [4] Read next token */
			/***********************/
			s = l.next_token();

			/********************************/
			/* [5] Main reading tokens loop */
			/********************************/
			while (s.sym != TokenNames.EOF)
			{
				/********************************/
				/* If a lexical error is encountered, wipe the file contents
				and make it only contain the word "ERROR" */
				/********************************/
				if(s.sym == TokenNames.LEXICAL_ERROR)
				{
					fileWriter.close();
					fileWriter = new PrintWriter(outputFileName);
					fileWriter.print("ERROR");
					break;
				}
				
				/*********************/
				/* [7] Print to file. Format: */
				/* TOKEN[line,column] for tokens without values */
				/* TOKEN(value)[line,column] for tokens with values */
				/*********************/
				tokenName = getTokenNameFromID(s.sym);
				fileWriter.print(tokenName);
				if (s.value != null)
				{
					fileWriter.print("(");
					fileWriter.print(s.value);
					fileWriter.print(")");
				}
				fileWriter.print("[");
				fileWriter.print(l.getLine());
				fileWriter.print(",");
				fileWriter.print(l.getTokenStartPosition());
				fileWriter.print("]");
				
				/***********************/
				/* [8] Read next token */
				/***********************/
				s = l.next_token();
				if(s.sym!= TokenNames.EOF)
				{
					fileWriter.print("\n");
				}
			}
			
			/******************************/
			/* [9] Close lexer input file */
			/******************************/
			l.yyclose();

			/**************************/
			/* [10] Close output file */
			/**************************/
			fileWriter.close();
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	* Gets the name of a token from its identifying number, as
	* defined in TokenNames.java
	*/
	static private String getTokenNameFromID(int id)
	{
		switch (id)
		{
			case TokenNames.EOF: return "EOF";
			case TokenNames.PLUS: return "PLUS";
			case TokenNames.MINUS: return "MINUS";
			case TokenNames.TIMES: return "TIMES";
			case TokenNames.DIVIDE: return "DIVIDE";
			case TokenNames.EQ: return "EQ";
			case TokenNames.LT: return "LT";
			case TokenNames.GT: return "GT";
			case TokenNames.AND: return "AND";
			case TokenNames.OR: return "OR";
			case TokenNames.LPAREN: return "LPAREN";
			case TokenNames.RPAREN: return "RPAREN";
			case TokenNames.LBRACK: return "LBRACK";
			case TokenNames.RBRACK: return "RBRACK";
			case TokenNames.LBRACE: return "LBRACE";
			case TokenNames.RBRACE: return "RBRACE";
			case TokenNames.COMMA: return "COMMA";
			case TokenNames.DOT: return "DOT";
			case TokenNames.SEMICOLON: return "SEMICOLON";
			case TokenNames.ASSIGN: return "ASSIGN";
			case TokenNames.ARRAY: return "ARRAY";
			case TokenNames.CLASS: return "CLASS";
			case TokenNames.EXTENDS: return "EXTENDS";
			case TokenNames.NEW: return "NEW";
			case TokenNames.NIL: return "NIL";
			case TokenNames.IF: return "IF";
			case TokenNames.ELSE: return "ELSE";
			case TokenNames.WHILE: return "WHILE";
			case TokenNames.RETURN: return "RETURN";
			case TokenNames.TYPE_INT: return "TYPE_INT";
			case TokenNames.TYPE_STRING: return "TYPE_STRING";
			case TokenNames.TYPE_VOID: return "TYPE_VOID";
			case TokenNames.ID: return "ID";
			case TokenNames.INT: return "INT";
			case TokenNames.STRING: return "STRING";
			case TokenNames.LEXICAL_ERROR: return "LEXICAL_ERROR";
			
			default:
				return null;
		}
	}
}


