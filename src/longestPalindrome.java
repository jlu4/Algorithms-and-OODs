
public class longestPalindrome {
	public static int low;
	public static int maxLen;

	public String longestPalindrome(String s) {
		low = 0;
		maxLen = 0;
		if (s.length() < 2)
			return s;
		for (int i = 0; i < s.length(); i++) {  //RE 0
			helper(i, i, s);
			helper(i, i + 1, s);
		}
		return s.substring(low, low + maxLen);
	}

	public void helper(int start, int end, String s) {
		while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
			start--;
			end++;
		}

		if (maxLen < end - start - 1) {
			maxLen = end - start - 1;     // - 1
			low = start + 1;   // inside the loop

		}
	}
}
