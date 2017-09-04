
public class RvalNode extends TreeNode {

	String data = "";
	public RvalNode(String data) {
		this.data = data;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}

}
