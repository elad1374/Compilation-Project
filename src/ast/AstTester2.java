package ast;
// the input is copy of input/Input.txt 
// "
// GIDI := 60;
// AVROOM := 55 + GIDI;
// JACOB.sons[11] := JOSEF;
// "
//
// run:
// javac -d bin src/ast/*.java 
// java -cp bin ast.AstTester2
// dot -Tjpeg -o output/test_ast.jpeg output/AST_IN_GRAPHVIZ_DOT_FORMAT.txt
//
// check output/test_ast.jpeg

public class AstTester2 {
    public static void main(String[] args) {
        System.out.println("Starting AST test for Starter Code example...");

        // ==========================================
        // 1. GIDI := 60;
        // ==========================================
        AstVarSimple gidiVar1 = new AstVarSimple(1, "GIDI");
        AstExpInt val60 = new AstExpInt(1, 60);
        AstStmtAssign assign1 = new AstStmtAssign(1, gidiVar1, val60);

        // ==========================================
        // 2. AVROOM := 55 + GIDI;
        // ==========================================
        AstVarSimple avroomVar = new AstVarSimple(2, "AVROOM");
        AstExpInt val55 = new AstExpInt(2, 55);
        AstVarSimple gidiVar2 = new AstVarSimple(2, "GIDI");
        AstExpVar expGidi = new AstExpVar(2, gidiVar2);
        
        // 0 represents the '+' operator in your AstExpBinop logic
        AstExpBinop binop = new AstExpBinop(2, val55, expGidi, 0); 
        AstStmtAssign assign2 = new AstStmtAssign(2, avroomVar, binop);

        // ==========================================
        // 3. JACOB.sons[11] := JOSEF;
        // ==========================================
        // Build the left side: JACOB.sons[11]
        AstVarSimple jacobVar = new AstVarSimple(3, "JACOB");
        AstVarField fieldSons = new AstVarField(3, jacobVar, "sons");
        AstExpInt val11 = new AstExpInt(3, 11);
        AstVarSubscript subscriptSons = new AstVarSubscript(3, fieldSons, val11);
        
        // Build the right side: JOSEF
        AstVarSimple josefVar = new AstVarSimple(3, "JOSEF");
        AstExpVar expJosef = new AstExpVar(3, josefVar);
        
        // Connect them in an assignment
        AstStmtAssign assign3 = new AstStmtAssign(3, subscriptSons, expJosef);

        // ==========================================
        // 4. Build the linked list of statements
        // ==========================================
        AstStmtList list3 = new AstStmtList(3, assign3, null);
        AstStmtList list2 = new AstStmtList(2, assign2, list3);
        AstStmtList rootList = new AstStmtList(1, assign1, list2);

        System.out.println("\n--- Printing AST ---");
        
        // Print the AST from the root list
        rootList.printMe();

        // Finalize the Graphviz DOT file
        AstGraphviz.getInstance().finalizeFile();

        System.out.println("\nTest complete! Check the 'output' directory.");
    }
}