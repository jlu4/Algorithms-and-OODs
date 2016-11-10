package readbook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*Design a system which would make a schedule for a user to complete a book in given number of days. A pre condition is that the schedule for every day should end at the end of some chapter. 
Ex ¨C 3 chapter with 10 pages each and user has to complete this book in 2 days, then the schedule should be either be 2 chapters on first day and 1 chapter on second or 1 chapter on first day and 2 chapters on second. (code)*/

/*The basic idea is to get the total number of pages in the book / Number of days. This will give approx. pages that the user has to read each day. Now find the nearest chapter ending from this average and assign those chapters to each day. 
Example: Chapter 1 - 10 pages, Chapter 2 - 10 pages, Chapter 3 - 30 pages, Chapter 4 - 40 pages, Chapter 5 - 10 pages. 
Number of days = 4 

Average = 100/4 = 25 pages per day. 
Day 1 : Chapter 1 + Chapter 2 
Day 2: Chapter 3 
Day 3: Chapter 4 
Day 4: Chapter 5*/

public class readBook {
	public static List<Set<Integer>> readBook(Map<Integer, Integer> pagesPerChapter, int totalDays) {
		// Store pages count in the array {like 10, 20, 50, 90, 100}
		int pages[] = new int[pagesPerChapter.size()];
		pages[0] = pagesPerChapter.get(1);
		for (int index = 1; index < pages.length; index++) {
			pages[index] = pages[index - 1] + pagesPerChapter.get(index + 1);
		}

		int averagePages = pages[pages.length - 1] / totalDays;
		List<Set<Integer>> chaptersPerDay = new ArrayList<>();
		int index = 0, product = 1;
		while (index < pages.length) {
			int closest = getClosestToValueIndex(pages, index, averagePages * product++);
			Set<Integer> chapters = new HashSet<>();
			for (int value = index; value <= closest; value++) {
				chapters.add(value + 1);
			}
			index = closest + 1;
			chaptersPerDay.add(chapters);
		}
		return chaptersPerDay;
	}

	private static int getClosestToValueIndex(int[] data, int low, int key) {
		int high = data.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (data[mid] == key) {
				return mid;
			} else {
				int d1 = Math.abs(data[mid] - key);
				int d2 = Math.abs(data[mid + 1] - key);
				if (d2 <= d1) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
		}
		return high;
	}
}
