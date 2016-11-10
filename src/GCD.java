import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
/*
Greatest Common Divisor
time complexity: O(n)
space complexity: O(1)

*/
public class GCD {
	public static float GCDtest(int[] nums) {
		if(nums == null || nums.length == 0 || nums[0] == 0) return 0;
		if(nums.length == 1) return nums[0];  //remember only one
		int divisor = nums[0];
		for(int i = 1; i < nums.length; ++i) {
          	if(nums[i] == 0) return 0;  //remember zero
			int divident = nums[i]; 
			while( divident % divisor != 0){   //Euclid's algorithm
				int tmp = divisor;
				divisor = divident % divisor;
				divident = tmp;
			}
		}
		return divisor;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{32, 16, 8};
		System.out.println(GCDtest(nums));

	}
}
