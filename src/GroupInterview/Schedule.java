package GroupInterview;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;


public class Schedule {
	/**
	 * wrapper for a shceduleObject used to get the listofmap
	 */
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;   //when startEnd == false, can add into result,条件是再次加入INIT?
		}
	}

	final int LocationNum = 20;
	Map<Integer, scheduleRequest> setOfContents;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule; //以location为坐标 
	List<scheduleRequest> result;
	List<scheduleRequest> abandon;

	public Schedule() {
		setOfContents = new HashMap<>();   //keep the content tempery
		listOfSchedule = new ArrayList<>();
		result = new LinkedList<>();
		abandon = new LinkedList<>();
	}

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	//此LIST的数据结构含义： 包装一个BOOLEAN， 用来判断进程是否
	//多线程
	public void initializeSchedule(List<scheduleRequest> scheduleList) {// 只有这一个地方依赖输入，到时候需要改一下。
		for (int i = 0; i < LocationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest s : scheduleList) {
			if (listOfSchedule.get(s.location).containsKey(s.starttime)) {
				listOfSchedule.get(s.location).get(s.starttime).add(new scheduleWrapper(s, true));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, true));
				listOfSchedule.get(s.location).put(s.starttime, newList);
			}
			if (listOfSchedule.get(s.location).containsKey(s.endtime)) {
				listOfSchedule.get(s.location).get(s.endtime).add(new scheduleWrapper(s, false));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, false));
				listOfSchedule.get(s.location).put(s.endtime, newList);
			}
		}
	}

	/* 当前版本暂不考虑递归求解 */
	public void schedule(int location) {
		for (Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(location).entrySet()) {
			for (scheduleWrapper sW : entry.getValue()) {
				if (!sW.startEnd && setOfContents.containsKey(sW.originalObject.id)) {
					// 这是一个进程到头了需要删除的情况
					result.add(setOfContents.get(sW.originalObject.id));// 只有平安结束的才会计入结果
					setOfContents.remove(sW.originalObject.id);// 从set中去掉
				}
			}
			/* 此处是为了完成先出后进 */
			for (scheduleWrapper sW : entry.getValue()) {
				if (sW.startEnd) {// 插入一个开始的内容
					if (setOfContents.containsKey(sW.originalObject.id)) {// 如果set内已经有同名的内容
						if (calculateDuration(setOfContents.get(sW.originalObject.id)) < calculateDuration(
								sW.originalObject)) {// 比较两者的distance
							abandon.add(setOfContents.get(sW.originalObject.id));// 若原有的value较小，删除原有并保留到abandon中
							setOfContents.remove(sW.originalObject.id);
							setOfContents.put(sW.originalObject.id, sW.originalObject);// 将新的放入
						} else
							abandon.add(sW.originalObject);// 否则则将新的保存于abandon中
					} else if (setOfContents.size() >= 3) {// 如果当前set内的内容数量超过三个
						int keyToRemove = findSmallest(setOfContents);// 找出其中duration最小的一个
						if (calculateDuration(setOfContents.get(keyToRemove)) < calculateDuration(sW.originalObject)) {
							abandon.add(setOfContents.get(keyToRemove));// 如果最小的那个value小于当前要插入的，
							setOfContents.remove(keyToRemove);// 删除最小value的一个
							setOfContents.put(sW.originalObject.id, sW.originalObject);// 将新来的加入set
						} else {
							abandon.add(sW.originalObject);// 否则直接舍弃新来的。
						}
					} else {
						setOfContents.put(sW.originalObject.id, sW.originalObject);// 否则属于正常情况，仅仅就是加入即可
					}
				}
			}
		}
	}

	/**
	 * this function is used to find the element with smallest value in a set of
	 * scheduleObjects the function is called in schedule function
	 */
	//找出最小价值的广告
	public int findSmallest(Map<Integer, scheduleRequest> set2) {
		int smallest = Integer.MAX_VALUE;
		int resultName = 0;
		for (Map.Entry<Integer, scheduleRequest> entry : set2.entrySet()) {
			if (calculateDuration(entry.getValue()) < smallest) {
				smallest = calculateDuration(entry.getValue());
				resultName = entry.getValue().id;
			}
		}
		return resultName;
	}

	/**
	 * this function is used to calculate a duration of a scheduleObject
	 */
	public int calculateDuration(scheduleRequest a) {
		return a.endtime - a.starttime;
	}
}



// 目前该程序唯一的问题在于尚不清楚输出究竟要是一个什么形式。