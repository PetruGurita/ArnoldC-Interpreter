
public class DivisionOperatorNode extends TreeNode {

	public DivisionOperatorNode() {
		
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
