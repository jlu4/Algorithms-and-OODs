import java.util.Stack;

// min sum path of BST time complexity: O(n) space complexity: O(hight of tree)

// my version with iterative
/*��һ����Min Path Sum in BST������������˵�������Ҫͨ��������leaf node�����������������������һ��д��ʱ���жϵ��ӽڵ�����ʱ�� && д���� || ��������test casesû�����������˰���ŷ����Լ�д���ˡ�*/
class MinPathSum{
public class minPathSum {
	public int Solution(TreeNode root) {
		if(root == null) return 0;
		if(root.left == null && root.right == null) return root.val;
		int minSum = Integer.MAX_VALUE;
		Stack<TreeNode> pathNode = new Stack<TreeNode>(); //store all nodes at each level
		Stack<Integer> pathSum = new Stack<Integer>(); //store the sum
		pathNode.push(root);
		pathSum.push(root.val);
		while(!pathNode.empty()){   //BFS
			TreeNode crtNode = pathNode.pop();
			int crtSum = pathSum.pop();
			if(crtNode.left == null && crtNode.right == null)
				minSum = crtSum < minSum ? crtSum : minSum;
			if(crtNode.right != null){
				pathNode.push(crtNode.right);
				pathSum.push(crtSum + crtNode.right.val);
			}
			if(crtNode.left != null){
				pathNode.push(crtNode.left);
				pathSum.push(crtSum + crtNode.left.val);
			}
		}
		return minSum;
	}
}

	// other version with recursive
	public class PathSum {
		public int Solution(TreeNode root) {
			if (root == null)
				return 0;
			if (root.left != null && root.right == null)
				return Solution(root.left) + root.val; 
			if (root.left == null && root.right != null)    //&& is necessary
				return Solution(root.right) + root.val;
			return Math.min(Solution(root.left), Solution(root.right)) + root.val;  //root.val
		}
	}
}