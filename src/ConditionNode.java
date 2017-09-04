
public class ConditionNode extends TreeNode {

	
	// the field name is used to find the node that has a specific name
	public String name;	
	public ConditionNode(String name) {
		this.name = name;
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
