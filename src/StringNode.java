
public class StringNode extends TreeNode{

	String text;
	StringNode(String text) {
		this.text = text;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
}
