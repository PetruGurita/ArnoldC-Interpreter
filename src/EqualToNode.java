
public class EqualToNode extends TreeNode {

	public int value;
		
	public EqualToNode() {
		this.value = 0;
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
