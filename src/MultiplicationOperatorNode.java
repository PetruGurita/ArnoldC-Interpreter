
public class MultiplicationOperatorNode extends TreeNode{

	public int value;
	public MultiplicationOperatorNode() {
		this.value = 0;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
