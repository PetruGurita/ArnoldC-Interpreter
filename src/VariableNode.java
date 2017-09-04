
public class VariableNode extends TreeNode {

	String variable;
	VariableNode(String variable) {
		this.variable = variable;
	}
	
	public void accept(Visitor v) {
        v.visit(this);          
	}
}

