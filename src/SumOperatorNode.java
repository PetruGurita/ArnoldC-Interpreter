
public class SumOperatorNode extends TreeNode {
	
	public SumOperatorNode() {
		
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}

}
