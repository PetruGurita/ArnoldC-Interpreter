
public class OrNode extends TreeNode {

	public int value;
	public OrNode() {
		this.value = 0;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}

}
