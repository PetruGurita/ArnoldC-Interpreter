
public class LvalNode extends TreeNode{

	public String data;
	
	public LvalNode(String data) {
		
		this.data = data;
	}

	public void accept(Visitor v) {
        v.visit(this);          
	}
	
}
