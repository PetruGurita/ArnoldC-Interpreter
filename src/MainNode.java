
public class MainNode extends TreeNode {

	MainNode() {
		
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
