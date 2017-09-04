
public class ElseBodyNode extends TreeNode {

	public ElseBodyNode() {
		
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
