
public class IfBodyNode extends TreeNode {

	public IfBodyNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
