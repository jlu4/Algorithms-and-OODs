import java.util.ArrayList;
import java.util.List;

public class GrayCode {
	 public class Solution {
	        public List<Integer> grayCode(int n) {
	        if(n == 0){
	         List<Integer> res = new ArrayList<Integer>();
	         res.add(0);
	         return res;
	        }
	          List<Integer> tmp = grayCode(n - 1);
	          List<Integer> res = new ArrayList<Integer>(tmp);
	          int addNum = 1 << (n - 1);
	          for(int i = addNum - 1; i >=0; i--){
	                res.add(tmp.get(i) + addNum);
	          }
	          return res;
	    }
	 }
	//term1和term2是题目给的两个BYTE
		/*byte x = (byte)(term1 ^ term2);
		int total = 0;
		while(x != 0){
		    x = (byte) (x & (x - 1));
		    total++;
		}
		if(total == 1) return 1; else return 0;*/
		
		/*public static boolean grayCheck(byte term1, byte term2) {
			byte result = (byte) (term1 ^ term2);
			for (int i = 0; i <= 7; i++) {
			byte temp = (byte) (1 << i);
			if (temp == result) {
			return true;
			}
			}
			return false;
			}*/
}
	 
