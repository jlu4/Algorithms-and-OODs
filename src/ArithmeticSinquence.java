/*
	int[] A = {2,5,2,3,4,6,8,10,12,9,8,7,6,2,4,8};
	OA2: find out number of arithmetic sequence in array
	time complexity: O(n)
	space complexity: O(1)
	小明有一个神奇的镜子巴拉巴拉，可以神奇地反射巴拉巴拉”楼主一下子慌了还以为出新题了，仔细看了才发现就是经典的right rotation。所以小伙伴做题的时候遇到这种情况也不要惊慌，好好读题就是了
	
	In addition, remove vowel, window minimum, 一道是说每个人会有至少5个分数 求每个人最高的5个分数的平均分, gray code
	
         Four Ingeter，这道题给你四个数a,b,c,d，要求把他们排序使|n0-n1|+|n1-n2|+|n2-n3|最大。 (n0,n1,n2,n3是拍完序的数)
         kth closest point no PQ
         2016年8月之后的面经似乎palindrome和kth closest point命中率很高啊
         
         manacher?
       ？
*/
public class ArithmeticSinquence{
	public static int Solution(int[] nums) {
		if(nums == null || nums.length < 3) return 0;
		int left = 0, right = 1, diff = nums[1] - nums[0], count = 0;
		while(right < nums.length - 1){
			if(diff != nums[right + 1] - nums[right]){
				count += (right - left - 1) * (right - left) / 2;
				if(count > 1000000000) return -1;
				diff = nums[right + 1] - nums[right];
				left = right;
			}
			++right;	
		}
		count += (right - left - 1) * (right - left) / 2;
		return count > 1000000000 ? -1 : count;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{2,5,2,3,4,6,8,10,12,9,8,7,6,2,4,8};
		System.out.println(Solution(nums));

	}
	public static int getLAS(int[] A){
	    // Time: O(n)
	    // Space: O(1)
	    if (A.length < 3) return 0;
	    
	    int res = 0;
	    int diff = Integer.MIN_VALUE;
	    int count = 0;
	    int start = 0;
	    for (int i = 1; i < A.length; i++){
	        int currDiff = A[i] - A[i - 1];
	        if (diff == currDiff){
	            count += i - start - 1 > 0 ? i - start - 1 : 0;
	        } else {
	            start = i - 1;
	            diff = currDiff;
	            res += count;
	            count = 0;
	        }
	    }
	    res += count;
	    return res;
	}
}