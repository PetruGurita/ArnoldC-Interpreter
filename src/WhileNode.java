
public class WhileNode extends TreeNode {
	
	public WhileNode() {
		
	}
	public void accept(Visitor v) {
        v.visit(this);          
}

}
