package GroupInterview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Selection {
	class scheduleWrapper {
		public scheduleRequest requestObject;
		public selectionRequest selectionObject;

		scheduleWrapper(scheduleRequest s, selectionRequest s1) {
			this.requestObject = s;
			this.selectionObject = s1;

		}
	}

	public List<scheduleRequest> select(List<scheduleRequest> scheduleList, int timePoint) {
		HashMap<Integer, Integer> locationValueMap = new HashMap<Integer, Integer>();
		locationValueMap.put(1, 6);
		locationValueMap.put(2, 5);
		locationValueMap.put(3, 1);
		locationValueMap.put(4, 4);
		locationValueMap.put(5, 2);
		locationValueMap.put(6, 3);
		HashMap<Integer, Integer> contentScoreMap = new HashMap<Integer, Integer>();
		contentScoreMap.put(1, 2);
		contentScoreMap.put(2, 1);
		contentScoreMap.put(3, 9);
		contentScoreMap.put(4, 90);
		contentScoreMap.put(5, 4);
		contentScoreMap.put(6, 7);
		contentScoreMap.put(7, 8);
		contentScoreMap.put(8, 6);
		contentScoreMap.put(9, 3);
		Stack<scheduleRequest> stack = new Stack<scheduleRequest>();
		Collections.sort(scheduleList, new Comparator<scheduleRequest>() {
			@Override
			public int compare(scheduleRequest o1, scheduleRequest o2) {
				return o1.location - o2.location;
			}
		});
		List<scheduleRequest> result = new ArrayList<scheduleRequest>();
		stack.push(scheduleList.get(0));
		int[] max = new int[1];
		max[0] = 0;
		scheduleRequest[] maxSr = new scheduleRequest[1];
		int[] level = new int[1];
		level[0] = 1;
		Set<Integer> set = new HashSet<Integer>();
		int count = 1;
		for (int i = 1; i <= 6; i++) {
			while (!stack.isEmpty()) {
				scheduleRequest sr = stack.pop();
				if (sr.location != i) {
					stack.push(sr);
					break;
				}
				if (!set.contains(sr.id) && sr.starttime <= timePoint && sr.endtime >= timePoint) {
					if (locationValueMap.get(sr.location) * contentScoreMap.get(sr.id) > max[0]) {
						max[0] = locationValueMap.get(sr.location) * contentScoreMap.get(sr.id);
						maxSr[0] = sr;
					}
				}
				if (count != scheduleList.size()) {
					stack.push(scheduleList.get(count));
					count++;
				} else {
					break;
				}
			}
			if (maxSr[0] != null && !set.contains(maxSr[0].id)) {
				result.add(maxSr[0]);
				set.add(maxSr[0].id);
			}
			maxSr[0] = null;
			max[0] = 0;
		}
		System.out.println("=====================================================");

		for (scheduleRequest sr : result) {
			if (sr != null) {
				System.out.print("id =" + sr.id + " ");
				System.out.print("starttime =" + sr.starttime + " ");
				System.out.print("endtime =" + sr.endtime + " ");
				System.out.print("location =" + sr.location + " ");
				System.out.println("");
			}
		}
		return result;
	}

	public void DFS(List<scheduleRequest> scheduleList, int timePoint, int[] max,
			HashMap<Integer, Integer> locationValueMap, HashMap<Integer, Integer> contentScoreMap,
			List<scheduleRequest> result, int i, int[] level, scheduleRequest[] maxSr) {
		System.out.println("location=" + scheduleList.get(i).location);
		System.out.println("area=" + level[0]);
		if (scheduleList.get(i).location != level[0]) {
			result.add(maxSr[0]);
			max[0] = 0;
			return;
		}

		if (scheduleList.get(i).starttime <= timePoint && scheduleList.get(i).endtime >= timePoint) {
			System.out.println("hi");
			if (locationValueMap.get(scheduleList.get(i).location)
					* contentScoreMap.get(scheduleList.get(i).id) > max[0]) {
				max[0] = locationValueMap.get(scheduleList.get(i).location)
						* contentScoreMap.get(scheduleList.get(i).id);
				maxSr[0] = scheduleList.get(i);
				System.out.println("hi1");
			}
		}
		DFS(scheduleList, timePoint, max, locationValueMap, contentScoreMap, result, i + 1, level, maxSr);
		return;

	}

	public int getDuration(scheduleRequest origin) {
		return origin.endtime - origin.starttime;
	}

}
