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
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule = new ArrayList<>();// ��scheduleһ����һ��map������¼ʱ���
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
	}// �������֮�����������Բ��ø�

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
		for (int i = 0; i < listOfSchedule.size(); ++i) {// һ��һ������ı���
			listofGaps.add(new TreeMap<Integer, Map<String, scheduleRequest>>());// ����д��ֻ��һ��ԭ����������ˡ�����
			Map<String, scheduleRequest> set = new HashMap<>();// �˴���˴���ֻΪÿ�α����ϴε�set
			for (Map.Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(i).entrySet()) {
				Map<String, scheduleRequest> setCopy = new HashMap<>(set);// �˴���Ϊ�˱�֤set��ı�
				for (scheduleWrapper sW : entry.getValue()) {
					if (sW.startEnd) {
						setCopy.put(sW.originalObject.id, sW.originalObject);
					} else {
						setCopy.remove(sW.originalObject.id);
					} // ������ǰ��set
				}
				set = setCopy;
				listofGaps.get(i).put(entry.getKey(), setCopy);
				// ������ǰ����gap�ı�
			}
			if (!listofGaps.get(i).containsKey(0)) {
				Map<String, scheduleRequest> emptyMap = new HashMap<>();
				listofGaps.get(i).put(0, emptyMap);// ���gap��list����û��0ʱ��ǵü��룬
												// ����0����Ϊ���0֮���ֽϴ�Ŀ��п���ֱ�Ӳ���
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
					: Math.log((double) ((getMax(endTimes)) + 1) / (double) ((insertTimes.get(i)) + 1));// ������Ĳ���ֵ�ͳ�ʼֵ�Ժ󶼵øģ�������const
			Double value = valueList.get(i) * mapOfValue.get(sI.id) + (mid); // first
																				// fit
			if (result == null || value > result) {// �ȴ��Ǳ�С��Ҫ������
				result = value;
				location = i;
			}
			System.out.println("value:" + value + ",starttime:" + insertTimes.get(i) + ",endtime" + getMax(endTimes));
		} // �˴���δ����location����Ϊ0�������
		System.out.println("result:" + result + "location" + location);
		return new scheduleRequest(sI.id, location, insertTimes.get(location), insertTimes.get(location) + sI.duration);
	}

	public scheduleRequest optimize1(scheduleinsertionObject sI)// first fit �汾
	{
		// Integer insertTime = null; // Integer insertLocation = null;
		List<Integer> insertTimes = new LinkedList<>();
		List<Integer> endTimes = new ArrayList<>();
		for (int i = 0; i < listofGaps.size(); ++i) {
			Integer insertTime = 0;
			if (!listofGaps.get(i).isEmpty()) {
				endTimes.add(listofGaps.get(i).lastKey());
				Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);// �ҵ���һ�����Կ�ʼ�ĵ�
				while (indexBegin != null) {// �����б�Ϊ�գ���һ�Σ����Ǹ��´εĿ�ʼ��Ϊ�յ�ʱ��
					Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
					if ((indexEnd == null || indexEnd - indexBegin >= sI.duration) && (insertTime == 0)) {// ����ע��time�����Ǵ�1����0��ʼ
						insertTime = indexBegin;
						break;// �ҵ�����ֹ
					} else {
						indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// ������¸����Ե�λ�ÿ�ʼ
					}
				}
			} else {
				endTimes.add(0);
			}
			insertTimes.add(insertTime);
		} // System.out.println(endTimes.toString());
		return maxValue(insertTimes, endTimes, sI);
	}

	public scheduleRequest optimize2(scheduleinsertionObject sI) {// most fit������
		List<Integer> insertTimes = new LinkedList<>();
		List<Integer> endTimes = new LinkedList<>();
		for (int i = 0; i < listofGaps.size(); ++i) {
			int gap = Integer.MAX_VALUE;
			Integer insertTime = 0;
			Integer endTime = Integer.MAX_VALUE;
			Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);
			while (indexBegin != null) {// �����б�Ϊ�գ���һ�Σ����Ǹ��´εĿ�ʼ��Ϊ�յ�ʱ��
				Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
				if (indexEnd == null) {
					if (endTime == Integer.MAX_VALUE) {//ע�⣬�˴��п��ܻ���ֽ�β��������������
						insertTime = indexBegin;
					}
				} else if (indexEnd - indexBegin >= sI.duration) {
					if (indexEnd - indexBegin - sI.duration < gap) {
						insertTime = indexBegin;
						endTime = indexEnd;
						gap = indexEnd - indexBegin - sI.duration;
					}
				}
				indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// ������¸����Ե�λ�ÿ�ʼ
			}
			insertTimes.add(insertTime);
			endTimes.add(endTime);
		}
		// System.out.println("insertTimes:"+insertTimes.toString()+"endTimes:"+endTimes.toString());
		return maxValue(insertTimes, endTimes, sI);
	}
}
