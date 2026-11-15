package ast;
// input:
// "int add(int a, int b) { return a + b; }"
//
// run:
// javac -d bin src/ast/*.java //only once
// java -cp bin ast.AstTester
// dot -Tjpeg -o output/test_ast.jpeg output/AST_IN_GRAPHVIZ_DOT_FORMAT.txt
//
// check output/test_ast.jpeg

public class AstTester {
    public static void main(String[] args) {
        System.out.println("Starting independent AST test with formals...");

        // 1. Create return type: int
        AstTypeInt returnType = new AstTypeInt(1);

        // 2. Create parameters (formals): int a, int b
        AstTypeInt typeA = new AstTypeInt(1);
        AstFormal formalA = new AstFormal(typeA, "a");

        AstTypeInt typeB = new AstTypeInt(1);
        AstFormal formalB = new AstFormal(typeB, "b");

        // Build the linked list of formals: (int a, int b)
        AstFormalList formalsTail = new AstFormalList(formalB, null);
        AstFormalList formals = new AstFormalList(formalA, formalsTail);

        // 3. Create function body: return a + b;
        
        // Create variable 'a'
        AstVarSimple varA = new AstVarSimple(2, "a");
        AstExpVar expVarA = new AstExpVar(2, varA);

        // Create variable 'b'
        AstVarSimple varB = new AstVarSimple(2, "b");
        AstExpVar expVarB = new AstExpVar(2, varB);

        // Create binary operation: a + b (op 0 is '+')
        AstExpBinop addExp = new AstExpBinop(2, expVarA, expVarB, 0);

        // Create return statement
        AstStmtReturn returnStmt = new AstStmtReturn(2, addExp);

        // Create statement list for the function body
        AstStmtList body = new AstStmtList(2, returnStmt, null);

        // 4. Create function declaration: int add(int a, int b) { return a + b; }
        AstFuncDec addFunc = new AstFuncDec(1, returnType, "add", formals, body);

        // 5. Create Program root
        AstDecList decList = new AstDecList(1, addFunc, null);
        AstProgram program = new AstProgram(1, decList);

        System.out.println("\n--- Printing AST ---");
        
        // Print the AST (triggers all printMe() and Graphviz functions)
        program.printMe();

        // Finalize and close the Graphviz DOT file
        AstGraphviz.getInstance().finalizeFile();

        System.out.println("\nTest complete! Check the 'output' directory.");
    }
}