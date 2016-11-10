/*
	Day change
	time complexity: O(n*days)
	space complexity: O(n)
*/
public class DayChange {
	public static int[] Solution(int[] nums, int days) {
		if (nums == null || nums.length <= 1 || days <= 0)
			return nums;
		int len = nums.length;
		int[] preNum = new int[len];
		preNum = nums; 
		for (int count = 0; count < days; ++count) {
			int[] crtNum = new int[len]; 
			crtNum[0] = preNum[1];                      //remember
			crtNum[len - 1] = preNum[len - 2];
			for (int i = 1; i < len - 1; ++i)
				crtNum[i] = preNum[i - 1] ^ preNum[i + 1]; 
			preNum = crtNum;
		}
		return preNum;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 0, 0, 0, 0, 0, 0, 1 };
		nums = dayChange(nums, 1);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i]);
		}

	}

	public static int[] dayChange(int[] days, int n) {
		if (days == null || days.length == 0 || n <= 0) {
			return days;                 //not return null
		}
		for (int i = 0; i < n; i++) {
			int pre = 0;
			for (int j = 0; j < days.length - 1; j++) {
				int cur = days[j];         //keep the value before change
				days[j] = pre == days[j + 1] ? 0 : 1;
				pre = cur;                            
			}
			days[days.length - 1] = pre == 0 ? 0 : 1;   //remember
		}
		return days;
	}
	
	/*public class DaysChange {
		public static int[] Solution(int[] nums, int days) {
			if(nums == null || nums.length <= 1 || days <= 0) return nums;
			int len = nums.length;
			// preNum represents previous day's list
			int[] preNum = new int[len];
	      	preNum = nums;
			for(int count = 0; count < days; ++count) {
				int[] crtNum = new int[len];
				crtNum[0] = preNum[1];
				crtNum[len - 1] = preNum[len - 2];
				for(int i = 1; i < len - 1; ++i)
					crtNum[i] = preNum[i - 1] ^ preNum[i + 1];
				preNum = crtNum;
			}
			return preNum;
		}
	}*/

}
