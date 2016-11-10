import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WindowSum {
	public class Solution {
		public int[] maxSlidingWindow(int[] a, int k) {
			if (a == null || k <= 0) {
				return new int[0];
			}
			int n = a.length;
			int[] r = new int[n - k + 1];
			int ri = 0;
			Deque<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < a.length; i++) {
				while (!q.isEmpty() && q.peek() < i - k + 1) {
					q.poll();
				}
				while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
					q.pollLast();
				}
				q.offer(i);
				if (i >= k - 1) {
					r[ri++] = a[q.peek()];     //not poll()
				}
			}
			return r;
		}
	}
	
	public static List<Integer> getSums(int[] nums, int k) {
		List<Integer> result = new ArrayList<>();//注意(arraylist == null || arraylist.size() == 0)
        //要return一个已经初始化的arrayList而不是null，否则会有一个test case过不去
		if (nums == null || nums.length < k) {
			return result;
		}
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (i >= k) {       //first
				sum -= nums[i - k];
			}
			if (i >= k - 1) {   //second
				result.add(sum);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[]{1,3,-1,-3,5,3,6,7};
		List<Integer> A = new ArrayList<Integer>();
		A.add(1);
		A.add(3);A.add(-1);A.add(-3);A.add(5);A.add(3);A.add(6);A.add(7);
		List<Integer> res1 = getSums(num, 3);
		for(Integer i : res1) {
			System.out.println(i);
		}

	}

}
