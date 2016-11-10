package PocketGem;

public class strStr {
	public int strStr(String haystack, String needle) {
		int l1 = haystack.length(), l2 = needle.length();
		if (l1 < l2) return -1;
		if (l2 == 0 || needle == null) return 0;
		int threshold = l1 - l2;
		for (int i = 0; i <= threshold; ++i) {
		if (haystack.substring(i,i+l2).equals(needle)) {
		return i;
		}
		}
		return -1;
		}
}
