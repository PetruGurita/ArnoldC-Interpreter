
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class printVisitor implements Visitor {

//   same as the interpreter, prints every node with it's children
	
public printVisitor(String fileName) throws FileNotFoundException {
		String where = fileName.substring(fileName.lastIndexOf('/') + 1, fileName.length() -2);
		where += "ast";
		File file = new File("output/" + where);
		PrintStream o = new PrintStream(file);
		System.setOut(o);
	}
	String spaces(TreeNode t) {
		int number = t.ancestor(t);
		String space = "";
		int i = 0;
		while (i < number) {
			i++;
			space += "\t";

		}
		return space;
	}

	@Override
	public void visit(VariableNode varNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(varNode) + "VariableNode <" + varNode.variable + ">");
		
	}

	@Override
	public void visit(IfNode ifNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(ifNode) + "IfNode");
		
	}

	@Override
	public void visit(GreaterThanNode greaterNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(greaterNode) + "GreaterThanNode");
		

	}

	@Override
	public void visit(EqualToNode equalNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(equalNode) + "EqualToNode");
		
	}

	@Override
	public void visit(IfBodyNode ifbodyNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(ifbodyNode) + "IfBodyNode");
		
	}

	@Override
	public void visit(ElseBodyNode elseBodyNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(elseBodyNode) + "ElseBodyNode");
		
	}

	@Override
	public void visit(LvalNode lvalNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(lvalNode) + "LvalNode <" + lvalNode.data + ">");
		
	}

	@Override
	public void visit(MainNode mainNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(mainNode) + "MainNode");
		
	}

	@Override
	public void visit(DifferenceNode minusNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(minusNode) + "DifferenceNode");
		
	}

	@Override
	public void visit(DivisionOperatorNode divisionNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(divisionNode) + "DivisionNode");
		
	}

	@Override
	public void visit(ModuloOperatorNode moduloNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(moduloNode) + "ModuloNode");
		
	}

	@Override
	public void visit(MultiplicationOperatorNode multiplicationNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(multiplicationNode) + "MultiplicationNode");
		
	}

	@Override
	public void visit(OrNode orNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(orNode) + "OrNode");
		
	}

	@Override
	public void visit(SumOperatorNode plusNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(plusNode) + "SumNode");
		
	}

	@Override
	public void visit(PrintNode printNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(printNode) + "PrintNode");
		
	}

	@Override
	public void visit(RvalNode rvalNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(rvalNode) + "RvalNode <" + rvalNode.data + ">");
		
	}

	@Override
	public void visit(StringNode stringNode) {
		// TODO Auto-generated method stub
		String text = stringNode.text.substring(1, stringNode.text.length() - 1);
		System.out.println(spaces(stringNode) + "StringNode <" + text + ">");
		
	}

	@Override
	public void visit(DeclareNode declareNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(declareNode) + "DeclareNode");
		
	}

	@Override
	public void visit(ConstantNode constantNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(constantNode) + "ConstantNode <" + constantNode.value + ">");
		
	}

	@Override
	public void visit(ConditionNode conditionNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(conditionNode) + "ConditionNode <" + conditionNode.name + ">");
		
	}

	@Override
	public void visit(AssignmentNode assignNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(assignNode) + "AssignmentNode");
		
	}

	@Override
	public void visit(AndNode andNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(andNode) + "AndNode");
		
	}

	@Override
	public void visit(TreeNode tree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(WhileNode whileNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(whileNode) + "WhileNode");
		
		
	}

	@Override
	public void visit(BodyNode bodyNode) {
		// TODO Auto-generated method stub
		System.out.println(spaces(bodyNode) + "BodyNode");
		
	}

}
