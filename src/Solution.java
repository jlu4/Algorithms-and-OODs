import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */

public class Solution {
	 public static boolean validWordAbbreviation(String word, String abbr) {
		 if (word == null || abbr == null) return false;
	        if (word.length() < abbr.length()) return false;
	        Queue<Character> queue = new LinkedList<Character>();
	        int i = 0, j = 0; 
	        while (i < word.length() && j < abbr.length()) {
	                if (word.charAt(i) == abbr.charAt(j)) {
	                	i++;
	                	j++;
	                    continue;
	                } else if (abbr.charAt(j) > '9' || abbr.charAt(j) < '0') {
	                    return false;
	                } else {
	                       queue.offer(abbr.charAt(j));
	                       if  (j + 1 == abbr.length() || (j + 1 < abbr.length() && abbr.charAt(j + 1) >= 'a' 
	                    && abbr.charAt(j + 1) <= 'z')) {
	                           StringBuilder sb = new StringBuilder();
	                           while (!queue.isEmpty()) {
	                               sb.append(queue.poll());
	                           }
	                           String s = sb.toString();
	                           int index = Integer.parseInt(s);
	                           if (j + 1 == abbr.length() && i + index == word.length()) {
	                        	   
	                               return true;
	                           }
	                           i = i + index;
	                           if (i >= word.length()) {
	                               return false;
	                           }
	                       }
		                   j++;
	                    }
	                }
	        System.out.println(i);
	        System.out.println(word.length());
	        System.out.println(j);
	        System.out.println(abbr.length());
	        if (i != word.length() || j != word.length()) {System.out.println("hi"); return false;}
	             return true;
	        }  
	public static void main(String[] args) {
		String a = "internationalization";
		String b = "i12iz4n";
		boolean list = validWordAbbreviation(a, b);
		System.out.println(list);

	}
}
