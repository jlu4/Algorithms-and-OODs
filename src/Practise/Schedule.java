package Practise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Schedule {
	public class scheduleWrapper {
		boolean startEnd;
		scheduleRequest requestObject;

		public scheduleWrapper(boolean startEnd, scheduleRequest requestObject) {
			this.startEnd = startEnd;
			this.requestObject = requestObject;
		}
	}

	List<TreeMap<Integer, List<scheduleWrapper>>> ListOfSchedule;
	Map<Integer, scheduleRequest> setOfContent;
	List<scheduleRequest> abandon;

	public Schedule() {
		ListOfSchedule = new ArrayList<>();
		setOfContent = new TreeMap<>();
		abandon = new ArrayList<>();
	}

	int numOfContent = 30;
	int locationNum = 6;

	public void InitializeSchedule(List<scheduleRequest> scheduleList) {
		if (scheduleList == null || scheduleList.size() == 0)
			return;
		for (int i = 0; i < locationNum; i++) {
			ListOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest request : scheduleList) {
			if (ListOfSchedule.get(request.location).containsKey(request.starttime)) {
				scheduleWrapper wrapper = new scheduleWrapper(true, request);
				ListOfSchedule.get(request.location).get(request.starttime).add(wrapper);
			} else {
				List<scheduleWrapper> list = new ArrayList<>();
				list.add(new scheduleWrapper(true, request));
				ListOfSchedule.get(request.location).put(request.starttime, list);
			}
			if (ListOfSchedule.get(request.location).containsKey(request.endtime)) {
				scheduleWrapper wrapper = new scheduleWrapper(false, request);
				ListOfSchedule.get(request.location).get(request.endtime).add(wrapper);
			} else {
				List<scheduleWrapper> list = new ArrayList<>();
				list.add(new scheduleWrapper(false, request));
				ListOfSchedule.get(request.location).put(request.endtime, list);
			}
		}

	}

	public List<scheduleRequest> toSchedule(int location) {
		List<scheduleRequest> result = new ArrayList<scheduleRequest>();
		for (Map.Entry<Integer, List<scheduleWrapper>> entry : ListOfSchedule.get(location).entrySet()) {
			for (scheduleWrapper sw : entry.getValue()) {
				if (sw.startEnd == false && setOfContent.containsKey(sw.requestObject.id)) {
					setOfContent.remove(sw.requestObject.id);
					result.add(sw.requestObject);
				}
			}
			for (scheduleWrapper sw : entry.getValue()) {
				if (sw.startEnd) {
					if (setOfContent.containsKey(sw.requestObject.id)) {
						if (getDuration(sw.requestObject) > getDuration(setOfContent.get(sw.requestObject.id))) {
							abandon.add(setOfContent.get(sw.requestObject.id));
							setOfContent.remove(sw.requestObject.id);
							setOfContent.put(sw.requestObject.id, sw.requestObject);
						} else {
							abandon.add(sw.requestObject);
						}
					} else {
						if (setOfContent.size() >= 3) {
							int smallest = getSmallestId(setOfContent);
							if (getDuration(sw.requestObject) - smallest > 0) {
								setOfContent.remove(smallest);
								setOfContent.put(sw.requestObject.id, sw.requestObject);
								abandon.add(sw.requestObject);
							} else {
								abandon.add(sw.requestObject);
							}
						} else {
							setOfContent.put(sw.requestObject.id, sw.requestObject);
						}
					}
				}
			}
		}

		return result;
	}

	public int getDuration(scheduleRequest sr) {
		return sr.endtime - sr.starttime;
	}

	public int getSmallestId(Map<Integer, scheduleRequest> map) {
		int smallest = Integer.MAX_VALUE;
		int result = -1;
		for (Entry<Integer, scheduleRequest> entry : map.entrySet()) {
			int duration = getDuration(entry.getValue());
			if (smallest > duration) {
				smallest = duration;
				result = entry.getValue().id;
			}
		}
		return result;
	}
}
