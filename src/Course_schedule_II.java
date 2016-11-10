import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */

public class Course_schedule_II {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> adj = new ArrayList<List<Integer>>(); //build the adjacent list
		for (int i = 0; i < numCourses; i++) {
			adj.add(i, new ArrayList<Integer>()); //initialize
		}
		for (int i = 0; i < prerequisites.length; i++) {
			adj.get(prerequisites[i][1]).add(prerequisites[i][0]); //set prerequisite courses to each course
		}
		Stack<Integer> stack = new Stack<Integer>(); //store selected course
		boolean[] selected = new boolean[numCourses];
		boolean[] isLoop = new boolean[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (!topologicalSort(stack, i, adj, selected, isLoop))
				return new int[0];
		}
		int[] res = new int[numCourses];
		int j = 0;
		while (!stack.isEmpty()) {
			res[j++] = stack.pop();
		}
		return res;
	}

	public boolean topologicalSort(Stack<Integer> stack, int u, List<List<Integer>> adj, boolean[] selected,
			boolean[] isLoop) {
		if (selected[u]) 
			return true;  
		if (isLoop[u])   
			return false;
		isLoop[u] = true;  
		for (Integer v : adj.get(u)) {
			if (!topologicalSort(stack, v, adj, selected, isLoop))   //remember v
				return false;
		}
		selected[u] = true;
		stack.push(u);
		return true;

	}

	public static void main(String[] args) {
		Point p1 = new Point();
		p1.x = 1;
		p1.y = 2;
		Point p2 = new Point();
		p2.x = 1;
		p2.y = 3;
		Point p3 = new Point();
		p3.x = -1;
		p3.y = 1;
		Point p4 = new Point();
		p4.x = -1;
		p4.y = 3;
		Point p5 = new Point();
		p5.x = 0;
		p5.y = -1;
		Point p6 = new Point();
		p6.x = 3;
		p6.y = -1;
		Point p7 = new Point();
		p7.x = 0;
		p7.y = 0;
		Point p8 = new Point();
		p8.x = -1;
		p8.y = 3;
		Point p9 = new Point();
		p9.x = 2;
		p9.y = 2;
		Point origin = new Point(0, 0);
		Point[] p = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8, p9 };

	}
}
