package PocketGem;

public class TernaryExpressionToBinaryTree {
	public static TreeNode solve(String s) {
		if (s == null || s.length() == 0)
			return null;
		if (s.length() == 1)
			return new TreeNode(s.charAt(0));
		int flag = 0;
		int mid = 0;
		for (int i = 2; i <= s.length() - 1; i++) {
			if (s.charAt(i) == '?')
				flag++;
			else if (s.charAt(i) == ':') {
				if (flag == 0) {
					mid = i;
					break;
				} else
					flag--;
			}
		}
		TreeNode head = new TreeNode(s.charAt(0));
		TreeNode temp_left = solve(s.substring(2, mid));
		TreeNode temp_right = solve(s.substring(mid + 1, s.length()));
		head.left = temp_left;
		head.right = temp_right;
		return head;
	}
}
