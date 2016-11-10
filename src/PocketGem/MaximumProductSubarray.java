package PocketGem;

public class MaximumProductSubarray {
	 public int maxProduct(int[] A) {
	        if(A == null || A.length == 0)
	        return 0;
	        int max = A[0];
	        int currmax = A[0];
	        int currmin = A[0];
	        for(int i = 1; i < A.length; i++){
	            int temp = currmax;
	            currmax = Math.max(A[i], Math.max(currmax * A[i], currmin * A[i]));
	            currmin = Math.min(A[i], Math.min(temp * A[i], currmin * A[i]));
	            max = Math.max(currmax, max);
	        }
	        return max;
	    }
}
