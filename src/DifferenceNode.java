
public class DifferenceNode extends TreeNode {

	public DifferenceNode() {
		
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
