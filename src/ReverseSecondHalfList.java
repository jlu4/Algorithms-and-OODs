
public class ReverseSecondHalfList {
	public static ListNode Solution(ListNode head) {
		if(head == null || head.next == null || head.next.next == null) return head;    
		ListNode slow = head;
		ListNode fast = head.next;
		while(fast.next != null && fast.next.next != null) {     //include mid if odd
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode pre = null;     //important
		ListNode start = slow.next;   
		fast = null;   
		while(start != null) {
			fast = start.next;
			start.next = pre;
			pre = start;
			start = fast;
		}
      	slow.next = pre;      //remember
      	return head;
	}
}
