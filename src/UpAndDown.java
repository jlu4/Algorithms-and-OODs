import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


class Student {
	int id;
	int score;
	public Student(int id, int score) {
		this.id = id;
		this.score = score;
	}
	public Student() {
		
	}
}
public class UpAndDown {
    
	public static Map<Integer, Integer> Solution(List<Student> list) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(list.size(), new Comparator<Integer>(){
			@Override
			public int compare(Integer s1, Integer s2){
				return s1 - s2;
			}
		});
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (Student s : list) {
			if (map.containsKey(s.id)) {
			     map.get(s.id).add(s.score);
			} else {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(s.score);
				map.put(s.id, tmp);
			}
		}
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			for (Integer score : entry.getValue()) {
				
				pq.offer(score);
				if(pq.size() > 3) {
					pq.poll();
				}
			}
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += pq.poll();
			}
			entry.getValue().add(sum / 3);
		}
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			int lastIndex = entry.getValue().size() - 1;
			result.put(entry.getKey(), entry.getValue().get(lastIndex));
		}
		return result;
		
		
	}
	
	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
	    Student s1 = new Student(1, 5);  // 1: 5, 3, 6, 7, 3   2: 6, 9, 7, 5, 3   3: 7, 6, 1, 9, 2
	    Student s2 = new Student(2, 6);  // 4: 1, 1, 4, 9, 0   5: 6, 7, 9, 8, 2
	    Student s3 = new Student(3, 7);
	    Student s4 = new Student(4, 1);
	    Student s5 = new Student(5, 8);
	    Student s6 = new Student(5, 2);
	    Student s7 = new Student(2, 9);
	    Student s8 = new Student(3, 2);
	    Student s9 = new Student(1, 3);
	    Student s10 = new Student(5, 6);
	    Student s11 = new Student(5, 7);
	    Student s12 = new Student(5, 9);
	    Student s13 = new Student(1, 6);
	    Student s14 = new Student(1, 7);
	    Student s15 = new Student(1, 3);
	    Student s16 = new Student(2, 7);
	    Student s17 = new Student(2, 5);
	    Student s18 = new Student(2, 3);
	    Student s19 = new Student(3, 6);
	    Student s20 = new Student(3, 1);
	    Student s21 = new Student(3, 9);
	    Student s22 = new Student(4, 1);
	    Student s23 = new Student(4, 4);
	    Student s24 = new Student(4, 9);
	    Student s25 = new Student(4, 0);
	    list.add(s1);list.add(s2);list.add(s3);list.add(s4);list.add(s5);list.add(s6);list.add(s7);list.add(s8);list.add(s9);
	    list.add(s10);list.add(s11);list.add(s12);list.add(s13);list.add(s14);list.add(s15);list.add(s16);list.add(s17);list.add(s18);
	    list.add(s19);list.add(s20);list.add(s21);list.add(s22);list.add(s23);list.add(s24);list.add(s25);
	    Map<Integer, Integer> map = Solution(list);
	    for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
	    	System.out.println(entry.getKey() + " " + entry.getValue());
	    }
	    
	    

	}

}