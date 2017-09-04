
public class AndNode extends TreeNode{

	// in nodes used for arithmetic operation have the field 'value'
	public int value;
	public AndNode() {
		this.value = 0;
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
