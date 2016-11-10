/*
	LRU count miss
	time complexity: O(n); space complexity: O(size)
*/
import java.util.*;
class Node2{
	public int val;
	public Node2 pre;
	public Node2 next;
	public Node2(int _val) {
		val = _val;
	}
}
public class LRUMissCount {
	public static int Solution(int[] array, int size) {
		if(array.length == 0 || array == null) return 0;
		if(size == 0) return array.length;
		HashMap<Integer, Node2> cache = new HashMap<Integer, Node2>();
		int countMiss = 0;
		int len = array.length;
		Node2 fakeHead = new Node2(-1);
		Node2 fakeTail = new Node2(-1);
		fakeHead.next = fakeTail;
		fakeTail.pre = fakeHead;
		for(int i = 0; i < len; ++i) {
			if(cache.containsKey(array[i])){
				Node2 tmp = cache.get(array[i]);
				removeNode(tmp);  
				addNode(tmp, fakeTail);  
			} 
			else{
				if(cache.size() == size){
					Node2 tmp = fakeHead.next;
					removeNode(tmp); 
					cache.remove(tmp.val);
				}
				cache.put(array[i], new Node2(array[i]));    //sequence
				addNode(cache.get(array[i]), fakeTail);  
				++countMiss;
			}
			Node2 test = fakeHead.next;
			while(test != fakeTail){
				System.out.print(test.val);
				test = test.next;
			}
			System.out.println();
		}
		return countMiss;
	}
    public static void addNode(Node2 n, Node2 fakeTail) {
      fakeTail.pre.next = n;
      n.pre = fakeTail.pre;
      fakeTail.pre = n;
      n.next = fakeTail;
    }
    public static void removeNode(Node2 n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }
    
    /*public int Solution(int[] array, int size) {
        if (array == null)  return 0;
        List<Integer> cache = new LinkedList<Integer>();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (cache.contains(array[i])) {
                cache.remove(new Integer(array[i]));
            }
            else {
                count++;
                if (size == cache.size())
                    cache.remove(0);
            }
            cache.add(array[i]);
        }
        return count;
    }*/
    
    public static void main(String[] args) {
		int[] nums = new int[]{2, 3, 2, 1, 5, 2, 4, 5, 3, 2, 5, 2};
		System.out.println(Solution(nums, 3));

	}
}