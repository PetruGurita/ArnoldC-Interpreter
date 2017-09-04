
public class DeclareNode extends TreeNode {

	public DeclareNode() {

	}
	// DeclareNode will always have a node with constant type, and one of type LvalNode
	String getConstantNodeName() {
		LvalNode node = (LvalNode) this.children.get(0);
		return node.data;
	}

	int getConstantNodeValue() {
		ConstantNode node = (ConstantNode) this.children.get(1);
		return node.value;
	}
	public void accept(Visitor v) {
        v.visit(this);          
	}
}
