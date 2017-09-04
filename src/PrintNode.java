
public class PrintNode extends TreeNode {

	PrintNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
