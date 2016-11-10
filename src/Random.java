
public class Random {
	 public RandomListNode copyRandomList(RandomListNode head) {
	        if (head == null) return head;
	        RandomListNode c = head;
	        while (c != null) {       //remember and figure out
	            RandomListNode next = c.next;
	            c.next = new RandomListNode(c.label);
	            c.next.next = next;
	            c = next;
	        }
	        c = head;
	        while (c!= null) {         //remember and figure out
	            if(c.random != null) {        
	                c.next.random = c.random.next;
	            }
	            c = c.next.next;
	        }
	        c = head;
	        RandomListNode copyHead = head.next;
	        RandomListNode copy = copyHead;
	        while (copy.next != null) {     //remember and figure out
	            c.next = c.next.next;        //important
	            c = c.next;
	            copy.next = copy.next.next;
	            copy = copy.next;
	        }
	        c.next = c.next.next;
	        return copyHead;
	        
	    }
}
