/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/*************/
/* USER CODE */
/*************/

import java_cup.runtime.*;

/******************************/
/* DOLLAR DOLLAR - DON'T TOUCH! */
/******************************/

%%

/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/
   
/*****************************************************/ 
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/ 
%class Lexer

/********************************************************************/
/* The current line number can be accessed with the variable yyline */
/* and the current column number with the variable yycolumn.        */
/********************************************************************/
%line
%column

/*******************************************************************************/
/* Note that this has to be the EXACT same name of the class the CUP generates */
/*******************************************************************************/
%cupsym TokenNames

/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup

%state YYCOMMENT_1
%state YYCOMMENT_2

/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/   
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied verbatim (letter to letter) into the Lexer class code.     */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                                                          */  
/*****************************************************************************/   
%{
	/*********************************************************************************/
	/* Create a new java_cup.runtime.Symbol with information about the current token */
	/*********************************************************************************/
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	/*******************************************/
	/* Enable line number extraction from main */
	/*******************************************/
	public int getLine() { return yyline + 1; } 

	/**********************************************/
	/* Enable token position extraction from main */
	/**********************************************/
	public int getTokenStartPosition() { return yycolumn + 1; } 
%}

/***********************/
/* MACRO DECLARATIONS */
/***********************/
LineTerminator	  = \r|\n|\r\n
WhiteSpace		  = {LineTerminator} | [ \t]
INTEGER			  = 0 | [1-9][0-9]*
INVALID_0_INTEGER = 0[0-9]+
ID				  = [a-zA-Z][0-9a-zA-Z]*
STRING			  = \"[a-zA-Z]*\"
INVALID_STRING    = \"[^\r\n\"]*\"
UNCLOSED_STRING   = \"[^\r\n\"]*
COMMENT_CHAR      = [a-zA-Z0-9 \t\(\)\[\]\{\}\?\!\+\-\*\/\.\;]

/******************************/
/* DOLLAR DOLLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

<YYINITIAL> {

"+"					{ return symbol(TokenNames.PLUS);}
"-"					{ return symbol(TokenNames.MINUS);}
"*"					{ return symbol(TokenNames.TIMES);}
"/"					{ return symbol(TokenNames.DIVIDE);}
"="					{ return symbol(TokenNames.EQ);}
"<"					{ return symbol(TokenNames.LT);}
">"					{ return symbol(TokenNames.GT);}
"and"				{ return symbol(TokenNames.AND);}
"or"				{ return symbol(TokenNames.OR);}

"("					{ return symbol(TokenNames.LPAREN);}
")"					{ return symbol(TokenNames.RPAREN);}
"["					{ return symbol(TokenNames.LBRACK);}
"]"					{ return symbol(TokenNames.RBRACK);}
"{"					{ return symbol(TokenNames.LBRACE);}
"}"					{ return symbol(TokenNames.RBRACE);}
","					{ return symbol(TokenNames.COMMA);}
"."					{ return symbol(TokenNames.DOT);}
";"					{ return symbol(TokenNames.SEMICOLON);}
":="				{ return symbol(TokenNames.ASSIGN);}

"array" 			{ return symbol(TokenNames.ARRAY);}
"class" 			{ return symbol(TokenNames.CLASS);}
"extends" 			{ return symbol(TokenNames.EXTENDS);}
"new" 				{ return symbol(TokenNames.NEW);}
"nil" 				{ return symbol(TokenNames.NIL);}
"if" 				{ return symbol(TokenNames.IF);}
"else" 				{ return symbol(TokenNames.ELSE);}
"while" 			{ return symbol(TokenNames.WHILE);}
"return" 			{ return symbol(TokenNames.RETURN);}
"int" 				{ return symbol(TokenNames.TYPE_INT);}
"string" 			{ return symbol(TokenNames.TYPE_STRING);}
"void" 				{ return symbol(TokenNames.TYPE_VOID);}

{STRING} 			{ 
	String text = yytext();
	text = text.substring(1, text.length() - 1);
	return symbol(TokenNames.STRING, text);
}
{INVALID_STRING} 	{ return symbol(TokenNames.LEXICAL_ERROR);}
{UNCLOSED_STRING} 	{ return symbol(TokenNames.LEXICAL_ERROR);}
{INTEGER} 			{ 
	long value = Long.parseLong(yytext());
	if(value < 32768){
		return symbol(TokenNames.INT, (int)value);
	} else {
		return symbol(TokenNames.LEXICAL_ERROR);
	}
}
{INVALID_0_INTEGER} { return symbol(TokenNames.LEXICAL_ERROR);}

"//" { yybegin(YYCOMMENT_1); }
"/*" { yybegin(YYCOMMENT_2); }

{ID} 				{ return symbol(TokenNames.ID, yytext());}
{WhiteSpace}		{ /* just skip what was found, do nothing */ }
<<EOF>>				{ return symbol(TokenNames.EOF);}

[^] 				{ return symbol(TokenNames.LEXICAL_ERROR);}
}


<YYCOMMENT_1> {
    {LineTerminator}    { yybegin(YYINITIAL);}
    <<EOF>>             { yybegin(YYINITIAL); return symbol(TokenNames.EOF);} 
    {COMMENT_CHAR}      { /*ignore*/}
    [^]                 { return symbol(TokenNames.LEXICAL_ERROR); }
}

<YYCOMMENT_2> {
    "*/"                { yybegin(YYINITIAL); }
    <<EOF>>             { return symbol(TokenNames.LEXICAL_ERROR); } 
    {COMMENT_CHAR}      { /*ignore*/}
	{LineTerminator}    { /*ignore*/}
    [^]                 { return symbol(TokenNames.LEXICAL_ERROR); } 
}
