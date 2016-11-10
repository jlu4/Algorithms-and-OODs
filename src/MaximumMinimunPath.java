
public class MaximumMinimunPath {
	static int m, n, max;
	public static int maximumMinimumPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
		return Integer.MIN_VALUE;
		}
		m = matrix.length;
		n = matrix[0].length;
		max = Integer.MIN_VALUE;
		DFS(matrix, 0, 0, matrix[0][0]);
		return max;
		}
		private static void DFS(int[][] matrix, int i, int j, int currentMin) {
		if (i >= m || j >= n) {   //equal
		return;
		}
		currentMin = Math.min(currentMin, matrix[i][j]);
		if (i == m - 1 && j == n - 1) {      //remember
		max = Math.max(max, currentMin);
		return;
		}
		DFS(matrix, i + 1, j, currentMin);
		DFS(matrix, i, j + 1, currentMin);
		}
}
