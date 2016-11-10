
public class MergeTwoList {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode node = new ListNode(0);
		ListNode newhead = node;   //remember to save the head
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				node.next = l1;
				l1 = l1.next;    //remember to move
			} else {
				node.next = l2;
				l2 = l2.next;
			}
			node = node.next;   //remember to move 
		}
		if (l1 != null) {
			node.next = l1;
		}
		if (l2 != null) {
			node.next = l2;
		}
		return newhead.next;
	}
	
	
	/*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);   
            return l1;   //return the head
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
        
    }*/
}
