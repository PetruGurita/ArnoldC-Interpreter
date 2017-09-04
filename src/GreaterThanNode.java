
public class GreaterThanNode extends TreeNode{
	
	public GreaterThanNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
