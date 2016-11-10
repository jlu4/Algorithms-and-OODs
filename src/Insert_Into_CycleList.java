/*
	Insert into sorted cycle linked list
	time complexity: O(n);
*/
// case 1 : pre.val ¡Ü x ¡Ü cur.val:
// Insert between pre and cur.
// case 2 : x is the maximum or minimum value in the list:
// Insert before the head. e.g. the head has the smallest value and its pre.val > head.val.
// case 3 : Traverses back to the starting point:
// Insert before the starting point.
public class Insert_Into_CycleList {
	public static CNode Solution(CNode head, int target) {
		CNode newNode = new CNode(target);
		if (head == null) {                  //remember
			newNode.next = newNode;
			return newNode;
		}
		CNode crtNode = head;
		/*
		 * CNode realHead = head;
		 */
		do {
			// find real head of the linked list
			/*
			 * if(crtNode.val > crtNode.next.val) realHead = crtNode.next;
			 */
			// < insert at the end of same elements, > insert at the beginning
			// of same elements
			if (target > crtNode.val && target <= crtNode.next.val) //case 1    // > <=
				break;
			if (crtNode.val > crtNode.next.val && (target > crtNode.val || target <= crtNode.next.val)) //case 2
				break;
			crtNode = crtNode.next;    //remember
		} while (crtNode != head); // when back to starting point, then stop. For case 3
		newNode.next = crtNode.next;
		crtNode.next = newNode;
		return newNode;
		// return realHead;
	}
}