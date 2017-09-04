
public class AssignmentNode extends TreeNode {

	public AssignmentNode() {
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
