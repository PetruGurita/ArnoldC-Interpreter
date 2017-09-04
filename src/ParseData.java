
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.FileNotFoundException;

public class ParseData {

	public Stack<TreeNode> nodeStack;
	public MainNode root;
	
	public TreeNode buildTree(String fileName) throws IOException {
		ReadData data = new ReadData();
		BufferedReader buffer = data.readFile(fileName);
		String command = buffer.readLine();
		// get root
		String[] startMain = command.split("\\s+");
		while (startMain[0].equals("IT'S") && startMain[1].equals("SHOWTIME")) {
			command = buffer.readLine();
			startMain = command.split("\\s+");
		}
		nodeStack = new Stack<TreeNode>();
		root = new MainNode();
		root.parent = root;
		nodeStack.push(root);
		
		// processing
		while (nodeStack.isEmpty() == false && command != null) {
			if (addNode(command, buffer) == false) {
				popNode(command);
			}
			command = buffer.readLine();
			

		}
		buffer.close();
		callprint(root, fileName);
		callinterpretor(root, fileName);
		return root;
	}


	private boolean addNode(String command, BufferedReader buffer) throws IOException {
		// we read every line and parse the text to create the instruction node
		String[] args = toArgs(command);
		if(args == null)
			return true;
		if (args[0].equals("HEY") && args[1].equals("CHRISTMAS") && args[2].equals("TREE")) {
			DeclareNode declare = new DeclareNode();
			LvalNode lval = new LvalNode(args[3]);
			declare.addChild(lval);
			while ((args[0].equals("YOU") && args[1].equals("SET") && args[2].equals("US")
					&& args[3].equals("UP")) == false) {
				command = buffer.readLine();
				args = command.split("\\s+");
			}
			int value = getValue(args, 4);
			ConstantNode constant = new ConstantNode(value);
			declare.addChild(constant);
			nodeStack.peek().addChild(declare);
			return true;
		}
		else if (args[0].equals("TALK") && args[1].equals("TO") && args[2].equals("THE") && args[3].equals("HAND")) {
			PrintNode print = new PrintNode();
			char quote = args[4].charAt(0);
			if (quote == '"') {
				String[] parseQuotes = command.split("\"");
				StringNode stringNode = new StringNode("\"" + parseQuotes[1] + "\"");
				print.addChild(stringNode);
			} else if (args[4].matches("^-?\\d+$")) {
				int value = Integer.parseInt(args[4]);
				ConstantNode constant = new ConstantNode(value);
				print.addChild(constant);
			} else {
				
				for (TreeNode t : root.children) {
					if (t instanceof DeclareNode) {
						
						LvalNode checkName = (LvalNode) t.children.get(0);
						if (checkName.data.equals(args[4])) {
							VariableNode variable = new VariableNode(args[4]);
							print.addChild(variable);
						}
						
					}
				}
			}
			nodeStack.peek().addChild(print);
			return true;

		}
		else if (args[0].equals("GET") && args[1].equals("TO") && args[2].equals("THE") && args[3].equals("CHOPPER")) {
			AssignmentNode assignNode = new AssignmentNode();
			String name = args[4];
			LvalNode lvalNode = new LvalNode(name);
			assignNode.addChild(lvalNode);
			nodeStack.peek().addChild(assignNode);
			nodeStack.push(assignNode);
			
			return true;
		}
		else if (args[0].equals("HERE") && args[1].equals("IS") && args[2].equals("MY") && args[3].equals("INVITATION")) {
			int value = getValue(args, 4);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				nodeStack.peek().addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[4]);
				nodeStack.peek().addChild(rvalNode);
			}
			return true;

		}

		else if (args[0].equals("CONSIDER") && args[1].equals("THAT") && args[2].equals("A") && args[3].equals("DIVORCE")) {
			OrNode orNode = new OrNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				orNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 4);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				orNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[4]);
				orNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(orNode);
			return true;
		}
		else if (args[0].equals("KNOCK") && args[1].equals("KNOCK")) {
			AndNode andNode = new AndNode();
			if (nodeStack.peek() instanceof AssignmentNode) {
				andNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 2);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				andNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[2]);
				andNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(andNode);
			return true;
		}

		else if (args[0].equals("LET") && args[1].equals("OFF") && args[2].equals("SOME") && args[3].equals("STEAM")
				&& args[4].equals("BENNET")) {

			GreaterThanNode greaterNode = new GreaterThanNode();
				if (nodeStack.peek() instanceof AssignmentNode) {
				greaterNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 5);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				greaterNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[5]);
				greaterNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(greaterNode);
			return true;
		}
		else if (args[0].equals("YOU") && args[1].equals("ARE") && args[2].equals("NOT") && args[3].equals("YOU")
				&& args[4].equals("YOU") && args[5].equals("ARE") && args[6].equals("ME")) {

			EqualToNode equalToNode = new EqualToNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				equalToNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 7);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				equalToNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[7]);
				equalToNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(equalToNode);
			return true;

		}
		else if (args[0].equals("I") && args[1].equals("LET") && args[2].equals("HIM") && args[3].equals("GO")) {

			ModuloOperatorNode moduloNode = new ModuloOperatorNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				moduloNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 4);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				moduloNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[4]);
				moduloNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(moduloNode);
			return true;

		}

		else if (args[0].equals("HE") && args[1].equals("HAD") && args[2].equals("TO") && args[3].equals("SPLIT")) {

			DivisionOperatorNode divisionNode = new DivisionOperatorNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				divisionNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 4);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				divisionNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[4]);
				divisionNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(divisionNode);
			return true;
		}
		else if (args[0].equals("YOU'RE") && args[1].equals("FIRED")) {

			MultiplicationOperatorNode multiplicationNode = new MultiplicationOperatorNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				multiplicationNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 2);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				multiplicationNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[2]);
				multiplicationNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(multiplicationNode);
			return true;
		}
		else if (args[0].equals("GET") && args[1].equals("DOWN")) {

			DifferenceNode minusNode = new DifferenceNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				minusNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 2);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				minusNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[2]);
				minusNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(minusNode);
			return true;
		}		
		else if (args[0].equals("GET") && args[1].equals("UP")) {

			SumOperatorNode sumNode = new SumOperatorNode();
			
			if (nodeStack.peek() instanceof AssignmentNode) {
				sumNode.addChild(nodeStack.peek().children.get(nodeStack.peek().children.size() - 1));
				nodeStack.peek().children.remove(nodeStack.peek().children.size() - 1);
			}
			int value = getValue(args, 2);
			if (value != -1) {
				ConstantNode constantNode = new ConstantNode(value);
				sumNode.addChild(constantNode);
			} else {
				RvalNode rvalNode = new RvalNode(args[2]);
				sumNode.addChild(rvalNode);
			}
			nodeStack.peek().addChild(sumNode);
			return true;
		}
		else if (args[0].equals("STICK") && args[1].equals("AROUND")) {
			WhileNode whileNode = new WhileNode();
			String name = args[2];
			ConditionNode conditionNode = new ConditionNode(name);
			whileNode.addChild(conditionNode);
			nodeStack.peek().addChild(whileNode);
			nodeStack.push(whileNode);
			BodyNode bodyNode = new BodyNode();
			nodeStack.peek().addChild(bodyNode);
			nodeStack.push(bodyNode);
			
			return true;
			
		}
		else if (args[0].equals("BECAUSE") && args[1].equals("I'M") && args[2].equals("GOING") &&
				 args[3].equals("TO") && args[4].equals("SAY") && args[5].equals("PLEASE")) {
			
			IfNode ifNode = new IfNode();
			String name = args[6];
			ConditionNode condition = new ConditionNode(name);
			ifNode.addChild(condition);
			nodeStack.peek().addChild(ifNode);
			nodeStack.push(ifNode);
			IfBodyNode ifBody = new IfBodyNode();
			nodeStack.peek().addChild(ifBody);
			nodeStack.push(ifBody);
			return true;
		}
		else if (args[0].equals("BULLSHIT")) {
			ElseBodyNode elseNode = new ElseBodyNode();
			nodeStack.pop();
			nodeStack.peek().addChild(elseNode);
			nodeStack.push(elseNode);
			return true;
		}
		return false;
	}

	private void popNode(String command) {
		String[] args = toArgs(command);
		if(args == null)
			return;
		if (args[0].equals("YOU") && args[1].equals("HAVE") && args[2].equals("NO") && args[3].equals("RESPECT")
				&& args[4].equals("FOR") && args[5].equals("LOGIC")) {
			if(nodeStack.peek() instanceof ElseBodyNode || nodeStack.peek() instanceof IfBodyNode)
				nodeStack.pop();
			nodeStack.pop();
		}
		else if (args[0].equals("CHILL")) {
			if(nodeStack.peek() instanceof BodyNode) {
				nodeStack.pop();
			}
			nodeStack.pop();
			
		}
		else if (args[0].equals("YOU") && args[1].equals("HAVE") && args[2].equals("BEEN")
				&& args[3].equals("TERMINATED")) {
			nodeStack.pop();			
		}
		else if (args[0].equals("ENOUGH") && args[1].equals("TALK")) {
			nodeStack.pop();
		}
	
	}
	int getValue(String[] args, int position) {
		if (args.length > position && args[position].equals("@I") && args[position + 1].equals("LIED"))
			return 0;
		else if (args[position].equals("@NO") && args[position + 1].equals("PROBLEMO"))
			return 1;

		else if (args[position].matches("^-?\\d+$"))
			return Integer.parseInt(args[position]);

		return -1;
	}
	void callprint(TreeNode t, String fileName) throws FileNotFoundException{
		Visitor v = new printVisitor(fileName);
		print(t, v);
	}
	void callinterpretor(TreeNode t, String fileName) throws FileNotFoundException{
		Visitor v = new Interpretor(fileName);
		execute(t, v);
	}
	void print(TreeNode t, Visitor v) {
		t.accept(v);
		for(int i = 0; i < t.children.size(); i++) {
			print(t.children.get(i), v);
		}
	}
	void execute(TreeNode t, Visitor v) {
		for(int i = 0; i < t.children.size(); i++) {
			t.children.get(i).accept(v);
		}
	}
	String[] toArgs(String command) {
		String[] args = command.split("\\s+");
		if((args.length == 0) ||(args.length == 1 && args[0].equals("") == true))
			return null;
		List<String> l = new ArrayList<String>();
	    for(String s : args) {
	       if(s != null && s.length() > 0 && s.equals("") == false) {
	          l.add(s);
	       }
	    }
	    args = l.toArray(new String[l.size()]);
		return args;
		
	}

}
