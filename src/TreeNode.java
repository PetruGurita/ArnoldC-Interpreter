
import java.util.ArrayList;

//the basic class TreeNode that all the other nodes extend

public class TreeNode{
	
    TreeNode parent;
    ArrayList<TreeNode> children;

//the node's children    
    public TreeNode() {
        this.children = new ArrayList<TreeNode>();
    }
    
//create the parent-child bound
    public void addChild(TreeNode child) {
    	this.children.add(child);
    	child.parent = this;
    }

//count the node's acestors path to the root, used at printNode
    public int ancestor(TreeNode t) {
    	int ancestors = 0;
    	while(t instanceof MainNode == false) {
    		ancestors = ancestors + 1;
    		t = t.parent;
    	}
    	return ancestors;
    }
    public void accept(Visitor v) {
        v.visit(this);          
}

}
