import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class Optimization {
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;
		}
	}
	final int locationNum = 3;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule = new ArrayList<>();// 和schedule一样的一个map用来记录时间戳
	List<TreeMap<Integer, Map<String, scheduleRequest>>> listofGaps = new ArrayList<>();
	List<Double> valueList = new ArrayList<>();
	Map<String, Integer> mapOfValue = new HashMap<>();

	public Optimization() {
		valueList.add(1.0);
		valueList.add(0.8);
		valueList.add(0.6);
		mapOfValue.put("1", 10);
		mapOfValue.put("2", 8);
		mapOfValue.put("3", 6);
	}// 这个部分之后会给出，所以不用改

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	public void initializeSchedule(List<scheduleRequest> scheduleList) {
		for (int i = 0; i < locationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest s : scheduleList) {
			if (listOfSchedule.get(s.location).containsKey(s.starttime)) {
				listOfSchedule.get(s.location).get(s.starttime).add(new scheduleWrapper(s, true));
			} else {
				ArrayList<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, true));
				listOfSchedule.get(s.location).put(s.starttime, newList);
			}
			if (listOfSchedule.get(s.location).containsKey(s.endtime)) {
				listOfSchedule.get(s.location).get(s.endtime).add(new scheduleWrapper(s, false));
			} else {
				ArrayList<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, false));
				listOfSchedule.get(s.location).put(s.endtime, newList);
			}
		}
	}

	public void generateGapList() {
		for (int i = 0; i < listOfSchedule.size(); ++i) {// 一个一个区域的保存
			listofGaps.add(new TreeMap<Integer, Map<String, scheduleRequest>>());// 这里写上只有一个原因就是怕忘了。。。
			Map<String, scheduleRequest> set = new HashMap<>();// 此处如此处理只为每次保留上次的set
			for (Map.Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(i).entrySet()) {
				Map<String, scheduleRequest> setCopy = new HashMap<>(set);// 此处是为了保证set会改变
				for (scheduleWrapper sW : entry.getValue()) {
					if (sW.startEnd) {
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
				Map<String, scheduleRequest> emptyMap = new HashMap<>();
				listofGaps.get(i).put(0, emptyMap);// 如果gap的list里面没有0时间记得加入，
												// 加入0是因为如果0之后又较大的空闲可以直接插入
			}
		}
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	private Integer findNextBegin(TreeMap<Integer, Map<String, scheduleRequest>> map, Integer indexBegin,
			scheduleinsertionObject sI) {
		Integer result = indexBegin;
		while (result != null && (map.get(result).size() == 3 || map.get(result).containsKey(sI.id))) {
			result = map.higherKey(result);
		}
		return result;
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	private Integer findNextEnd(TreeMap<Integer, Map<String, scheduleRequest>> map, Integer indexBegin,
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
			System.out.println("value:" + value + ",starttime:" + insertTimes.get(i) + ",endtime" + getMax(endTimes));
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
