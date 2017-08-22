package IHMComponent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyTree {

	private MyNode root = null; 
	public MyTree(MyNode root) {
		this.root = root;
	}

	public JTree convertToJTree() {
		return new JTree(build(root));
	}

	private DefaultMutableTreeNode build(MyNode node) {
		DefaultMutableTreeNode res ;
		if(node.isLeaf())
			res = new DefaultMutableTreeNode(node.getUserObjectName());
		else {
			res = new DefaultMutableTreeNode(node.getUserObjectName());
			for(int i = 0; i < node.getChildCount();i++)
				res.add(build(node.getChildAt(i)));
		}
		return res;
	}
}

