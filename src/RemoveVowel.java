import java.util.*;

public class RemoveVowel {

	public RemoveVowel() {
		// TODO Auto-generated constructor stub
	}
	
	public String removeVowel(String original) {
		if (original == null) return null;
		StringBuilder noVowelSb = new StringBuilder();
		Set<Character> vowels = new HashSet<Character>();
		vowels.add('a');
		vowels.add('A');
		vowels.add('e');
		vowels.add('E');
		vowels.add('i');
		vowels.add('I');
		vowels.add('o');
		vowels.add('O');
		vowels.add('u');
		vowels.add('U');
		char cur;
		for (int i = 0; i < original.length(); i++) {
			cur = original.charAt(i);
			if (!vowels.contains(cur)) {
				noVowelSb.append(cur);
			}
		}
		return noVowelSb.toString();
		
	}
	/*StringBuffer sb = new StringBuffer();
	String v = "aeiouAEIOU";
	for(int i = 0; i < string.length(); i++){
	    if(v.indexOf(string.charAt(i)) > -1) continue; 
	    sb.append(string.charAt(i));
	}
	return sb.toStirng();
*/
}
