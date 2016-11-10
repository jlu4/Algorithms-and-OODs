public class RotateMatrix {
	public static int[][] Solution(int[][] matrix, int flag) {
		// if matrix is null return itself
		if (matrix.length == 0 || matrix[0].length == 0 || matrix == null)
	         return null;
	         int row = matrix.length;
	         int col = matrix[0].length;
	         int[][] res = new int[col][row];
	         for (int i = 0; i < col; i++) {
	             for (int j = 0; j < row; j++) {
	                 if(flag == 0)
	                 res[i][j] = matrix[row - 1 -j][i];
	                 else if(flag == 1)
	                 res[i][j] = matrix[j][col - 1 - i];
	             }
	         }
	         return res;
	}

	public static void main(String args[]) {
		int[][] a = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				 };
		int[][] b = Solution(a, 0);
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println("");
		}
	}
}