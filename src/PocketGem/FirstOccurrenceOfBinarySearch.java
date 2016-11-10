package PocketGem;

public class FirstOccurrenceOfBinarySearch {
	private int firstOccurrenceBinarySearch(int[] source, int needle) {
		int low = 0;
		int high = source.length - 1;
		int firstOccurrence = Integer.MIN_VALUE;
		while (low <= high) {
			int middle = low + ((high - low) >>> 1);
			if (source[middle] == needle) {
				// key found and we want to search an earlier occurrence
				firstOccurrence = middle;
				high = middle - 1;
			} else if (source[middle] < needle) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		if (firstOccurrence != Integer.MIN_VALUE) {
			return firstOccurrence;
		}
		return -(low + 1); // key not found
	}
}
