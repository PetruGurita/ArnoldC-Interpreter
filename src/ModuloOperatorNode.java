
public class ModuloOperatorNode extends TreeNode {

	public int value;
	
	public ModuloOperatorNode() {
		this.value = 0;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
