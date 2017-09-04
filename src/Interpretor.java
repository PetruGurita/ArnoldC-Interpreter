
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Interpretor implements Visitor {

	//  apply for each node the visitor
	//  in constructor we create the files with a desired path
		public Interpretor(String fileName) throws FileNotFoundException {
		String where = fileName.substring(fileName.lastIndexOf('/') + 1, fileName.length() -2);
		where += "out";
		File file = new File("output/" + where);
		PrintStream o = new PrintStream(file);
		System.setOut(o);
	}
	@Override
	public void visit(VariableNode varNode) {
		// TODO Auto-generated method stub
		DeclareNode view = getVariable(varNode, varNode.variable);
		ConstantNode variableValue = (ConstantNode) view.children.get(1);
		System.out.println(variableValue.value);
	}

	@Override
	public void visit(IfNode ifNode) {
		// TODO Auto-generated method stub
		ConditionNode conditionNode = (ConditionNode) ifNode.children.get(0);
		DeclareNode checkVariable = getVariable(conditionNode, conditionNode.name);
		ConstantNode checkValue = (ConstantNode) checkVariable.children.get(1);
		int chooseIfElse = checkValue.value;
		if (chooseIfElse > 0) {
			ifNode.children.get(1).accept(this);

		} else if (ifNode.children.size() == 3) {
			ifNode.children.get(2).accept(this);
		}

	}

	@Override
	public void visit(GreaterThanNode greaterNode) {
		// TODO Auto-generated method stub
			int[] sum = new int[2];
			int currentValue = 0;
			for(int i = 0; i < greaterNode.children.size(); i++) {
				if(greaterNode.children.get(i) instanceof ConstantNode) {
					ConstantNode constant = (ConstantNode)greaterNode.children.get(i);
					currentValue = constant.value;
				}
				else if(greaterNode.children.get(i) instanceof RvalNode) {
					RvalNode auxiliary = (RvalNode)greaterNode.children.get(i);
					DeclareNode declareVariable = getVariable(greaterNode.children.get(i), auxiliary.data);
					currentValue = declareVariable.getConstantNodeValue();
				}
				sum[i] = currentValue;
				
			}
			if(greaterNode.parent instanceof AssignmentNode) {
				
				LvalNode updateValue = (LvalNode) greaterNode.parent.children.get(0);
				DeclareNode updateDeclaredVariable = getVariable(updateValue, updateValue.data);
				ConstantNode checkValue = (ConstantNode) updateDeclaredVariable.children.get(1);
				if(sum[0] - sum[1] > 0) {
				checkValue.value = 1;
				}
			else
				checkValue.value = 0;
			}
			else {
				AndNode parent = (AndNode) greaterNode.parent;
				parent.value = sum[0] - sum[1] > 0 ? (1) : (0);
			}
	}

	@Override
	public void visit(EqualToNode equalNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < equalNode.children.size(); i++) {
			equalNode.children.get(i).accept(this);
		}
		if(equalNode.children.get(0) instanceof ConstantNode) {
			ConstantNode constant = (ConstantNode) equalNode.children.get(0);
			equalNode.value = constant.value;
		}
		if(equalNode.children.get(0) instanceof RvalNode) {
			RvalNode firstNode = (RvalNode)equalNode.children.get(0);
			DeclareNode firstValue = getVariable(equalNode, firstNode.data);
			equalNode.value = firstValue.getConstantNodeValue();
		}
		ConstantNode secondValueToCompare = (ConstantNode) equalNode.children.get(1);
		int finalValue = (equalNode.value == secondValueToCompare.value) ? (1) : (0);
		if(equalNode.parent instanceof AssignmentNode) {
			LvalNode assignedNode = (LvalNode) equalNode.parent.children.get(0);
			DeclareNode assignedNodeDeclaration = getVariable(equalNode, assignedNode.data);
			ConstantNode assignedNodeValue = (ConstantNode) assignedNodeDeclaration.children.get(1);
			assignedNodeValue.value = finalValue;
		}
		else {
			OrNode orNode = (OrNode) equalNode.parent;
			orNode.value = finalValue;
		}
		
			

	}

	@Override
	public void visit(IfBodyNode ifBodyNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < ifBodyNode.children.size(); i++)
			ifBodyNode.children.get(i).accept(this);
	}

	@Override
	public void visit(ElseBodyNode elseBodyNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < elseBodyNode.children.size(); i++)
			elseBodyNode.children.get(i).accept(this);

	}

	@Override
	public void visit(LvalNode lvalNode) {

		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MainNode mainNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DifferenceNode minusNode) {
		// TODO Auto-generated method stub
		RvalNode firstNodeToCompare = (RvalNode)minusNode.children.get(0);
		RvalNode secondNodeToCompare = (RvalNode)minusNode.children.get(1);
		DeclareNode firstValue = getVariable(minusNode, firstNodeToCompare.data);
		DeclareNode secondValue = getVariable(minusNode, secondNodeToCompare.data);
		LvalNode assignedNode = (LvalNode) minusNode.parent.children.get(0);
		DeclareNode assignedNodeDeclaration = getVariable(minusNode, assignedNode.data);
		ConstantNode assignedNodeValue = (ConstantNode) assignedNodeDeclaration.children.get(1);
		assignedNodeValue.value = firstValue.getConstantNodeValue() - secondValue.getConstantNodeValue();
		
		
	}

	@Override
	public void visit(DivisionOperatorNode divisionNode) {
		// TODO Auto-generated method stub
		RvalNode firstNodeToCompare = (RvalNode)divisionNode.children.get(0);
		RvalNode secondNodeToCompare = (RvalNode)divisionNode.children.get(1);
		DeclareNode firstValue = getVariable(divisionNode, firstNodeToCompare.data);
		DeclareNode secondValue = getVariable(divisionNode, secondNodeToCompare.data);
		LvalNode assignedNode = (LvalNode) divisionNode.parent.children.get(0);
		DeclareNode assignedNodeDeclaration = getVariable(divisionNode, assignedNode.data);
		ConstantNode assignedNodeValue = (ConstantNode) assignedNodeDeclaration.children.get(1);
		assignedNodeValue.value = firstValue.getConstantNodeValue() / secondValue.getConstantNodeValue();

	}

	@Override
	public void visit(ModuloOperatorNode moduloNode) {
		// TODO Auto-generated method stub
		RvalNode firstNodeToCompare = (RvalNode)moduloNode.children.get(0);
		RvalNode secondNodeToCompare = (RvalNode)moduloNode.children.get(1);
		DeclareNode firstValue = getVariable(moduloNode, firstNodeToCompare.data);
		DeclareNode secondValue = getVariable(moduloNode, secondNodeToCompare.data);
		moduloNode.value = firstValue.getConstantNodeValue() % secondValue.getConstantNodeValue();
		if(moduloNode.parent instanceof EqualToNode) {
			EqualToNode equal = (EqualToNode) moduloNode.parent;
			equal.value = moduloNode.value;
		}
		else {
			LvalNode assignedNode = (LvalNode) moduloNode.parent.children.get(0);
			DeclareNode assignedNodeDeclaration = getVariable(moduloNode, assignedNode.data);
			ConstantNode assignedNodeValue = (ConstantNode) assignedNodeDeclaration.children.get(1);
			assignedNodeValue.value = firstValue.getConstantNodeValue() % secondValue.getConstantNodeValue();
			
		}

	}

	@Override
	public void visit(MultiplicationOperatorNode multiplicationNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < multiplicationNode.children.size(); i++) {
			multiplicationNode.children.get(i).accept(this);
		}
		if(multiplicationNode.value == 0) {
			RvalNode firstNodeToCompare = (RvalNode)multiplicationNode.children.get(0);
			RvalNode secondNodeToCompare = (RvalNode)multiplicationNode.children.get(1);
			DeclareNode firstValue = getVariable(multiplicationNode, firstNodeToCompare.data);
			DeclareNode secondValue = getVariable(multiplicationNode, secondNodeToCompare.data);
			multiplicationNode.value = firstValue.getConstantNodeValue() * secondValue.getConstantNodeValue();
		}
		LvalNode assignedNode = (LvalNode) multiplicationNode.parent.children.get(0);
		DeclareNode assignedNodeDeclaration = getVariable(multiplicationNode, assignedNode.data);
		ConstantNode assignedNodeValue = (ConstantNode) assignedNodeDeclaration.children.get(1);
		assignedNodeValue.value = multiplicationNode.value;
		multiplicationNode.value = 0;
	}

	private RvalNode orHelper(RvalNode firstValueToCompare, RvalNode secondValueToCompare) {

		DeclareNode firstNode = getVariable(firstValueToCompare, firstValueToCompare.data);
		DeclareNode secondNode = getVariable(secondValueToCompare, secondValueToCompare.data);
		ConstantNode firstValue = (ConstantNode) firstNode.children.get(1);
		ConstantNode secondValue = (ConstantNode) secondNode.children.get(1);
		if (firstValue.value > 0)
			return firstValueToCompare;
		else if (secondValue.value > 0)
			return secondValueToCompare;
		return null;
	}

		public void visit(OrNode orNode) {
		// TODO Auto-generated method stub
		int finalValue = 0;
		if (orNode.children.get(0) instanceof ConstantNode && orNode.children.get(1) instanceof ConstantNode) {
			ConstantNode valueOne = (ConstantNode) orNode.children.get(0);
			ConstantNode valueTwo = (ConstantNode) orNode.children.get(1);
			if (valueOne.value > 0)
				finalValue = 1;
			else
				finalValue = valueTwo.value > 0 ? (1) : (0);
		} else if (orNode.children.get(0) instanceof RvalNode && orNode.children.get(1) instanceof RvalNode) {
			RvalNode firstNodeToCompare;
			RvalNode secondNodeToCompare;
			if (orNode.children.get(0) instanceof OrNode) {
				firstNodeToCompare = orHelper((RvalNode) orNode.children.get(0).children.get(0),
						(RvalNode) orNode.children.get(0).children.get(1));

			} else {
				firstNodeToCompare = (RvalNode) orNode.children.get(0);
			}
			secondNodeToCompare = (RvalNode) orNode.children.get(1);
			RvalNode PositiveValue = orHelper(firstNodeToCompare, secondNodeToCompare);
			DeclareNode finalNode = getVariable(PositiveValue, PositiveValue.data);
			ConstantNode finalNodeValue = (ConstantNode) finalNode.children.get(1);
			finalValue = finalNodeValue.value;
		} else {
			orNode.children.get(0).accept(this);
			RvalNode secondNodeToCompare = (RvalNode) orNode.children.get(1);
			DeclareNode secondNode = getVariable(orNode, secondNodeToCompare.data);
			if (orNode.value > 0)
				finalValue = 1;
			else if (secondNode.getConstantNodeValue() > 0)
				finalValue = 1;
			else
				finalValue = 0;
		}
		if (orNode.parent instanceof AssignmentNode) {
			LvalNode destinationNode = (LvalNode) orNode.parent.children.get(0);
			DeclareNode destinationDeclareNode = getVariable(destinationNode, destinationNode.data);
			ConstantNode destinationNodeValue = (ConstantNode) destinationDeclareNode.children.get(1);
			destinationNodeValue.value = finalValue;

		}
		else {
			OrNode parent = (OrNode) orNode.parent;
			parent.value = finalValue;
		}
	}


	@Override
	public void visit(SumOperatorNode plusNode) {
		// TODO Auto-generated method stub
			
			int sum = 0;
			for(int i = 0; i < plusNode.children.size(); i++) {
				if(plusNode.children.get(i) instanceof ConstantNode) {
					ConstantNode constant = (ConstantNode)plusNode.children.get(i);
					sum += constant.value;
				}
				else if(plusNode.children.get(i) instanceof RvalNode) {
					RvalNode auxiliary = (RvalNode)plusNode.children.get(i);
					DeclareNode declareVariable = getVariable(plusNode.children.get(i), auxiliary.data);
					sum += declareVariable.getConstantNodeValue();
				}
			}
			if(plusNode.parent instanceof AssignmentNode) {
				
				LvalNode updateValue = (LvalNode) plusNode.parent.children.get(0);
				DeclareNode updateDeclaredVariable = getVariable(updateValue, updateValue.data);
				ConstantNode addValue = (ConstantNode) updateDeclaredVariable.children.get(1);
				addValue.value = sum;
			}
			if(plusNode.parent instanceof MultiplicationOperatorNode) {
				MultiplicationOperatorNode multiplicator = (MultiplicationOperatorNode)plusNode.parent;
				multiplicator.value = sum;
			}
		}

	

	@Override
	public void visit(PrintNode printNode) {
		// TODO Auto-generated method stub
		printNode.children.get(0).accept(this);
	}

	@Override
	public void visit(RvalNode rvalNode) {
		// TODO Auto-generated method stub
		if (rvalNode.parent instanceof AssignmentNode) {

			// inseamna ca este sursa, cea care ii va atribui
			// nodului rval din branch-ul curent(cu parintele assignmentNode)
			// valoarea sa
			DeclareNode source = getVariable(rvalNode, rvalNode.data);
			LvalNode aux = (LvalNode) rvalNode.parent.children.get(0);
			DeclareNode destination = getVariable(rvalNode, aux.data);
			ConstantNode giver = (ConstantNode) source.children.get(1);
			ConstantNode receiver = (ConstantNode) destination.children.get(1);
			receiver.value = giver.value;
		}
	}

	@Override
	public void visit(StringNode stringNode) {
		// TODO Auto-generated method stub
		String text = stringNode.text.substring(1, stringNode.text.length() - 1);
		System.out.println(text);

	}

	@Override
	public void visit(DeclareNode declareNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ConstantNode constantNode) {
		// TODO Auto-generated method stub
		if (constantNode.parent instanceof AssignmentNode) {

			LvalNode aux = (LvalNode) constantNode.parent.children.get(0);
			DeclareNode destination = getVariable(constantNode, aux.data);
			ConstantNode receiver = (ConstantNode) destination.children.get(1);
			receiver.value = constantNode.value;
		}
		else if(constantNode.parent instanceof MultiplicationOperatorNode ) {
			MultiplicationOperatorNode multiplicator = (MultiplicationOperatorNode) constantNode.parent;
			if(multiplicator.value == 0)
				multiplicator.value = 1;
			multiplicator.value *= constantNode.value;
		}
	}

	@Override
	public void visit(ConditionNode conditionNode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AssignmentNode assignNode) {
		// TODO Auto-generated method stub
		for (int i = 0; i < assignNode.children.size(); i++) {
			assignNode.children.get(i).accept(this);
		}

	}

	@Override
	public void visit(AndNode andNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < andNode.children.size(); i++)
			andNode.children.get(i).accept(this);
		if(andNode.value == 0 && andNode.children.get(0) instanceof ConstantNode) {
			ConstantNode valueOne = (ConstantNode)andNode.children.get(0);
			ConstantNode valueTwo = (ConstantNode)andNode.children.get(1);
			if(valueOne.value > 0 && valueTwo.value > 0)
				andNode.value = 1;
			else
				andNode.value = 0;
		}
		else if(andNode.children.get(1) instanceof ConstantNode) {
			ConstantNode valueTwo = (ConstantNode)andNode.children.get(1);
			if(andNode.value > 0 && valueTwo.value > 0)
				andNode.value = 1;
			else
				andNode.value = 0;
		}
		else {
			RvalNode valueTwo = (RvalNode)andNode.children.get(1);
			DeclareNode declareVariable = getVariable(andNode, valueTwo.data);
			if(andNode.value > 0 && declareVariable.getConstantNodeValue() > 0)
				andNode.value = 1;
			else
				andNode.value = 0;
			
		}
		LvalNode updateValue = (LvalNode) andNode.parent.children.get(0);
		DeclareNode updateDeclaredVariable = getVariable(updateValue, updateValue.data);
		ConstantNode changeValue = (ConstantNode) updateDeclaredVariable.children.get(1);
		changeValue.value = andNode.value;
		andNode.value = 0;

	}

	@Override
	public void visit(TreeNode tree) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(WhileNode whileNode) {
		// TODO Auto-generated method stub
		ConditionNode condition = (ConditionNode)whileNode.children.get(0);
		DeclareNode conditionName = getVariable(whileNode, condition.name);
		while(conditionName.getConstantNodeValue() > 0) {
			for(int i = 0; i < whileNode.children.size(); i++)
				whileNode.children.get(i).accept(this);
			condition = (ConditionNode)whileNode.children.get(0);
			conditionName = getVariable(whileNode, condition.name);
		}
	}

	@Override
	public void visit(BodyNode bodyNode) {
		// TODO Auto-generated method stub
		for(int i = 0; i < bodyNode.children.size(); i++)
			bodyNode.children.get(i).accept(this);
	}

	private DeclareNode getVariable(TreeNode t, String name) {
		while (t instanceof MainNode == false) {
			t = t.parent;
		}
		for (int i = 0; i < t.children.size(); i++) {
			if (t.children.get(i) instanceof DeclareNode) {
				LvalNode lval = (LvalNode) t.children.get(i).children.get(0);
				if (lval.data.equalsIgnoreCase(name))
					return (DeclareNode) t.children.get(i);
			}
		}
		return null;

	}

}
