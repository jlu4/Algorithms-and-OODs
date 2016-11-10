package PocketGem;

public class InorderSuccessorInBST {
	public static TreeNode inorderSuccessor2(TreeNode root, TreeNode node) {
		if (node.right != null) { // 有右孩⼦子，直接找右⼦子树的最⼩小节点
		return minValue(node.right);
		}
		TreeNode succ = null;
		while(root != null) {
		if(root.val > node.val) { // 继续找更⼩小的
		succ = root; // 后继节点必然⽐比node要⼤大，所以只能在这⾥里保存
		root = root.left;
		}
		else if(root.val < node.val){ // 继续找更⼤大的
		root = root.right;
		}
		else{ // root节点和node节点重复，停⽌止
		break;
		}
		}
		return succ;
		}
	public static TreeNode minValue(TreeNode node) {
		TreeNode cur = node;
		// 最⼩小节点必定在最左下⾓角
		while (cur.left != null) {
		cur = cur.left;
		}
		return cur;
		}
	public TreeNode successor(TreeNode root, TreeNode p) {
		  if (root == null)
		    return null;

		  if (root.val <= p.val) {
		    return successor(root.right, p);
		  } else {
		    TreeNode left = successor(root.left, p);
		    return (left != null) ? left : root;
		  }
		}
	public TreeNode predecessor(TreeNode root, TreeNode p) {
		  if (root == null)
		    return null;

		  if (root.val >= p.val) {
		    return predecessor(root.left, p);
		  } else {
		    TreeNode right = predecessor(root.right, p);
		    return (right != null) ? right : root;
		  }
		}
}
