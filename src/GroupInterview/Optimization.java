package GroupInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import GroupInterview.Schedule.scheduleWrapper;

public class Optimization {
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;
		}
	}
	final int locationNum = 6;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule;// 和schedule一样的一个map用来记录时间戳
	List<TreeMap<Integer, Map<Integer, scheduleRequest>>> listofGaps;
	List<Double> valueList = new ArrayList<>();
	Map<Integer, Integer> mapOfValue = new HashMap<>();

	public Optimization() {
		listOfSchedule = new ArrayList<TreeMap<Integer, List<scheduleWrapper>>>();
		listofGaps = new ArrayList<TreeMap<Integer, Map<Integer, scheduleRequest>>>();
		valueList.add(0.2);
		valueList.add(1.8);
		valueList.add(1.4);
		valueList.add(1.2);
		valueList.add(1.0);
		valueList.add(0.8);
		valueList.add(0.6);
		valueList.add(0.4);
		valueList.add(0.2);
		valueList.add(0.1);
		mapOfValue.put(1, 0); //id, value
		mapOfValue.put(2, 10);
		mapOfValue.put(3, 6);
		mapOfValue.put(4, 5);
		mapOfValue.put(5, 4);
		mapOfValue.put(6, 3);
		mapOfValue.put(7, 2);
		mapOfValue.put(8, 1);
		mapOfValue.put(9, 1);
	}// 这个部分之后会给出，所以不用改

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	//List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule = new ArrayList<>();
	//In listOfschedule, directly get(i) will get the location/area, every TreeMap means an area.
	//In the area TreeMap, key is the starttime/endtime, value is the list of the wrapper
	//Often use to get the starttime/endtime and the add a wrapper into the list,  if add a false wrapper, will show and means done
	public void initializeSchedule(List<scheduleRequest> scheduleList) {
		
		for (int i = 0; i <= locationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		System.out.println("listOfSchedule size = " + listOfSchedule.size());
		for (scheduleRequest s : scheduleList) {
			System.out.println("s.location = "+s.location);
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
     
	
	//List<TreeMap<Integer, Map<Integer, scheduleRequest>>> listofGaps = new ArrayList<>();
	//List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule = new ArrayList<>();
	//In listOfschedule, directly get(i) will get the location/area, every TreeMap means an area.
	//In the area TreeMap, key is the starttime/endtime, value is the list of the wrapper
	//Often use to get the starttime/endtime and the add a wrapper into the list,  if add a false wrapper, will show and means done
	//Map<Integer, scheduleRequest> setOfContents;   id + orginalObject
	public void generateGapList() {
		
		for (int i = 0; i < listOfSchedule.size(); ++i) {// 一个一个区域的保存
			listofGaps.add(new TreeMap<Integer, Map<Integer, scheduleRequest>>());// 这里写上只有一个原因就是怕忘了。。。
			Map<Integer, scheduleRequest> set = new HashMap<>();// 此处如此处理只为每次保留上次的set
			//to every area

			for (Map.Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(i).entrySet()) { //starttime/endtime - key
				//setOfContents?
				Map<Integer, scheduleRequest> setCopy = new HashMap<>(set);// 此处是为了保证set会改变
				for (scheduleWrapper sW : entry.getValue()) {
					if (sW.startEnd) { //not finish?
						setCopy.put(sW.originalObject.id, sW.originalObject);
					} else {
						setCopy.remove(sW.originalObject.id);
					} // 构建当前的set
				}
				set = setCopy;
				listofGaps.get(i).put(entry.getKey(), setCopy);
				// 构建当前所有gap的表
			}
			if (!listofGaps.get(i).containsKey(0)) {
				Map<Integer, scheduleRequest> emptyMap = new HashMap<>();
				listofGaps.get(i).put(0, emptyMap);// 如果gap的list里面没有0时间记得加入，
												// 加入0是因为如果0之后又较大的空闲可以直接插入
			}
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		for(TreeMap<Integer, Map<Integer, scheduleRequest>> gapList : listofGaps) {
			for(Map.Entry<Integer, Map<Integer, scheduleRequest>> entry : gapList.entrySet()) {
				for(Map.Entry<Integer, scheduleRequest> entry1 : entry.getValue().entrySet()) {
					System.out.print("id = " + entry1.getKey() + " ");
					System.out.print("idOfValue = " + entry1.getValue().id+ " ");
					System.out.print("locationOfValue = " + entry1.getValue().location + " ");
					System.out.print("starttimeOfValue = " + entry1.getValue().starttime + " ");
					System.out.print("endtimeOfValue = " + entry1.getValue().endtime + " ");
					System.out.println(" ");
				}
			}
		}
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	//not fit, will keep going
	private Integer findNextBegin(TreeMap<Integer, Map<Integer, scheduleRequest>> map, Integer indexBegin,
			scheduleinsertionObject sI) {
		Integer result = indexBegin;
		while (result != null && (map.get(result).size() == 3 || map.get(result).containsKey(sI.id))) {//no end, no exceed 3, no duplicate
			System.out.println("map.get(result).size() "+ map.get(result).size());
			result = map.higherKey(result);
		}
		return result;
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	//fit, will keep going
	private Integer findNextEnd(TreeMap<Integer, Map<Integer, scheduleRequest>> map, Integer indexBegin,
			scheduleinsertionObject sI) {
		Integer result = map.higherKey(indexBegin);
		while (result != null && map.get(result).size() < 3 && !map.get(result).containsKey(sI.id)) {
			result = map.higherKey(result);
		}
		return result;
	}

	private Integer getMax(List<Integer> Times) {
		Integer result = 0;
		for (Integer time : Times) {
			result = Math.max(time, result);
		}
		return result;
	}

	/**
	 * this function is used to judge the value of a result and it contains some
	 * standard for different cases
	 */
	public scheduleRequest maxValue(List<Integer> insertTimes, List<Integer> endTimes, scheduleinsertionObject sI) {
		Double result = null;
		Integer location = null;
		
		for (int i = 0; i < insertTimes.size(); ++i) {
			// Double
			// value=(valueList.get(i)*mapOfValue.get(sI.name))/((double)endTimes.get(i)-(double)insertTimes.get(i));//most-fit
			Double mid = insertTimes.get(i) == endTimes.get(i) ? 0
					: Math.log((double) ((getMax(endTimes)) + 1) / (double) ((insertTimes.get(i)) + 1));// 这里面的测试值和初始值以后都得改，最好提成const
			Double value = valueList.get(i) * mapOfValue.get(sI.id) + (mid); // first
																				// fit
			if (result == null || value > result) {// 比大还是比小主要看这里
				result = value;
				location = i;
			}
			System.out.println("value: " + value + " starttime: " + insertTimes.get(i) + " endtime " + getMax(endTimes));
		} // 此处并未考虑location数字为0的情况。
		System.out.println("result:" + result + "location" + location);
		return new scheduleRequest(sI.id, location, insertTimes.get(location), insertTimes.get(location) + sI.duration);
	}

	public scheduleRequest optimize1(scheduleinsertionObject sI)// first fit 版本
	{
		// Integer insertTime = null; // Integer insertLocation = null;
		List<Integer> insertTimes = new LinkedList<>();
		List<Integer> endTimes = new ArrayList<>();
		for (int i = 0; i < listofGaps.size(); ++i) {
			Integer insertTime = 0;
			if (!listofGaps.get(i).isEmpty()) {
				endTimes.add(listofGaps.get(i).lastKey());
				Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);// 找到第一个可以开始的点
				while (indexBegin != null) {// 当该列表不为空（第一次）或是该下次的开始不为空的时候
					Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
					if ((indexEnd == null || indexEnd - indexBegin >= sI.duration) && (insertTime == 0)) {// 这里注意time到底是从1还是0开始
						insertTime = indexBegin;
						break;// 找到即终止
					} else {
						indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// 否则从下个可以的位置开始
					}
				}
			} else {
				endTimes.add(0);
			}
			insertTimes.add(insertTime);
		} // System.out.println(endTimes.toString());
		return maxValue(insertTimes, endTimes, sI);
	}

		public scheduleRequest optimize2(scheduleinsertionObject sI) {// most fit的做法
			List<Integer> insertTimes = new LinkedList<>();
			List<Integer> endTimes = new LinkedList<>();
			for (int i = 0; i < listofGaps.size(); ++i) {
				int gap = Integer.MAX_VALUE;
				Integer insertTime = 0;
				Integer endTime = Integer.MAX_VALUE;
				Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);
				while (indexBegin != null) {// 当该列表不为空（第一次）或是该下次的开始不为空的时候
					Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
					if (indexEnd == null) {
						if (endTime == Integer.MAX_VALUE) {//注意，此处有可能会出现结尾是最大整数的情况
							insertTime = indexBegin;
						}
					} else if (indexEnd - indexBegin >= sI.duration) {
						if (indexEnd - indexBegin - sI.duration < gap) {
							insertTime = indexBegin;
							endTime = indexEnd;
							gap = indexEnd - indexBegin - sI.duration;
						}
					}
					indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// 否则从下个可以的位置开始
				}
				insertTimes.add(insertTime);
				endTimes.add(endTime);
			}
			// System.out.println("insertTimes:"+insertTimes.toString()+"endTimes:"+endTimes.toString());
			return maxValue(insertTimes, endTimes, sI);
		}
}