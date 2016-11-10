
public class Rotate_String {
	public static int rightRotate(String word1, String word2) {
	    if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0 || word1.length() != word2.length()) {
	        return -1;
	    }
	    String str = word1 + word1;
	    return str.indexOf(word2) != -1 ? 1 : -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*public static boolean isRotation(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 ||
		s2.length() == 0 || s1.length() != s2.length()) {
		return false;
		}
		String xyxy = s1 + s1;
		return xyxy.contains(s2);
		}*/
	
	

}
