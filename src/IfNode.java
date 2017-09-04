
public class IfNode extends TreeNode {

	public IfNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
