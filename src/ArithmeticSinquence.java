/*
	int[] A = {2,5,2,3,4,6,8,10,12,9,8,7,6,2,4,8};
	OA2: find out number of arithmetic sequence in array
	time complexity: O(n)
	space complexity: O(1)
	С����һ������ľ��Ӱ�����������������ط������������¥��һ���ӻ��˻���Ϊ�������ˣ���ϸ���˲ŷ��־��Ǿ����right rotation������С��������ʱ�������������Ҳ��Ҫ���ţ��úö��������
	
	In addition, remove vowel, window minimum, һ����˵ÿ���˻�������5������ ��ÿ������ߵ�5��������ƽ����, gray code
	
         Four Ingeter�����������ĸ���a,b,c,d��Ҫ�����������ʹ|n0-n1|+|n1-n2|+|n2-n3|��� (n0,n1,n2,n3�����������)
         kth closest point no PQ
         2016��8��֮����澭�ƺ�palindrome��kth closest point�����ʺܸ߰�
         
         manacher?
       ��
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