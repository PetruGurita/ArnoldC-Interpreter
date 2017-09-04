
public class BodyNode extends TreeNode{

	public BodyNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
