
public class ConstantNode extends TreeNode {

	public int value;
	
	public ConstantNode(int value) {

		this.value = value;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}

}
